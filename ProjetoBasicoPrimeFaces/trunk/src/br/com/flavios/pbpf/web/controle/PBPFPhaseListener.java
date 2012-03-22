package br.com.flavios.pbpf.web.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.flavios.pbpf.negocio.controle.PBPFBusinessFactory;
import br.com.flavios.pbpf.negocio.controle.PBPFConstantes;
import br.com.flavios.pbpf.negocio.controle.entidade.SistemaTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Sistema;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumSecurity;
import core.jsf.FacesUtil;

public class PBPFPhaseListener implements PhaseListener, PBPFConstantes {

	private static final long serialVersionUID = -2125913687300128502L;
	private String urlDestino;
	
	/**
	 * Valida AUTENTICAÇÃO nas aplicações controladas.
	 */
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			//intercepta mensagens de erro de AUTORIZAÇÃO disparadas pelo Controle de Acesso
			FacesContext context = event.getFacesContext();
			if(PBPFSecurityUtil.getParamSession(context, PBPFEnumSecurity.MENSAGEM_ACESSO_RECURSO) != null){
				String mensagem = (String)PBPFSecurityUtil.getParamSession(context, PBPFEnumSecurity.MENSAGEM_ACESSO_RECURSO);
				FacesUtil.addMessageCtx(context, 	new FacesMessage(mensagem));
				PBPFSecurityUtil.removeParamSession(context, PBPFEnumSecurity.MENSAGEM_ACESSO_RECURSO);
			}
		}
	}
	
	/**
	 * Método responsável por interceptar as requisições e verificar se o usuário
	 * logado na aplicação possui permissão para acessar o recurso (PÁGINA) requisitada.
	 */
	public void afterPhase(PhaseEvent event) {
		//valida se a sessão expirou
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
	 * Valida a existencia da árvore de componentes JSF para a requisição atual, caso não exista
	 * direciona para a página de login do sistema em questão.
	 */
	protected boolean isRequisicaoValida(FacesContext contexto){
		if(contexto == null){
			return false;
		}
		HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		//Redirecionar para página de login. Sessão expirada, inválida ou jSessionId inválido
		if(!(session != null && request.isRequestedSessionIdValid() && contexto.getViewRoot() != null && contexto.getViewRoot().getViewId() != null)){
			//1 VERIFICA A EXISTENCIA DE SISTEMA
			SistemaTO sistema = (SistemaTO)session.getAttribute(PBPFEnumSecurity.SISTEMA_CONTROLADO.name()); 
			//valida a existencia de SISTEMA associado ao controle de acesso
			if(sistema == null){
				//busca por um sistema que contenha o contexto chamado.
				//Verifica se o sistema não é o MOBILE.
				Sistema negocioSistema = PBPFBusinessFactory.getInstance().createSistema();
				if (FacesUtil.getRequest(contexto).getRequestURI().equals(FacesUtil.getRequest(contexto).getContextPath() + URL_MOBILE_LOGIN)) {
					sistema = negocioSistema.consultarSistemaNome("MOBILE");
				} else {
					sistema = negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());					
				}
				//verifica se é um contexto gerenciado pelo ADM, se o contexto não for gerenciado, direciona para a aplicação principal
				if(sistema == null){
					sistema = new SistemaTO();
					sistema.setBitSistemaPrincipal(Boolean.TRUE);
					this.urlDestino = negocioSistema.buscarObjeto(sistema).getVchUrlPaginaPrincipal();
					return false;
				}else {
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					session.setAttribute(PBPFEnumSecurity.SISTEMA_CONTROLADO.name(), sistema);
					return false;
				}
			}else{
				if(PBPFSecurityUtil.getParamSession(contexto, PBPFEnumSecurity.REQUEST_DASH_BOARD) == null){
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					session.setAttribute(PBPFEnumSecurity.SISTEMA_CONTROLADO.name(), sistema);
					return false;							
				}else{
					PBPFSecurityUtil.removeParamSession(contexto, PBPFEnumSecurity.REQUEST_DASH_BOARD);
				}
			}
		}
		return true;
	}
	
	/**
	 * Retorna o PhaseId da fase do ciclo de vida JSF que
	 * esta sendo interceptado e gerênciado pelo Controle de acesso.
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
