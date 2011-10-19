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
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.paginacao <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */

public class PaginacaoInfo extends Oab implements Serializable {

	private static final long serialVersionUID = -7299207225582964417L;

	public static int	 				LINHAS_POR_PAGINA_DEFAULT 	= 10;
	
	//constantes utilizadas para realizar a páginação
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
	private static final String  		FAST_REWIND					= "fastrewind"; //utilizado na paginação do RichFaces
	private static final String  		FAST_FORWARD				= "fastforward"; //utilizado na paginação do RichFaces
	private static int			 		flag 						= 1;

	private  OrderType		 			orderType 					= OrderType.DESC;
	
	private  String		 				orderBy = null;
	private  Integer			 		start;
	private  int			 			qtdItens;
	private  String				 		rotuloDataScroller;
	private  HtmlDatascroller	 		richScroll;
	private  HttpSession				context;

	/**
	 * Construtor que recebe um SESSION utilizado para armazenar os parametros de paginação 
	 * e a entidade que esta sendo paginada utilizado para manter o estado da paginação.
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
	 * da página apresentada para o usuário (página atual, qtd de registros por página, etc) e o objeto
	 * session utilizada para persistir temporariamente as informações da paginação, e a entidade que está
	 * sendo paginada utilizado para manter a paginação durante a navegação.
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
	 * da página apresentada para o usuário (página atual, qtd de registros por página, etc) e o objeto
	 * session utilizada para persistir temporariamente as informações da paginação.
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
	 * Inicializa os obejtos para a construção da paginacao
	 * @param event - ActionEvent
	 */
	public void inicializar(ActionEvent event){
		try{
			if(event instanceof DataScrollerEvent){
				//Paginação do RichFaces
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
	 * para que a paginação seja realizada corretamente.
	 * 
	 * @param start - Integer
	 */
	private Object getParamSession(String key){
		return this.getContext().getAttribute(key);
	}
	
	
	/**
	 * Altera o objeto que indica o item inicial armazenado na session
	 * para que a paginação seja realizada corretamente.
	 * 
	 * @param start - Integer
	 */
	private void setParamSession(Object start, String key){
		this.getContext().setAttribute(key, start);
	}
	
	/**
	 * Constroi os parâmetros utilizados na ordenção das consultar pelo RICH-FACES
	 * @param component - HtmlCommandLink
	 */
	private void inicializarVariaveisOrdenacao(HtmlAjaxCommandLink component){
		//se existir parametro de ordenação
		if(component != null){
			this.setOrderBy(component.getTitle());
		}else{
			this.setOrderBy(null);			
		}
		//se o usuário clicar 2X na mesma coluna a ordenação passa a ser DECRESCENTE
		if(orderBy != null && orderBy.equals(this.orderBy) && flag == 2){
			this.setOrderType(OrderType.DESC);
			flag = 1;
		}else{
			this.setOrderType(OrderType.ASC);
			flag = 2;
		}
	}
	
	/**
	 * Altera o valor da varíavel START
	 * @param start
	 */
	private void setStart(int start){
		setParamSession(start, KEY_START_SESSION);
		this.start = start;
	}
	
	/**
	 * Constroi os parâmetros do objeto utilizados na paginação das consultas com o Scroller do RicheFaces.
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
	 * Insere a variável que representa a página atual na paginação
	 */
	private void construirPaginaAtual(){
		setPaginaAtual(Math.abs(getStart() / getLinhasPorPagina()));
	}
	
	
	/**
	 * Retorna o valor armazenado na variável PaginacaoInfo.java.
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
	 * Retorna o valor armazenado na variável PaginacaoInfo.java.
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
	 * Reseta o mecanismo de paginação voltando o SCROLLER para a página 0
	 * @return void
	 */
	private void resetarPaginacao(){
		this.setStart(0);
		this.setPaginaAtual(0);
		setParamSession(0, KEY_START_SESSION);
	}

	/**
	 * Reseta o mecanismo de paginação voltando o SCROLLER para a página 0
	 * @return void
	 */
	private void recuperarPaginacao(){
		this.setStart(getStart());
		this.setPaginaAtual(getPaginaAtual());
		setParamSession(getStart(), KEY_START_SESSION);
	}

	/**
	 * Retorna o valor armazenado na variável orderBy.
	 * @return String
	 */
	public String getOrderBy() {
		if(getParamSession(KEY_ORDER_BY_SESSION) != null)
			return getParamSession(KEY_ORDER_BY_SESSION).toString();
		else
			return null;
	}

	/**
	 * Retorna o valor armazenado na variável crescenteDecrescente.
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
	 * Retorna o valor armazenado na variável qtdItens.
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
	 * Retorna o valor armazenado na variável PaginacaoInfo.java.
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
	 * Retorna o valor armazenado na variável paginaAtual.
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
	 * Retorna a quantidade máxima de registros apresentados por página escolhida pelo usuário.
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
	 * Retorna o valor armazenado na variável context.
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
	 * Verifica se a chamada ao objeto de paginação foi realizado por um
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
			//exceção que indica que a chamada
			//não foi realizada de uma instancia de DATAEASY
		}
		return false;
	}
	
	/**
	 * Verifica se a paginação deverá ser reinicializada.
	 * @param entidade - Class<? extends TransferObject>
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	private boolean resetarPaginacao(Class<? extends TransferObject> entidade){
		//busca a entidade armazenada na sessão
		Class<? extends TransferObject> entidadeSession = (Class)getParamSession(KEY_ENTIDADE_ATUAL_PAGINACAO);
		//se não existir entidade na SESSÃO reseta a paginação
		if(entidadeSession == null)
			return true;
		//se as entidades forem diferentes a paginação também deverá ser reiniciada
		if(!entidadeSession.getName().equals(entidade.getName()))
			return true;
		return false;
	}
	
}
