package br.org.oabgo.saeo.web.controle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.org.oabgo.saeo.negocio.controle.SAEOBusinessFactory;
import br.org.oabgo.saeo.negocio.controle.SAEOConstantes;
import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumSecurity;
import core.jsf.FacesUtil;

public class SAEOPhaseListener implements PhaseListener, SAEOConstantes {

	private static final long serialVersionUID = -2125913687300128502L;
	private String urlDestino;
	
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			//intercepta mensagens de erro de AUTORIZA��O disparadas pelo Controle de Acesso
			FacesContext context = event.getFacesContext();
			if(SAEOSecurityUtil.getParamSession(context, SAEOEnumSecurity.MENSAGEM_ACESSO_RECURSO) != null){
				String mensagem = (String)SAEOSecurityUtil.getParamSession(context, SAEOEnumSecurity.MENSAGEM_ACESSO_RECURSO);
				FacesUtil.addMessageCtx(context, 	new FacesMessage(mensagem));
				SAEOSecurityUtil.removeParamSession(context, SAEOEnumSecurity.MENSAGEM_ACESSO_RECURSO);
			}
		}
	}
	
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
	
	protected boolean isRequisicaoValida(FacesContext contexto){
		if(contexto == null){
			return false;
		}
		HttpServletRequest request = (HttpServletRequest)contexto.getExternalContext().getRequest();
		HttpSession session = request.getSession(false);
		if(!(session != null && request.isRequestedSessionIdValid() && contexto.getViewRoot() != null && contexto.getViewRoot().getViewId() != null)){
			SistemaTO sistema = (SistemaTO)session.getAttribute(SAEOEnumSecurity.SISTEMA_CONTROLADO.name()); 
			if(sistema == null){
				Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
				sistema = negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());
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
				if(SAEOSecurityUtil.getParamSession(contexto, SAEOEnumSecurity.REQUEST_DASH_BOARD) == null){
					this.urlDestino = sistema.getVchUrlPaginaPrincipal();
					return false;							
				}else{
					SAEOSecurityUtil.removeParamSession(contexto, SAEOEnumSecurity.REQUEST_DASH_BOARD);
				}
			}
		}
		return true;
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
