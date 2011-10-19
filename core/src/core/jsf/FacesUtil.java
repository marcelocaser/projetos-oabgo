package core.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIParameter;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <b>Classe:</b> FacesUtil.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.jsf <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class FacesUtil{
	
	/**
	 * Destroi a sessão corrente do usuario.
	 * @param ctx - FacesContext
	 */
	public static void sessionDestroy(FacesContext ctx){
		getSession(ctx).invalidate();
	}
	
	/**
	 * Retorna o objeto HTTPSession.
	 * @param ctx - FacesContext
	 * @return HttpSession
	 */
	public static HttpSession getSession(FacesContext ctx) {
		return (HttpSession) ctx.getExternalContext().getSession(true);
	}

	/**
	 * Retorna o objeto HTTPSession.
	 * @param ctx - FacesContext
	 * @return HttpSession
	 */
	public static HttpSession getSession(FacesContext ctx, boolean parametroSession) {
		return (HttpSession) ctx.getExternalContext().getSession(parametroSession);
	}
	
	/**
	 * Retorna o Objeto HTTPServletRequest.
	 * @param ctx - FacesContext
	 * @return HttpServletRequest
	 */
	public static HttpServletRequest getRequest(FacesContext ctx) {
		return (HttpServletRequest) ctx.getExternalContext().getRequest();
	}

	/**
	 * Retorna o caminho real da aplicação
	 * @param ctx - FacesContext
	 * @return String
	 */
	public static String getRealPath(FacesContext ctx) {
		return getServletContext(ctx).getRealPath("");
	}

	/**
	 * Retorna o Objeto HTTPServletRequest.
	 * @param ctx - FacesContext
	 * @return HttpServletRequest
	 */
	public static HttpServletResponse getResponse(FacesContext ctx) {
		return (HttpServletResponse)ctx.getExternalContext().getResponse();
	}
	
	/**
	 * Realiza uma navegacao JSF - Forward
	 * 
	 * @param ctx - FacesContext
	 * @param target - String de Navegacao definida no arquivo navigation-rules.xml
	 * @return void
	 */
	public static void handleNavigation(FacesContext ctx, String target){
		NavigationHandler handler = ctx.getApplication().getNavigationHandler();
		handler.handleNavigation(ctx, null, target);
	}

	/**
	 * Dispara um Foward no FacesContext
	 * @param ctx - FacesContext
	 * @param handler - ViewHandler
	 * @param viewId - String
	 */
	public static void foward(FacesContext ctx, ViewHandler handler, String viewId){
		UIViewRoot view = handler.createView(ctx, viewId);
	    ctx.setViewRoot(view);
	    ctx.renderResponse();
	}
	
	/**
	 * Recupera a lista de conversores controlada pela app
	 * 
	 * @param ctx - FacesContext
	 * @return Iterator<String>
	 */
	public static Iterator<String> getConverterIds(FacesContext ctx){
		return ctx.getApplication().getConverterIds();
	}
	
	/**
	 * Recupera a lista de validadores controlada pela app
	 * 
	 * @param ctx - FacesContext
	 * @return Iterator<String>
	 */
	public static Iterator<String> getValidatorIds(FacesContext ctx){
		return ctx.getApplication().getValidatorIds();
	}
	
	/**
	 * Redireciona para a página de url passada como parâmetro
	 * 
	 * @param ctx - FacesContext
	 * @param handler - ViewHandler
	 * @param viewId - String
	 */
	public static void redirect(FacesContext ctx, String url) throws IOException{
		try{
			ctx.getExternalContext().redirect(url);
		}catch (IllegalStateException e) {
			
		}
	}
	
	/**
	 * Adiciona parametros na sessão do usuário.
	 * @param ctx - FacesContext
	 * @param alias - String
	 * @param parameter - Object
	 */
	public static void setParamSession(FacesContext ctx, String alias, Object parameter) {
		getSession(ctx).setAttribute(alias, parameter);
	}

	/**
	 * Adiciona parametros na sessão do usuário.
	 * @param httpSession - HttpSession
	 * @param alias - String
	 * @param parameter - Object
	 */
	public static void setParamSession(HttpSession httpSession, String alias, Object parameter) {
		httpSession.setAttribute(alias, parameter);
	}

	/**
	 * Remove um atributo da SESSÃO.
	 * @param ctx - FacesContext
	 * @param alias - String
	 */
	public static void removeParamSession(FacesContext ctx, String alias) {
		getSession(ctx).removeAttribute(alias);
	}

	/**
	 * Insere um parâmetro no request.
	 * @param alias - String
	 * @param parameter - Object
	 */
	public static void setParamRequest(FacesContext ctx, String alias, Object parameter) {
		getRequest(ctx).setAttribute(alias, parameter);
	}
	
	/**
	 * Retorna o parametro armazenado no session.
	 * @param ctx - FacesContext
	 * @param key - String
	 * @return Object
	 */
	public static Object getParamSession(FacesContext ctx, String key){
		return getSession(ctx).getAttribute(key);
	}

	/**
	 * Retorna o parametro armazenado no session.
	 * @param ctx - HttpSession
	 * @param key - String
	 * @return Object
	 */
	public static Object getParamSession(HttpSession ctx, String key){
		return ctx.getAttribute(key);
	}
	
	/**
	 * Constroi um objeto MethodExpression utilizado na chamado a "action" ou "actionListener"
	 *
	 * @param ctx
	 * @param expression
	 * @param methodData
	 * @return
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException - 
	 * @return MethodExpression
	 */
	@SuppressWarnings("unchecked")
	public static MethodExpression createMethodExpression(FacesContext ctx, String expression, Class returnType, Class[] arguments) throws NoSuchMethodException, ClassNotFoundException {
		return ctx.getApplication().getExpressionFactory().createMethodExpression(ctx.getELContext(), expression, returnType, arguments);
	} 

	/**
	 * Constroi um objeto ValueExpression utilizado na chamado a "actionListener"
	 *
	 * @param ctx
	 * @param expression
	 * @param methodData
	 * @return
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException - 
	 * @return MethodExpression
	 */
	@SuppressWarnings("unchecked")
	public static ValueExpression getValueExpression(FacesContext ctx, String expression, Class expectedType) throws NoSuchMethodException, ClassNotFoundException {
		return ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), expression, expectedType);
	} 

	/**
	 * Constroi um objeto MethodExpression utilizado na chamado a "action" ou "actionListener"
	 *
	 * @param ctx
	 * @param expression
	 * @param methodData
	 * @return
	 * @throws NoSuchMethodException
	 * @throws ClassNotFoundException - 
	 * @return MethodExpression
	 */
	@SuppressWarnings({ "deprecation" })
	public static void createValueBinding(FacesContext ctx, String expression, Object value){
		ctx.getApplication().createValueBinding(expression).setValue(ctx, value);
//		ValueExpression expr = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(), expression, classe);
//		expr.getValue(ctx.getELContext());
	} 

	/**
	 * Retorna o parametro armazenado no request.
	 * @param ctx - FacesContext
	 * @param key - String
	 * @return Object
	 */
	public static Object getParamRequest(FacesContext ctx, String key){
		return getRequest(ctx).getAttribute(key);
	}
	
	/**
	 * Retorna o parametro passado pela URL
	 * @param ctx - FacesContext
	 * @param key - String
	 * @return Object
	 */
	public static String getParamUrl(FacesContext ctx, String key){
		return ctx.getExternalContext().getRequestParameterMap().get(key);
	}
	
	/**
	 * Retorna o valor do atributo de um componente
	 * @param event - ActionEvent do JSF
	 * @param nome - Nome do atributo
	 * @return String
	 */
	public static String getActionAtribute(ActionEvent event, String nome){
		return (String)event.getComponent().getAttributes().get(nome);
	}
	
	/**
	 * Retorna um valor inserido no request atraves de atributos Hidden.
	 * 
	 * @param context - FacesContext
	 * @param key - Key do objeto no HashMap
	 * @return Object
	 */
	 public static Object getRequestMapValue(FacesContext context, String key) {
		return context.getExternalContext().getRequestMap().get(key);
	}

	 /**
	  * Insere um atributo no request
	  * 
	  * @param context - FacesContext
	  * @param key - String Key do objeto no request
	  * @param value - Object
	  */
	public static void setRequestMapValue(FacesContext context, String key,	Object value) {
		context.getExternalContext().getRequestMap().put(key, value);
	}
	
	/**
	 * Adiciona uma mensagem no FacesContext
	 * @param ctx - FacesContext
	 * @param msgs - FacesMessage
	 */
	public static void addMessageCtx(FacesContext ctx, FacesMessage msgs){
		ctx.addMessage(null, msgs);
	}
	
	/**
	 * Adiciona um array de bytes no response.
	 * 
	 * @param ctx - FacesContext
	 * @param bytes - byte[]
	 * @param contentType - String - tipo de arquivo a ser renderizado
	 * @throws IOException
	 */
	public static void setResponseWriter(FacesContext ctx, byte[] bytes, String contentType) throws IOException{
		HttpServletResponse response = (HttpServletResponse)ctx.getExternalContext().getResponse();
		response.getOutputStream().write(bytes);
		response.setContentLength(bytes.length);
		if(contentType != null && !contentType.isEmpty())
			response.setContentType(contentType);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
		ctx.responseComplete();
	}
	
	/**
	 * Adiciona um array de bytes no response.
	 * 
	 * @param ctx - FacesContext
	 * @param bytes - byte[]
	 * @param contentType - String - tipo de arquivo a ser renderizado
	 * @throws IOException
	 */
	public static void setResponseWriter(FacesContext ctx, byte[] bytes, String contentType, String nome) throws IOException{
		HttpServletResponse response = (HttpServletResponse)ctx.getExternalContext().getResponse();
		response.getOutputStream().write(bytes);
		response.setContentLength(bytes.length);
		if(contentType != null && !contentType.isEmpty())
			response.setContentType(contentType);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		//Retira os espaços do nome, caso contrario o nome do arquivo sera cortado(Ex.: Nome = "Oficio Docflow.doc", Resultado = "Oficio")
		response.setHeader( "Content-Disposition", "attachment; filename=" + nome.replace(" ", ""));
		ctx.responseComplete();
	}
	
	
	/**
	 * Adiciona um array de bytes no response.
	 * 
	 * @param ctx - FacesContext
	 * @param bytes - byte[]
	 * @throws IOException
	 */
	
	public static void setResponseWriter(FacesContext ctx, byte[] bytes) throws IOException{
		setResponseWriter(ctx, bytes, null);
	}
	
	/**
	 * Retorna o objeto ServletContext da aplicação
	 * @param ctx - FacesContext
	 * @return ServletContext
	 */
	public static ServletContext getServletContext(FacesContext ctx){
		return getRequest(ctx).getSession().getServletContext();
	}
	
	/**
	 * Captura uma classe gerenciada pelo JSF
	 * @param ctx - FacesContext
	 * @param alias - String
	 * @return Object
	 */
	public static Object getManagedBean(FacesContext ctx, String alias){
		return ctx.getELContext().getELResolver().getValue(ctx.getELContext(), null, alias);
	}
	
	/**
	 * Busca um parâmetro passada pela View.
	 * 
	 * @param event - ActionEvent
	 * @param key - String
	 * @return Object
	 */
	public static Object getParamView(ActionEvent event, String key){
		try{
			UIParameter param = (UIParameter) event.getComponent().findComponent(key);
			return  param.getValue();
		}catch (Exception e) {
			return null;
		}
	}
	
}
