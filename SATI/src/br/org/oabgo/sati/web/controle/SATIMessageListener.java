package br.org.oabgo.sati.web.controle;

import java.util.Iterator;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.org.oabgo.sati.negocio.controle.SATIConstantes;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumSecurity;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import core.jsf.FacesUtil;

/**
 * <b>Classe:</b> SatiMessageListener.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
public class SATIMessageListener implements PhaseListener, SATIConstantes {
	
	public void afterPhase(PhaseEvent arg0) {}
	
	/**
	 * Listener que habilita a div de erros.
	 */
	@SuppressWarnings("unchecked")
	public void beforePhase(PhaseEvent e) {
		//so ativa se existir um contexto do FacesServlet
		FacesContext ctx = FacesContext.getCurrentInstance();
		if(ctx != null){
			Iterator i = ctx.getClientIdsWithMessages();
			//se existir mensagens ativa o MODAL.
			if(i.hasNext()) {
				SATIEnumTipoMensagem tipo = SATIEnumTipoMensagem.ERRO;
				//insere o tipo de mensagens no request
				if(FacesUtil.getParamRequest(ctx, SATIEnumSecurity.SATI_TIPO_MENSAGEM.name()) != null){
					tipo = (SATIEnumTipoMensagem) FacesUtil.getParamRequest(ctx, SATIEnumSecurity.SATI_TIPO_MENSAGEM.name());
				}
				FacesUtil.setParamRequest(ctx, SATIEnumSecurity.SATI_TIPO_MENSAGEM.name(), tipo);
				FacesUtil.setParamRequest(ctx, SATIEnumSecurity.SATI_MODAL_MENSAGEM.name(), Boolean.TRUE);
			}
		}
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}
	
}
