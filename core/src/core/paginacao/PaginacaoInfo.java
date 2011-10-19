package core.paginacao;

import java.io.Serializable;

import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import org.ajax4jsf.component.html.HtmlAjaxCommandLink;
import org.richfaces.component.html.HtmlDatascroller;
import org.richfaces.event.DataScrollerEvent;

import core.Oab;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.resource.ResourceServiceUtil;
import core.utilitario.Numero;


/**
 * <b>Classe:</b> PaginacaoInfo.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.paginacao <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */

public class PaginacaoInfo extends Oab implements Serializable {

	private static final long serialVersionUID = -7299207225582964417L;

	public static int	 				LINHAS_POR_PAGINA_DEFAULT 	= 10;
	
	//constantes utilizadas para realizar a p�gina��o
	private static final String			KEY_QTD_REGISTROS_SESSION   	= "#{KEY_QTD_REGISTROS_SESSION_LIST_INFO}";
	private static final String			KEY_START_SESSION   			= "#{KEY_START_SESSION_LIST_INFO}";
	private static final String			KEY_ORDER_BY_SESSION   			= "#{KEY_LAST_ORDER_BY_SESSION_LIST_INFO}";
	private static final String			KEY_ORDER_TYPE_BY_SESSION		= "#{KEY_LAST_ORDER_TYPE_SESSION_LIST_INFO}";
	private static final String			KEY_QTD_REGISTRO_POR_PAGINA		= "#{KEY_QTD_REGISTRO_POR_PAGINA_LIST_INFO}";
	private static final String			KEY_PAGINA_ATUAL_PAGINACAO		= "#{KEY_PAGINA_ATUAL_PAGINACAO_LIST_INFO}";
	private static final String			KEY_ENTIDADE_ATUAL_PAGINACAO	= "#{KEY_ENTIDADE_ATUAL_PAGINACAO_LIST_INFO}";
	
	private static final String	 		FIRST						= "first";
	private static final String	 		NEXT						= "next";
	private static final String	 		PREVIOUS					= "previous";
	private static final String	 		LAST						= "last";
	private static final String  		FAST_REWIND					= "fastrewind"; //utilizado na pagina��o do RichFaces
	private static final String  		FAST_FORWARD				= "fastforward"; //utilizado na pagina��o do RichFaces
	private static int			 		flag 						= 1;

	private  OrderType		 			orderType 					= OrderType.DESC;
	
	private  String		 				orderBy = null;
	private  Integer			 		start;
	private  int			 			qtdItens;
	private  String				 		rotuloDataScroller;
	private  HtmlDatascroller	 		richScroll;
	private  HttpSession				context;

	/**
	 * Construtor que recebe um SESSION utilizado para armazenar os parametros de pagina��o 
	 * e a entidade que esta sendo paginada utilizado para manter o estado da pagina��o.
	 * 
	 * @param session - HttpSession
	 * @param entidade - Class<? extends TransferObject>
	 */
	public PaginacaoInfo(HttpSession session, Class<? extends TransferObject> entidade) {
		this.context = session;
		if(entidade != null){
			if(validarChamadaPaginacao() && resetarPaginacao(entidade)){
				resetarPaginacao();
			}else{
				recuperarPaginacao();
			}
			setParamSession(entidade, KEY_ENTIDADE_ATUAL_PAGINACAO);
		}
	}
	
	/**
	 * Construtor que recebe um objeto ActionEvent que representa o componente JSF contendo o estado atual 
	 * da p�gina apresentada para o usu�rio (p�gina atual, qtd de registros por p�gina, etc) e o objeto
	 * session utilizada para persistir temporariamente as informa��es da pagina��o, e a entidade que est�
	 * sendo paginada utilizado para manter a pagina��o durante a navega��o.
	 * 
	 * @param event - ActionEvent
	 * @param session - HttpSession
	 * @param entidade - Class<? extends TransferObject>
	 */
	public PaginacaoInfo(ActionEvent event, HttpSession session, Class<? extends TransferObject> entidade) {
		this.context = session;
		this.inicializar(event);
		//altera a entidade que esta sendo paginada
		setParamSession(entidade, KEY_ENTIDADE_ATUAL_PAGINACAO);
	}

	/**
	 * Construtor que recebe um objeto ActionEvent que representa o componente JSF contendo o estado atual 
	 * da p�gina apresentada para o usu�rio (p�gina atual, qtd de registros por p�gina, etc) e o objeto
	 * session utilizada para persistir temporariamente as informa��es da pagina��o.
	 * 
	 * @param event - ActionEvent
	 * @param session - HttpSession
	 */
	public PaginacaoInfo(ActionEvent event, HttpSession session) {
		this.context = session;
		this.inicializar(event);
	}
	
	/**
	 * Construtor simples. Reseta o estado da paginacao caso seja informado um parametro TRUE.
	 * 
	 * Construtor da classe PaginacaoInfo.java
	 * @param resetarEstado - boolean
	 */
	public PaginacaoInfo(HttpSession session, boolean resetarEstado) {
		this.context = session;
		if(resetarEstado){
			this.resetarPaginacao();
		}
	}
	
	/**
	 * Inicializa os obejtos para a constru��o da paginacao
	 * @param event - ActionEvent
	 */
	public void inicializar(ActionEvent event){
		try{
			if(event instanceof DataScrollerEvent){
				//Pagina��o do RichFaces
				inicializarVariaveisPaginacao((DataScrollerEvent)event);
				this.construirPaginaAtual();
			}else if(event.getComponent() != null && event.getComponent() instanceof HtmlAjaxCommandLink){
				//ordena a listagem pelo RICH-FACES
				inicializarVariaveisOrdenacao((HtmlAjaxCommandLink)event.getComponent());								
				this.construirPaginaAtual();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Captura o objeto que indica o item inicial armazenado na session
	 * para que a pagina��o seja realizada corretamente.
	 * 
	 * @param start - Integer
	 */
	private Object getParamSession(String key){
		return this.getContext().getAttribute(key);
	}
	
	
	/**
	 * Altera o objeto que indica o item inicial armazenado na session
	 * para que a pagina��o seja realizada corretamente.
	 * 
	 * @param start - Integer
	 */
	private void setParamSession(Object start, String key){
		this.getContext().setAttribute(key, start);
	}
	
	/**
	 * Constroi os par�metros utilizados na orden��o das consultar pelo RICH-FACES
	 * @param component - HtmlCommandLink
	 */
	private void inicializarVariaveisOrdenacao(HtmlAjaxCommandLink component){
		//se existir parametro de ordena��o
		if(component != null){
			this.setOrderBy(component.getTitle());
		}else{
			this.setOrderBy(null);			
		}
		//se o usu�rio clicar 2X na mesma coluna a ordena��o passa a ser DECRESCENTE
		if(orderBy != null && orderBy.equals(this.orderBy) && flag == 2){
			this.setOrderType(OrderType.DESC);
			flag = 1;
		}else{
			this.setOrderType(OrderType.ASC);
			flag = 2;
		}
	}
	
	/**
	 * Altera o valor da var�avel START
	 * @param start
	 */
	private void setStart(int start){
		setParamSession(start, KEY_START_SESSION);
		this.start = start;
	}
	
	/**
	 * Constroi os par�metros do objeto utilizados na pagina��o das consultas com o Scroller do RicheFaces.
	 * @param richEvent - DataScrollerEvent
	 */
	private void inicializarVariaveisPaginacao(DataScrollerEvent richEvent){
		String paginator = richEvent.getNewScrolVal();
		if(Numero.isNumeric(paginator)){
			setStart((Integer.valueOf(paginator).intValue() * getLinhasPorPagina()) - getLinhasPorPagina());
		}else if(paginator.equals(FIRST)){
			setStart(0);
		}else if(paginator.equals(LAST)){
			int qtdTotalItens = (Integer)getParamSession(KEY_QTD_REGISTROS_SESSION);
			setStart((qtdTotalItens / getLinhasPorPagina()) * getLinhasPorPagina());
		}else if(paginator.equals(FAST_REWIND) || paginator.equals(PREVIOUS)){
			if(getStart() > 0){
				setStart(((Integer.parseInt(richEvent.getOldScrolVal())-1) * getLinhasPorPagina()) - getLinhasPorPagina());
			}else{
				setStart(0);
			}
		}else if(paginator.equals(FAST_FORWARD) || paginator.equals(NEXT)){
			if(getStart() == 0){
				setStart(getLinhasPorPagina());
			}else{
				setStart((Integer.parseInt(richEvent.getOldScrolVal()) * getLinhasPorPagina())); 
			}
		}
	}
	
	/**
	 * Insere a vari�vel que representa a p�gina atual na pagina��o
	 */
	private void construirPaginaAtual(){
		setPaginaAtual(Math.abs(getStart() / getLinhasPorPagina()));
	}
	
	
	/**
	 * Retorna o valor armazenado na vari�vel PaginacaoInfo.java.
	 * @return Integer
	 */
	public Integer getStart() {
		if(start == null && getParamSession(KEY_START_SESSION) != null){
			start = (Integer)getParamSession(KEY_START_SESSION);
		}else if(start == null && getParamSession(KEY_START_SESSION) == null){
			start = 0;
		}
		return start; 
	}

	/**
	 * Retorna o valor armazenado na vari�vel PaginacaoInfo.java.
	 * @return String
	 */
	public String getRotuloDataScroller() {
		if(rotuloDataScroller == null || rotuloDataScroller.equals(""))
			rotuloDataScroller = getRotuloDefault();
		return rotuloDataScroller;
	}
	
	/**
	 * Retorna a string "registro(s)" correspondem a sua pesquisa"
	 * @return String
	 */
	public String getRotuloFiltro(){
		try{
			return ResourceServiceUtil.getMessageResourceString("satiRegistrosFiltro", null);
		}catch(Exception e){
			return null;
		}

	}
	
	/**
	 * Retorna a String "registro(s)"
	 * @return String
	 */
	public String getRotuloDefault(){
		return ResourceServiceUtil.getMessageResourceString("satiRegistrosEncontrados", null);
	}

	/**
	 * Insere um valor ao atributo PaginacaoInfo.java
	 * @param rotuloDataScroller - String
	 * 
	 */
	public void setRotuloDataScroller(String rotuloDataScroller) {
		this.rotuloDataScroller = rotuloDataScroller;
	}
	
	/**
	 * Reseta o mecanismo de pagina��o voltando o SCROLLER para a p�gina 0
	 * @return void
	 */
	private void resetarPaginacao(){
		this.setStart(0);
		this.setPaginaAtual(0);
		setParamSession(0, KEY_START_SESSION);
	}

	/**
	 * Reseta o mecanismo de pagina��o voltando o SCROLLER para a p�gina 0
	 * @return void
	 */
	private void recuperarPaginacao(){
		this.setStart(getStart());
		this.setPaginaAtual(getPaginaAtual());
		setParamSession(getStart(), KEY_START_SESSION);
	}

	/**
	 * Retorna o valor armazenado na vari�vel orderBy.
	 * @return String
	 */
	public String getOrderBy() {
		if(getParamSession(KEY_ORDER_BY_SESSION) != null)
			return getParamSession(KEY_ORDER_BY_SESSION).toString();
		else
			return null;
	}

	/**
	 * Retorna o valor armazenado na vari�vel crescenteDecrescente.
	 * @return OrderType
	 */
	public OrderType getOrderType() {
		if(getParamSession(KEY_ORDER_TYPE_BY_SESSION) != null)
			return (OrderType)getParamSession(KEY_ORDER_TYPE_BY_SESSION);
		else
			return orderType;
	}

	/**
	 * Insere um valor ao atributo orderBy
	 * @param orderBy - String
	 * 
	 */
	public void setOrderBy(String orderBy) {
		setParamSession(orderBy, KEY_ORDER_BY_SESSION);
		this.orderBy = orderBy;
	}
	/**
	 * Insere um valor ao atributo crescenteDecrescente
	 * @param crescenteDecrescente - int
	 * 
	 */
	public void setOrderType(OrderType order) {
		setParamSession(order, KEY_ORDER_TYPE_BY_SESSION);
		this.orderType = order;
	}



	/**
	 * Retorna o valor armazenado na vari�vel qtdItens.
	 * @return int
	 */
	public int getQtdItens() {
		return qtdItens;			
	}

	/**
	 * Insere um valor ao atributo qtdItens
	 * @param qtdItens - int
	 * 
	 */
	public void setQtdItens(int qtdItens) {
		setParamSession(qtdItens, KEY_QTD_REGISTROS_SESSION);
		this.qtdItens = qtdItens;
	}

	/**
	 * Retorna o valor armazenado na vari�vel PaginacaoInfo.java.
	 * @return HtmlDatascroller
	 */
	public HtmlDatascroller getRichScroll() {
		if(richScroll == null){
			richScroll = new HtmlDatascroller();
			richScroll.setPage(getPaginaAtual()+1);
		}
		return richScroll;
	}

	/**
	 * Insere um valor ao atributo PaginacaoInfo.java
	 * @param richScroll - HtmlDatascroller
	 * 
	 */
	public void setRichScroll(HtmlDatascroller richScroll) {
		this.richScroll = richScroll;
	}

	/**
	 * Retorna o valor armazenado na vari�vel paginaAtual.
	 * @return int
	 */
	public int getPaginaAtual() {
		if((Integer)getParamSession(KEY_PAGINA_ATUAL_PAGINACAO) != null)
			return (Integer)getParamSession(KEY_PAGINA_ATUAL_PAGINACAO);
		else
			return 0;
	}

	/**
	 * Insere um valor ao atributo paginaAtual
	 * @param paginaAtual - int
	 */
	private void setPaginaAtual(int paginaAtual) {
		setParamSession(Integer.valueOf(paginaAtual), KEY_PAGINA_ATUAL_PAGINACAO);
	}
	
	/**
	 * Retorna a quantidade m�xima de registros apresentados por p�gina escolhida pelo usu�rio.
	 * @return int
	 */
	public int getLinhasPorPagina(){
		if(getParamSession(KEY_QTD_REGISTRO_POR_PAGINA) != null){
			return (Integer)getParamSession(KEY_QTD_REGISTRO_POR_PAGINA);
		}else{
			setParamSession(Integer.valueOf(LINHAS_POR_PAGINA_DEFAULT), KEY_QTD_REGISTRO_POR_PAGINA);
			return (Integer)getParamSession(KEY_QTD_REGISTRO_POR_PAGINA);
		}
	}

	/**
	 * Retorna o valor armazenado na vari�vel context.
	 * @return HttpSession
	 */
	public HttpSession getContext() {
		return context;
	}

	/**
	 * Insere um valor ao atributo context
	 * @param context - HttpSession
	 * 
	 */
	public void setContext(HttpSession context) {
		this.context = context;
	}

	/**
	 * Insere um valor ao atributo linhasPorPagina
	 *
	 * @param linhasPorPagina - int
	 */
	public void setLinhasPorPagina(int linhasPorPagina) {
		Integer itens = (Integer)getParamSession(KEY_QTD_REGISTRO_POR_PAGINA);
		if(itens != null){
			if(itens != linhasPorPagina){
				setParamSession(Integer.valueOf(linhasPorPagina), KEY_QTD_REGISTRO_POR_PAGINA);				
			}
		}else{
			setParamSession(Integer.valueOf(linhasPorPagina), KEY_QTD_REGISTRO_POR_PAGINA);
		}
	}
	

	/**
	 * Verifica se a chamada ao objeto de pagina��o foi realizado por um
	 * objeto que herda do framework DATAEASY.
	 * @return boolean
	 */
	private boolean validarChamadaPaginacao(){
		Throwable throwble = new Throwable();
		String caller = throwble.getStackTrace()[2].getClassName();
		try{
			if(Oab.class.isAssignableFrom(Class.forName(caller))){
				return true;				
			}
		}catch (ClassNotFoundException e) {
			//exce��o que indica que a chamada
			//n�o foi realizada de uma instancia de DATAEASY
		}
		return false;
	}
	
	/**
	 * Verifica se a pagina��o dever� ser reinicializada.
	 * @param entidade - Class<? extends TransferObject>
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean resetarPaginacao(Class<? extends TransferObject> entidade){
		//busca a entidade armazenada na sess�o
		Class<? extends TransferObject> entidadeSession = (Class)getParamSession(KEY_ENTIDADE_ATUAL_PAGINACAO);
		//se n�o existir entidade na SESS�O reseta a pagina��o
		if(entidadeSession == null)
			return true;
		//se as entidades forem diferentes a pagina��o tamb�m dever� ser reiniciada
		if(!entidadeSession.getName().equals(entidade.getName()))
			return true;
		return false;
	}
	
}
