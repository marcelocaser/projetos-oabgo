package br.org.oabgo.saeo.negocio.controle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlPanelGrid;

import org.richfaces.component.html.HtmlSimpleTogglePanel;
import org.richfaces.component.html.HtmlToolBar;
import org.springframework.dao.DataIntegrityViolationException;

import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumSecurity;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumTipoMensagem;
import core.controlador.Controlador;
import core.excecoes.AcessoException;
import core.excecoes.RegraNegocioException;
import core.jsf.FacesUtil;
import core.mensagem.Mensagem;
import core.mensagem.MensagemLista;
import core.resource.ResourceServiceUtil;
import core.utilitario.Util;

public class SAEOControle extends Controlador implements SAEOConstantes {
	
	private static final long serialVersionUID = 1L;
	
	private SAEOBusinessFactory saeoBusinessFactory;

	protected 	HtmlToolBar 				menuBarra;
	protected   HtmlSimpleTogglePanel    	simpleTogglePanel;
	protected   boolean                  	estadoSimpleTogglePanel;
	
	/**
	 * Retorna a instancia de negocio das classes de negocio
	 * @return SAEOBusinessFactory
	 */
	protected SAEOBusinessFactory getSAEOBusinessFactory(){
		if(saeoBusinessFactory == null){
			saeoBusinessFactory = SAEOBusinessFactory.getInstance();
		}
		return saeoBusinessFactory;
	}
	
	/**
	 * Retorna o usu�rio logado.
	 * @return UsuarioTO
	 */
	public UsuarioTO getUsuarioLogado(){
		return (UsuarioTO)getParamSession(SAEOEnumSecurity.USUARIO_LOGADO); 
	}

	/**
	 * Retorna o sistema logado.
	 * @return SistemaTO
	 */
	public SistemaTO getSistemaLogado(){
		return (SistemaTO)getParamSession(SAEOEnumSecurity.SISTEMA_CONTROLADO); 
	}

	/**
	 * Insere um par�metro no request.
	 * @param alias - AdmNetEnumSecurity
	 * @param parameter - Object
	 */
	protected void setParamRequest(SAEOEnumSecurity alias, Object parameter) {
		FacesUtil.setParamRequest(this.getContext(), alias.name(), parameter);
	}
	
	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionaliza��o.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, SAEOEnumTipoMensagem tipo) {
		setParamRequest(SAEOEnumSecurity.SAEO_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SAEOEnumSecurity.SAEO_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key)));
	}

	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionaliza��o.
	 * @param parametros - {@link String[]} - Lista de parametros que ser�o inseridos na mensagem.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, String[] parametros, SAEOEnumTipoMensagem tipo) {
		setParamRequest(SAEOEnumSecurity.SAEO_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SAEOEnumSecurity.SAEO_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key, parametros)));
	}
	
	/**
	 * Adiciona uma lista de mensagens no request
	 * @param mensagens - List<Mensagem>
	 * @param tipo - int
	 */
	protected void setMessage(List<Mensagem> listaMensagem, SAEOEnumTipoMensagem tipo) {
		setParamRequest(SAEOEnumSecurity.SAEO_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SAEOEnumSecurity.SAEO_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Adiciona uma lista de mensagens no request
	 * @param mensagens - MensagemLista
	 * @param tipo - int
	 */
	protected void setMessage(MensagemLista listaMensagem, SAEOEnumTipoMensagem tipo) {
		setParamRequest(SAEOEnumSecurity.SAEO_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SAEOEnumSecurity.SAEO_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem.getMensagens()){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Retorna o contexto do sistema
	 * @return String
	 */
	protected String getContextPath(){
		return FacesUtil.getRequest(getContext()).getContextPath();
	}
	
	/**
	 * Insere parametros do controle de acesso na sess�o
	 *
	 * @param key - AdmNetEnumSecurity
	 * @param parameter - Object
	 * @return void
	 */
	protected void setParamSession(SAEOEnumSecurity key, Object parameter){
		super.setParamSession(key.name(), parameter);
	}

	/**
	 * Remove parametros do controle de acesso na sess�o
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return void
	 */
	protected void removeParamSession(SAEOEnumSecurity key){
		super.removeParamSession(key.name());
	}
	
	/**
	 * Remove a autentica��o do usu�io no controle de acesso.
	 * @param sistema - {@link String} - Atributo que representa o sistema que requisitou o logoff 
	 * no controle de acesso.
	 * 
	 * @return void
	 */
	protected void removerAcesso() throws Exception{
		//Destroi a sess�o do Usuario
		sessionDestroy();
		Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
		SistemaTO sistema = negocioSistema.consultarSistemaContexto(getRequest().getContextPath());
		//Redireciona para a p�gina inicial do sistema.
		FacesUtil.redirect(getContext(), sistema.getVchUrlSistema());
	}
	
	/**
	 * Retorna parametros do controle de acesso armazenados na sess�o
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return Object
	 */
	protected Object getParamSession(SAEOEnumSecurity key){
		return super.getParamSession(key.name());
	}
	
	/**
	 * Tratamento de exce��es para filtrar as mensagens emitidas ao usu�rio
	 * @param e - {@link Exception}
	 */
	protected String tratarExcecao(Exception e){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		//Busca a origem de exce��es n�o declaradas
		if(e instanceof UndeclaredThrowableException){
			e = Util.getException(e);
		}else if(e instanceof InvocationTargetException){
			e = Util.getException(e);
		}
		
		//exce��es customizadas
		if (e instanceof RegraNegocioException) {
			mensagens = ((RegraNegocioException)e).getMensagens();
		}else if(e instanceof AcessoException){
			mensagens = ((AcessoException)e).getMensagens();
		}else if(e instanceof DataIntegrityViolationException){
			mensagens.add(new Mensagem(getMessage("admDataIntegrityViolationException")));	
		}else if(e instanceof NullPointerException){
			mensagens.add(new Mensagem(getMessage("admNullPointerException")));	
		}else{
			mensagens.add(new Mensagem(e.getMessage()));			
		}
		e.printStackTrace();
		this.setMessage(mensagens, SAEOEnumTipoMensagem.ERRO);
		return null;
	}
	
	/**
	 * Retorna o valor armazenado no atributo simpleTogglePanel.
	 *
	 * @return HtmlSimpleTogglePanel
	 */
	public HtmlSimpleTogglePanel getSimpleTogglePanel() {
		simpleTogglePanel = new HtmlSimpleTogglePanel();
		simpleTogglePanel.saveState(this.getContext());
		simpleTogglePanel.setOpened(estadoSimpleTogglePanel);
		
		return simpleTogglePanel;
	}

	/**
	 * Altera o estado do toggle panel
	 */
	public void alterarEstadoToggle(){
		estadoSimpleTogglePanel = !estadoSimpleTogglePanel;
	}
	
	/**
	 * Insere um valor ao atributo simpleTogglePanel
	 *
	 * @param simpleTogglePanel - HtmlSimpleTogglePanel
	 */
	public void setSimpleTogglePanel(HtmlSimpleTogglePanel simpleTogglePanel) {
		this.simpleTogglePanel = simpleTogglePanel;
	}

	/**
	 * Retorna o valor armazenado no atributo estadoSimpleTogglePanel.
	 * @return Boolean
	 */
	public Boolean getEstadoSimpleTogglePanel() {
		return estadoSimpleTogglePanel;
	}

	/**
	 * Insere um valor ao atributo estadoSimpleTogglePanel
	 *
	 * @param estadoSimpleTogglePanel - Boolean
	 */
	public void setEstadoSimpleTogglePanel(Boolean estadoSimpleTogglePanel) {
		this.estadoSimpleTogglePanel = estadoSimpleTogglePanel;
	}
	
	/**
	 * Retorna o valor armazenado no atributo menuAcoes.
	 * @param grid - HtmlPanelGrid
	 */
	public void setListaAcoes(HtmlPanelGrid grid) {}
	
}


