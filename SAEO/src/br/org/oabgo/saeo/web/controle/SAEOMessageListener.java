package br.org.oabgo.saeo.web.controle;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.org.oabgo.saeo.negocio.controle.SAEOConstantes;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumSecurity;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumTipoMensagem;
import core.jsf.FacesUtil;

@SuppressWarnings("serial")
public class SAEOMessageListener implements PhaseListener, SAEOConstantes {
	
	public void afterPhase(PhaseEvent arg0) {}
	
	/**
	 * Listener que habilita a div de erros
	 */
	@SuppressWarnings("unchecked")
	public void beforePhase(PhaseEvent e) {
		//so ativa se existir um contexto do FacesServlet
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(ctx != null){
			Iterator i = ctx.getClientIdsWithMessages();
			//se existir mensagens ativa o MODAL.
			if(i.hasNext()) {
				SAEOEnumTipoMensagem tipo = SAEOEnumTipoMensagem.ERRO;
				//insere o tipo de mensagens no request
				if(FacesUtil.getParamRequest(ctx, SAEOEnumSecurity.SAEO_TIPO_MENSAGEM.name()) != null){
					tipo = (SAEOEnumTipoMensagem) FacesUtil.getParamRequest(ctx, SAEOEnumSecurity.SAEO_TIPO_MENSAGEM.name());
				}
				FacesUtil.setParamRequest(ctx, SAEOEnumSecurity.SAEO_TIPO_MENSAGEM.name(), tipo);
				FacesUtil.setParamRequest(ctx, SAEOEnumSecurity.SAEO_MODAL_MENSAGEM.name(), Boolean.TRUE);
			}
		}
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
