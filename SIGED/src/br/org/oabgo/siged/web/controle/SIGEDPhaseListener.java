package br.org.oabgo.siged.web.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.org.oabgo.siged.negocio.controle.SIGEDBusinessFactory;
import br.org.oabgo.siged.negocio.controle.SIGEDConstantes;
import br.org.oabgo.siged.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumSecurity;
import core.jsf.FacesUtil;

public class SIGEDPhaseListener implements PhaseListener, SIGEDConstantes {

	private static final long serialVersionUID = -2125913687300128502L;
	private String urlDestino;
	
	/**
	 * Valida AUTENTICA��O nas aplica��es controladas.
	 */
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			//intercepta mensagens de erro de AUTORIZA��O disparadas pelo Controle de Acesso
			FacesContext context = event.getFacesContext();
			if(SIGEDSecurityUtil.getParamSession(context, SIGEDEnumSecurity.MENSAGEM_ACESSO_RECURSO) != null){
				String mensagem = (String)SIGEDSecurityUtil.getParamSession(context, SIGEDEnumSecurity.MENSAGEM_ACESSO_RECURSO);
				FacesUtil.addMessageCtx(context, 	new FacesMessage(mensagem));
				SIGEDSecurityUtil.removeParamSession(context, SIGEDEnumSecurity.MENSAGEM_ACESSO_RECURSO);
			}
		}
	}
	
	/**
	 * M�todo respons�vel por interceptar as requisi��es e verificar se o usu�rio
	 * logado na aplica��o possui permiss�o para acessar o recurso (P�GINA) requisitada.
	 */
	public void afterPhase(PhaseEvent event) {
		//valida se a sess�o expirou
		if(event.getPhaseId().equals(PhaseId.RESTORE_VIEW)){
			FacesContext context = event.getFacesContext();
			if(!isRequisicaoValida(context)){
				try{
					FacesUtil.redirect(context, this.urlDestino);
				}catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Valida a existencia da �rvore de componentes JSF para a requisi��o atual, caso n�o exista
	 * direciona para a p�gina de login do sistema em quest�o.
	 */
	protected boolean isRequisicaoValida(FacesContext contexto){
		if(contexto == null){
			return false;
		}
		HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		//Redirecionar para p�gina de login. Sess�o expirada, inv�lida ou jSessionId inv�lido
		if(!(session != null && request.isRequestedSessionIdValid() && contexto.getViewRoot() != null && contexto.getViewRoot().getViewId() != null)){
			//1 VERIFICA A EXISTENCIA DE SISTEMA
			SistemaTO sistema = (SistemaTO)session.getAttribute(SIGEDEnumSecurity.SISTEMA_CONTROLADO.name()); 
			//valida a existencia de SISTEMA associado ao controle de acesso
			if(sistema == null){
				//busca por um sistema que contenha o contexto chamado.
				Sistema negocioSistema = SIGEDBusinessFactory.getInstance().createSistema();
				sistema = negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());
				//verifica se � um contexto gerenciado pelo ADM, se o contexto n�o for gerenciado, direciona para a aplica��o principal
				if(sistema == null){
					sistema = new SistemaTO();
					sistema.setBitSistemaPrincipal(Boolean.TRUE);
					this.urlDestino = negocioSistema.buscarObjeto(sistema).getVchUrlPaginaPrincipal();
					return false;
				}else {
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					return false;
				}
			}else{
				if(SIGEDSecurityUtil.getParamSession(contexto, SIGEDEnumSecurity.REQUEST_DASH_BOARD) == null){
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					return false;							
				}else{
					SIGEDSecurityUtil.removeParamSession(contexto, SIGEDEnumSecurity.REQUEST_DASH_BOARD);
				}
			}
		}
		return true;
	}
	
	/**
	 * Retorna o PhaseId da fase do ciclo de vida JSF que
	 * esta sendo interceptado e ger�nciado pelo Controle de acesso.
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
