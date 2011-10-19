package br.org.oabgo.sati.negocio.controle;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlPanelGrid;

import org.richfaces.component.html.HtmlSimpleTogglePanel;
import org.richfaces.component.html.HtmlToolBar;
import org.springframework.dao.DataIntegrityViolationException;

import br.org.oabgo.sati.negocio.controle.anotacoes.SATIAcoesMenu;
import br.org.oabgo.sati.negocio.controle.entidade.AcessoSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumSecurity;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIAutorizacao;
import br.org.oabgo.sati.web.controle.SATIMenuAcoes;
import br.org.oabgo.sati.web.controle.SATIMenuBarra;
import core.controlador.Controlador;
import core.dao.TransferObject;
import core.excecoes.AcessoException;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.jsf.FacesUtil;
import core.mensagem.Mensagem;
import core.mensagem.MensagemLista;
import core.resource.ResourceServiceUtil;
import core.utilitario.Util;

public class SATIControle extends Controlador implements SATIConstantes {
	
	private static final long serialVersionUID = 1L;
	
	private static final String				OBJETO_MENU_ACOES 	= "OBJETO_MENU_ACOES";
	private static final String				FASE_ACAO 			= "FASE_ACAO";
	private static final String				SISTEMA_ID 			= "SISTEMA_ID";
	private static final String				PAGINA 				= "PAGINA_MENU_ACOES";
	
	private SATIBusinessFactory satiBusinessFactory;

	protected 	HtmlToolBar 				menuBarra;
	protected   HtmlSimpleTogglePanel    	simpleTogglePanel;
	protected   boolean                  	estadoSimpleTogglePanel;
	
	/**
	 * Retorna a instancia de negocio das classes de negocio.
	 * @return SATIBusinessFactory
	 */
	protected SATIBusinessFactory getSATIBusinessFactory(){
		if(satiBusinessFactory == null){
			satiBusinessFactory = SATIBusinessFactory.getInstance();
		}
		return satiBusinessFactory;
	}
	
	/**
	 * Lista os obejtos ordenados por uma propriedade.
	 * @param bean - {@link TransferObject}
	 * @param orderBy - {@link String} - parametro de ordenação.
	 * @throws BDException 
	 * @return TransferObject
	 */
	protected List<TransferObject> listarObjeto(TransferObject bean, String orderBy) throws BDException{
		return getSATIBusinessFactory().createServicoGenerico().listar(bean, orderBy);
	}
	
	/**
	 * Retorna o usuário logado.
	 * @return UsuarioTO
	 */
	public UsuarioTO getUsuarioLogado(){
		return (UsuarioTO)getParamSession(SATIEnumSecurity.USUARIO_LOGADO); 
	}

	/**
	 * Retorna o objeto de acesso.
	 * @return AcessoSistemaTO
	 */
	public AcessoSistemaTO getAcessoSistema(){
		return (AcessoSistemaTO)getParamSession(SATIEnumSecurity.ACESSO); 
	}

	/**
	 * Retorna o sistema logado.
	 * @return SistemaTO
	 */
	public SistemaTO getSistemaLogado(){
		return (SistemaTO)getParamSession(SATIEnumSecurity.SISTEMA_CONTROLADO); 
	}

	/**
	 * Retorna o perfil de acesso utilizado pelo ACS.
	 * @return PerfilAcessoTO
	 */
	public PerfilAcessoTO getPerfilAcessoLogado(){
		return (PerfilAcessoTO)getParamSession(SATIEnumSecurity.PERFIL_ACESSO_LOGADO); 
	}
	
	/**
	 * Insere um parâmetro no request.
	 * @param alias - AdmNetEnumSecurity
	 * @param parameter - Object
	 */
	protected void setParamRequest(SATIEnumSecurity alias, Object parameter) {
		FacesUtil.setParamRequest(this.getContext(), alias.name(), parameter);
	}
	
	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionalização.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, SATIEnumTipoMensagem tipo) {
		setParamRequest(SATIEnumSecurity.SATI_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SATIEnumSecurity.SATI_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key)));
	}

	/**
	 * Dispara mensagem via modal panel no request.
	 *
	 * @param key - {@link String} - Chave de texto existente no arquivo de internacionalização.
	 * @param parametros - {@link String[]} - Lista de parametros que serão inseridos na mensagem.
	 * @param tipo - {@link Integer} - Constante que indica o tipo de mensagem - [MSG_ERRO, MSG_SUCESSO, MSG_ATENCAO].
	 * @return void
	 */
	protected void setMessage(String key, String[] parametros, SATIEnumTipoMensagem tipo) {
		setParamRequest(SATIEnumSecurity.SATI_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SATIEnumSecurity.SATI_TIPO_MENSAGEM, 		tipo);
		FacesUtil.addMessageCtx(this.getContext(), 		new FacesMessage(this.getMessage(key, parametros)));
	}
	
	/**
	 * Adiciona uma lista de mensagens no request.
	 * @param mensagens - List<Mensagem>
	 * @param tipo - int
	 */
	protected void setMessage(List<Mensagem> listaMensagem, SATIEnumTipoMensagem tipo) {
		setParamRequest(SATIEnumSecurity.SATI_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SATIEnumSecurity.SATI_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Adiciona uma lista de mensagens no request.
	 * @param mensagens - MensagemLista
	 * @param tipo - int
	 */
	protected void setMessage(MensagemLista listaMensagem, SATIEnumTipoMensagem tipo) {
		setParamRequest(SATIEnumSecurity.SATI_MODAL_MENSAGEM, 	Boolean.TRUE);
		setParamRequest(SATIEnumSecurity.SATI_TIPO_MENSAGEM, 		tipo);
		for(Mensagem msg : listaMensagem.getMensagens()){
			FacesUtil.addMessageCtx(this.getContext(),new FacesMessage(ResourceServiceUtil.getMessageResourceString(msg.getMensagem(), null)));
		}
	}
	
	/**
	 * Retorna o contexto do sistema.
	 * @return String
	 */
	protected String getContextPath(){
		return FacesUtil.getRequest(getContext()).getContextPath();
	}
	
	/**
	 * Insere parametros do controle de acesso na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @param parameter - Object
	 * @return void
	 */
	protected void setParamSession(SATIEnumSecurity key, Object parameter){
		super.setParamSession(key.name(), parameter);
	}

	/**
	 * Remove parametros do controle de acesso na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return void
	 */
	protected void removeParamSession(SATIEnumSecurity key){
		super.removeParamSession(key.name());
	}
	
	/**
	 * Remove a autenticação do usuário no controle de acesso.
	 * @param sistema - {@link String} - Atributo que representa o sistema que requisitou o logoff.
	 * no controle de acesso.
	 * 
	 * @return void
	 */
	protected void removerAcesso() throws Exception{
		//Destroi a sessão do Usuario
		sessionDestroy();
		Sistema negocioSistema = SATIBusinessFactory.getInstance().createSistema();
		SistemaTO sistema = negocioSistema.consultarSistemaContexto(getRequest().getContextPath());
		//Redireciona para a página inicial do sistema.
		FacesUtil.redirect(getContext(), sistema.getVchUrlSistema());
	}
	
	/**
	 * Retorna parametros do controle de acesso armazenados na sessão.
	 *
	 * @param key - AdmNetEnumSecurity
	 * @return Object
	 */
	protected Object getParamSession(SATIEnumSecurity key){
		return super.getParamSession(key.name());
	}
	
	/**
	 * Tratamento de exceções para filtrar as mensagens emitidas ao usuário.
	 * @param e - {@link Exception}
	 */
	protected String tratarExcecao(Exception e){
		List<Mensagem> mensagens = new ArrayList<Mensagem>();
		//Busca a origem de exceções não declaradas
		if(e instanceof UndeclaredThrowableException){
			e = Util.getException(e);
		}else if(e instanceof InvocationTargetException){
			e = Util.getException(e);
		}
		
		//exceções customizadas
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
		this.setMessage(mensagens, SATIEnumTipoMensagem.ERRO);
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
	 * Insere um valor ao atributo simpleTogglePanel.
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
	 * Insere um valor ao atributo estadoSimpleTogglePanel.
	 *
	 * @param estadoSimpleTogglePanel - Boolean
	 */
	public void setEstadoSimpleTogglePanel(Boolean estadoSimpleTogglePanel) {
		this.estadoSimpleTogglePanel = estadoSimpleTogglePanel;
	}
	
	/**
	 * Método chamado durante as requisições para construir o menu de ações.
	 * @return void
	 */
	protected void construirMenuAcoes(){
		Throwable throwble = new Throwable();
		for(int i = 0; i < throwble.getStackTrace().length; i ++){
			try{
				Object obj = Class.forName(throwble.getStackTrace()[i].getClassName()).newInstance();
				if(obj instanceof SATIControle){
					Method method = Util.getMethod(obj.getClass().getMethods(), throwble.getStackTrace()[i].getMethodName());
					if(method != null && method.isAnnotationPresent(SATIAcoesMenu.class)){
						SATIAcoesMenu anotacao = method.getAnnotation(SATIAcoesMenu.class);
						setParamRequest(OBJETO_MENU_ACOES, 	anotacao.objeto().getSimpleName());
						setParamRequest(FASE_ACAO, 			anotacao.fase().toString());
						setParamRequest(SISTEMA_ID, 		anotacao.sistema());
						if(!anotacao.pagina().isEmpty()){
							//valida se o usuário tem autorização para acessar a página
							SATIAutorizacao autorizacao = new SATIAutorizacao(getSession(), anotacao.pagina());
							setParamRequest(PAGINA, 		anotacao.pagina());							
							if(!autorizacao.isAutorizado()){
								redirect(autorizacao.getUrlDestino());
							}
						}
						break;
					}
				}else{
					break;
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Retorna o valor armazenado no atributo menuAcoes.
	 * @return HtmlDropDownMenu
	 */
	public HtmlToolBar getMenuAcoes() {
		return criarMenuAcoes();
	}
	
	/**
	 * Insere um valor ao atributo menuAcoes.
	 * @param menuAcoes - HtmlToolBar
	 */
	public void setMenuAcoes(HtmlToolBar menuAcoes) {
		criarMenuAcoes();
	}
	
	/**
	 * Retorna o valor armazenado no atributo menuAcoes.
	 * @return HtmlDropDownMenu
	 */
	public HtmlPanelGrid getListaAcoes() {
		List<UIComponent> lista = criarLinksAcoes();
		if(lista != null && !lista.isEmpty()){
			HtmlPanelGrid grid = new HtmlPanelGrid();
			grid.setColumns(1);
			grid.setCellpadding("0");
			grid.setCellspacing("0");
			//itera sobre os LINKS
			for(UIComponent link : criarLinksAcoes()){
				grid.getChildren().add(link);
			}
			return grid;
		}else{
			return null;
		}
	}	
	
	/**
	 * Retorna o valor armazenado no atributo menuAcoes.
	 * @param grid - HtmlPanelGrid
	 */
	public void setListaAcoes(HtmlPanelGrid grid) {}
	
	/**
	 * Constroi o menu de ações com base nos parâmetros passados.
	 * @return HtmlToolBar
	 */
	private HtmlToolBar criarMenuAcoes(){
		//captura os parâmetros de construção
		String objetoTO = (String)getParamRequest(OBJETO_MENU_ACOES);
		String faseObjeto = (String)getParamRequest(FASE_ACAO);
		String nomeSistema = (String)getParamRequest(SISTEMA_ID);
		String pagina = (String)getParamRequest(PAGINA);
		//retorna o sistema que requisitou a ação
		SistemaTO sistema = SATIBusinessFactory.getInstance().createSistema().consultarSistemaNome(nomeSistema);
		//constroi o menu de ações
		SATIMenuAcoes menuAcoes = new SATIMenuAcoes(getContext(), sistema, getPerfilAcessoLogado(), objetoTO, faseObjeto, pagina);
		return menuAcoes.getMenuAcoes();
	}

	/**
	 * Constroi o menu de ações com base nos parâmetros passados.
	 * @return List<UIComponent>
	 */
	private List<UIComponent> criarLinksAcoes(){
		//captura os parâmetros de construção
		String objetoTO = (String)getParamRequest(OBJETO_MENU_ACOES);
		String faseObjeto = (String)getParamRequest(FASE_ACAO);
		String nomeSistema = (String)getParamRequest(SISTEMA_ID);
		String pagina = (String)getParamRequest(PAGINA);
		//retorna o sistema que requisitou a ação
		SistemaTO sistema = getSATIBusinessFactory().createSistema().consultarSistemaNome(nomeSistema);
		//constroi o menu de ações
		SATIMenuAcoes menuAcoes = new SATIMenuAcoes(getContext(), sistema, getPerfilAcessoLogado(), objetoTO, faseObjeto, pagina);
		return menuAcoes.getLinkAcoes();
	}

	
	/**
	 * Constroi a barra de menu principal de acordo com o perfil do usuário.
	 * @param sistema - {@link String} - Nome do sistema para a construção do MENU.
	 * @return {@link HtmlToolBar}
	 * @throws RegraNegocioException 
	 */
	protected HtmlToolBar construirMenuBarra(String nomeSistema) throws RegraNegocioException{
		SistemaTO sistema = getSATIBusinessFactory().createSistema().consultarSistemaNome(nomeSistema);
		SATIMenuBarra menuBarra = new SATIMenuBarra(sistema, getPerfilAcessoLogado(), getContext());
		return menuBarra.getMenuBarra();
	}
	
	/**
	 * Constroi a barra de menu principal de acordo com o perfil do usuário.
	 * @param sistema - {@link String} - Nome do sistema para a construção do MENU.
	 * @return {@link HtmlToolBar}
	 * @throws RegraNegocioException 
	 */
	protected HtmlToolBar construirMenuBarra(Long idMenuPai) throws RegraNegocioException{
		MenuSistemaTO menu = getSATIBusinessFactory().createMenuSistema().consultar(new MenuSistemaTO(idMenuPai));
		SATIMenuBarra menuBarra = new SATIMenuBarra(menu, getPerfilAcessoLogado(), getContext());
		return menuBarra.getMenuBarra();
	}
	
	
}


