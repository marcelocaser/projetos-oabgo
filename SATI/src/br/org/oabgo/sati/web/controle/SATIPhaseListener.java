package br.org.oabgo.sati.web.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.org.oabgo.sati.negocio.controle.SATIBusinessFactory;
import br.org.oabgo.sati.negocio.controle.SATIConstantes;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumSecurity;
import core.jsf.FacesUtil;

/**
 * <b>Classe:</b> SatiPhaseListener.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class SATIPhaseListener implements PhaseListener, SATIConstantes {

	private static final long serialVersionUID = -2125913687300128502L;
	private String urlDestino;
	
	/**
	 * Valida AUTENTICA��O nas aplica��es controladas.
	 */
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			//intercepta mensagens de erro de AUTORIZA��O disparadas pelo Controle de Acesso
			FacesContext context = event.getFacesContext();
			if(SATISecurityUtil.getParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO) != null){
				String mensagem = (String)SATISecurityUtil.getParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO);
				FacesUtil.addMessageCtx(context, 	new FacesMessage(mensagem));
				SATISecurityUtil.removeParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO);
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
			//1 � -- VERIFICA A EXISTENCIA DE SISTEMA
			SistemaTO sistema = (SistemaTO)session.getAttribute(SATIEnumSecurity.SISTEMA_CONTROLADO.name()); 
			//valida a existencia de SISTEMA associado ao controle de acesso
			if(sistema == null){
				//busca por um sistema que contenha o contexto chamado.
				Sistema negocioSistema = SATIBusinessFactory.getInstance().createSistema();
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
				if(SATISecurityUtil.getParamSession(contexto, SATIEnumSecurity.REQUEST_DASH_BOARD) == null){
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					return false;							
				}else{
					SATISecurityUtil.removeParamSession(contexto, SATIEnumSecurity.REQUEST_DASH_BOARD);
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
