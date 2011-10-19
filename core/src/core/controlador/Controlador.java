package core.controlador;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import core.Oab;
import core.dao.TransferObject;
import core.jsf.FacesUtil;
import core.paginacao.PaginacaoInfo;
import core.resource.ResourceServiceUtil;
import core.security.CredencialProvider;
import core.security.SecurityProvider;
import core.security.SecurityProviderRegistry;


/**
 * <b>Classe:</b> Controlador.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.controlador <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leoanrdo Peixoto
 * @version Revision: $ Date: $
 */
public abstract class Controlador extends Oab implements Serializable{

	private static final long serialVersionUID = 6195267542782188238L;
	private 	Application					application;
	protected 	PaginacaoInfo				info;
	
	
	/**
	 * Retorna o valor armazenado na variável ManagedBean.java.
	 * 
	 * @return FacesContext
	 */
	protected FacesContext getContext() {
		return FacesContext.getCurrentInstance();
	}

	/**
	 * Retorna o valor armazenado na variável Controller.java.
	 * @return Application
	 */
	protected Application getApplication() {
		if(application == null)
			application = getContext().getApplication();
		return application;
	}

	/**
	 * Insere um valor ao atributo Controller.java
	 * @param application - Application
	 * 
	 */
	public void setApplication(Application application) {
		this.application = application;
	}
	
	/**
	 * Registra os objetos responsáveis por controlar a segurança na aplicação.
	 * @param security - SecurityProvider
	 * @param usuarioLogado - ColaboradorTO
	 */
	protected void securityRegister(SecurityProvider security, TransferObject bean){
		SecurityProviderRegistry.registerSecurityProvider(security);
		CredencialProvider.setCredencial(bean);
	}
	
	/**
	 * Adiciona um array de bytes no response.
	 * @param bytes - byte[]
	 * @throws IOException
	 */
	protected void setResponseWriter(byte[] bytes) throws IOException{
		FacesUtil.setResponseWriter(this.getContext(), bytes);
	}
	
	/**
	 * Adiciona um array de bytes no response.
	 * 
	 * @param bytes - byte[]
	 * @param contentType - String
	 * 
	 * @throws IOException
	 */
	protected void setResponseWriter(byte[] bytes, String contentType) throws IOException{
		FacesUtil.setResponseWriter(this.getContext(), bytes, contentType);
	}
	
	/**
	 * Adiciona um array de bytes no response.
	 * 
	 * @param bytes - byte[]
	 * @param contentType - String
	 * 
	 * @throws IOException
	 */
	protected void setResponseWriter(byte[] bytes, String contentType , String nome) throws IOException{
		FacesUtil.setResponseWriter(this.getContext(), bytes, contentType, nome);
	}
	
	/**
	 * Adiciona parametros na sessão do usuário.
	 * @param alias - String
	 * @param parameter - Object
	 */
	protected void setParamSession(String alias, Object parameter) {
		FacesUtil.setParamSession(this.getContext(), alias, parameter);
	}
	
	/**
	 * Cria uma expressão para de retorno para um objeto no JAVA.
	 *
	 * @param ctx - {@link FacesContext}
	 * @param expressao - {@link String}
	 * @param tipoRetorno - {@link Class}
	 * @param argumentos - {@link Class}
	 * @return {@link MethodExpression}
	 * 
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException - 
	 * @return MethodExpression
	 */
	@SuppressWarnings("unchecked")
	public MethodExpression createMethodExpression(String expressao, Class tipoRetorno, Class[] argumentos) throws Exception {
		return getContext().getApplication().getExpressionFactory().createMethodExpression(getContext().getELContext(), expressao, tipoRetorno, argumentos);
	} 

	/**
	 * Cria uma expressão para de retorno para um objeto no JAVA.
	 *
	 * @param ctx - {@link FacesContext}
	 * @param expressao - {@link String}
	 * @param tipoRetorno - {@link Class}
	 * @return {@link ValueExpression}
	 * 
	 * @return Exception
	 */
	@SuppressWarnings("unchecked")
	public ValueExpression getValueExpression(String expressao, Class tipoRetorno) throws Exception {
		return FacesUtil.getValueExpression(getContext(), expressao, tipoRetorno);
	} 
	
	/**
	 * Destroi a sessão corrente do usuário, se este método for utilizado, o usuário
	 * atual e desligado da aplicação.
	 */
	protected void sessionDestroy(){
		FacesUtil.sessionDestroy(this.getContext());
	}
	
	/**
	 * Retorna um parâmetro armazenado na Sessão
	 * @param alias - String
	 * @return Object
	 */
	protected Object getParamSession(String alias){
		return FacesUtil.getParamSession(this.getContext(), alias); 
	}

	/**
	 * Retorna um parâmetro armazenado no Request
	 * @param alias - String
	 * @return Object
	 */
	protected Object getParamRequest(String alias){
		return FacesUtil.getParamRequest(this.getContext(), alias); 
	}
	/**
	 * Retorna o parametro passado pela URL
	 * @param alias - String
	 * @return String
	 */
	protected String getParamUrl(String alias){
		return FacesUtil.getParamUrl(this.getContext(), alias);
	}
	
	/**
	 * Remove um atributo da Sessão.
	 * @param alias - String
	 */
	protected void removeParamSession(String alias){
		FacesUtil.removeParamSession(this.getContext(), alias);
	}

	/**
	 * Insere um parâmetro no request.
	 * @param alias - String
	 * @param parameter - Object
	 */
	protected void setParamRequest(String alias, Object parameter) {
		FacesUtil.setParamRequest(this.getContext(), alias, parameter);
	}
	
	/**
	 * Retorna o texto do arquivo de internacionalização a partir da chave.
	 * 
	 * @param key - String
	 * @return String
	 */
	protected String getMessage(String key) {
		return ResourceServiceUtil.getMessageResourceString(key, null);
	}

	/**
	 * Retorna o texto do arquivo de internacionalização a partir da chave.
	 * @param key - String
	 * @return String
	 */
	protected String getMessage(String key, Object[] param) {
		return ResourceServiceUtil.getMessageResourceString(key, param);
	}
	
	/**
	 * Retorna o objeto Request atual
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest getRequest(){
		return (HttpServletRequest)FacesUtil.getRequest(this.getContext());
	}

	/**
	 * Retorna o objeto Response atual
	 * @return HttpServletResponse
	 */
	protected HttpServletResponse getResponse(){
		return (HttpServletResponse)FacesUtil.getResponse(this.getContext());
	}

	/**
	 * Retorna o objeto Session atual
	 * @return HttpSession
	 */
	protected HttpSession getSession(){
		return (HttpSession)FacesUtil.getSession(this.getContext());
	}

	/**
	 * Retorna o objeto Session atual
	 * @return HttpSession
	 */
	protected HttpSession getSession(boolean atributo){
		return (HttpSession)FacesUtil.getSession(this.getContext(), atributo);
	}

	/**
	 * Dispara um foward no FacesContext
	 * @param viewId - String
	 */
	protected void forward(String viewId){
		FacesUtil.foward(getContext(), getApplication().getViewHandler(), viewId);
	}
	
	/**
	 * Realiza uma navegacao JSF - Forward
	 * 
	 * @param target - String de Navegacao definida no arquivo navigation-rules.xml
	 * @return void
	 */
	protected void handleNavigation(String target){
		FacesUtil.handleNavigation(getContext(), target);
	}

	/**
	 * Redireciona para a URL especificada
	 * @param url - String
	 */
	protected void redirect(String url) throws IOException{
		FacesUtil.redirect(getContext(), url);
	}
	
	/**
	 * Retorna o valor de um determinado Key do arquivo de internacionalização
	 * @param key - String
	 * @return String
	 */
	protected String getTextMessageRessource(String key){
		return this.getTextMessageRessource(key, null);
	}

	/**
	 * Retorna o valor de um determinado Key do arquivo de internacionalização
	 * @param key - String
	 * @param param - Object[]
	 * @return String
	 */
	protected String getTextMessageRessource(String key, Object[] param){
		return ResourceServiceUtil.getMessageResourceString(key, param);
	}
	
	/**
	 * Retorna a lista de conversores controlada pela App
	 * @return Iterator<String>
	 */
	protected Iterator<String> getConverterIds(){
		return FacesUtil.getConverterIds(this.getContext());
	}
	
	/**
	 * Retorna a lista de validadores controlada pela App
	 * @return Iterator<String>
	 */
	protected Iterator<String> getValidatorIds(){
		return FacesUtil.getValidatorIds(this.getContext());
	}
	
	/**
	 * Retorna um parâmetro passado pela View.
	 * 
	 * @param event - ActinEvent
	 * @param key - String
	 * @return ManagedBean
	 */
	protected Object getParamView(ActionEvent event, String key){
		return FacesUtil.getParamView(event, key);
	}

	
	/**
	 * Retorna a instância de um ManagedBean gerênciado pelo JSF
	 * @param alias - String
	 * @return ManagedBean
	 */
	protected Controlador getManagedBean(String alias){
		return (Controlador)FacesUtil.getManagedBean(this.getContext(), alias);
	}
	/**
	 * Retorna a instancia do compenente de paginação.
	 * @param entidade - Class<? extends TransferObject>
	 * @return PaginacaoInfo
	 */
	public PaginacaoInfo getInfo(){
		info = new PaginacaoInfo(FacesUtil.getSession(this.getContext()), null);
		return info;
	}

	/**
	 * Retorna a instancia do compenente de paginação.
	 * @param entidade - Class<? extends TransferObject>
	 * @return PaginacaoInfo
	 */
	public PaginacaoInfo getInfo(Class<? extends TransferObject> entidade){
		info = new PaginacaoInfo(FacesUtil.getSession(this.getContext()), entidade);
		return info;
	}

	/**
	 * Retorna a instancia do compenente de paginação.
	 * @param event - ActionEvent
	 * @param entidade - Class<? extends TransferObject>
	 * @return PaginacaoInfo
	 */
	public PaginacaoInfo getInfo(ActionEvent event, Class<? extends TransferObject> entidade) {
		info = new PaginacaoInfo(event, FacesUtil.getSession(this.getContext()), entidade);
		return info;
	}

	/**
	 * Retorna a instancia do compenente de paginação.
	 * @param event - ActionEvent
	 * @return PaginacaoInfo
	 */
	public PaginacaoInfo getInfo(ActionEvent event) {
		info = new PaginacaoInfo(event, FacesUtil.getSession(this.getContext()));
		return info;
	}
	
	/**
	 * Retorna a instancia do compenente de paginação.
	 * @param resetarEstado - boolean
	 * @return PaginacaoInfo
	 */
	public PaginacaoInfo getInfo(boolean resetarEstado) {
		info = new PaginacaoInfo(FacesUtil.getSession(this.getContext()), resetarEstado);
		return info;
	}
	

	/**
	 * Insere um valor ao atributo ManagedBean.java
	 * @param info - PaginacaoInfo
	 */
	public void setInfo(PaginacaoInfo info) {
		this.info = info;
	}
}
