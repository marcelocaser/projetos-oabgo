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
public class SATIPhaseListener implements PhaseListener, SATIConstantes {

	private static final long serialVersionUID = -2125913687300128502L;
	private String urlDestino;
	
	/**
	 * Valida AUTENTICAÇÃO nas aplicações controladas.
	 */
	public void beforePhase(PhaseEvent event) {
		if(event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)){
			//intercepta mensagens de erro de AUTORIZAÇÃO disparadas pelo Controle de Acesso
			FacesContext context = event.getFacesContext();
			if(SATISecurityUtil.getParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO) != null){
				String mensagem = (String)SATISecurityUtil.getParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO);
				FacesUtil.addMessageCtx(context, 	new FacesMessage(mensagem));
				SATISecurityUtil.removeParamSession(context, SATIEnumSecurity.MENSAGEM_ACESSO_RECURSO);
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
			//1 ° -- VERIFICA A EXISTENCIA DE SISTEMA
			SistemaTO sistema = (SistemaTO)session.getAttribute(SATIEnumSecurity.SISTEMA_CONTROLADO.name()); 
			//valida a existencia de SISTEMA associado ao controle de acesso
			if(sistema == null){
				//busca por um sistema que contenha o contexto chamado.
				Sistema negocioSistema = SATIBusinessFactory.getInstance().createSistema();
				sistema = negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());
				//verifica se é um contexto gerenciado pelo ADM, se o contexto não for gerenciado, direciona para a aplicação principal
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
	 * esta sendo interceptado e gerênciado pelo Controle de acesso.
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}

}
