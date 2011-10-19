package br.org.oabgo.siged.web.controle;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.org.oabgo.siged.negocio.controle.SIGEDConstantes;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumSecurity;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import core.jsf.FacesUtil;

@SuppressWarnings("serial")
public class SIGEDMessageListener implements PhaseListener, SIGEDConstantes {
	
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
				SIGEDEnumTipoMensagem tipo = SIGEDEnumTipoMensagem.ERRO;
				//insere o tipo de mensagens no request
				if(FacesUtil.getParamRequest(ctx, SIGEDEnumSecurity.SIGED_TIPO_MENSAGEM.name()) != null){
					tipo = (SIGEDEnumTipoMensagem) FacesUtil.getParamRequest(ctx, SIGEDEnumSecurity.SIGED_TIPO_MENSAGEM.name());
				}
				FacesUtil.setParamRequest(ctx, SIGEDEnumSecurity.SIGED_TIPO_MENSAGEM.name(), tipo);
				FacesUtil.setParamRequest(ctx, SIGEDEnumSecurity.SIGED_MODAL_MENSAGEM.name(), Boolean.TRUE);
			}
		}
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
