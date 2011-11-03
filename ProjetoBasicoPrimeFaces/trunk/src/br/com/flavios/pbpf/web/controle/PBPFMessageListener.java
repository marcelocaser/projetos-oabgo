package br.com.flavios.pbpf.web.controle;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.flavios.pbpf.negocio.controle.PBPFConstantes;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumSecurity;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumTipoMensagem;
import core.jsf.FacesUtil;

@SuppressWarnings("serial")
public class PBPFMessageListener implements PhaseListener, PBPFConstantes {
	
	public void afterPhase(PhaseEvent arg0) {}
	
	/**
	 * Listener que habilita a div de erros
	 */
	public void beforePhase(PhaseEvent e) {
		//so ativa se existir um contexto do FacesServlet
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(ctx != null){
			Iterator<String> i = ctx.getClientIdsWithMessages();
			//se existir mensagens ativa o MODAL.
			if(i.hasNext()) {
				PBPFEnumTipoMensagem tipo = PBPFEnumTipoMensagem.ERRO;
				//insere o tipo de mensagens no request
				if(FacesUtil.getParamRequest(ctx, PBPFEnumSecurity.PBPF_TIPO_MENSAGEM.name()) != null){
					tipo = (PBPFEnumTipoMensagem) FacesUtil.getParamRequest(ctx, PBPFEnumSecurity.PBPF_TIPO_MENSAGEM.name());
				}
				FacesUtil.setParamRequest(ctx, PBPFEnumSecurity.PBPF_TIPO_MENSAGEM.name(), tipo);
				FacesUtil.setParamRequest(ctx, PBPFEnumSecurity.PBPF_MODAL_MENSAGEM.name(), Boolean.TRUE);
			}
		}
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
