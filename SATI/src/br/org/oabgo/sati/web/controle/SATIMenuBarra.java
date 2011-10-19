package br.org.oabgo.sati.web.controle;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.context.FacesContext;

import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlMenuSeparator;
import org.richfaces.component.html.HtmlToolBar;

import br.org.oabgo.sati.negocio.controle.SATIBusinessFactory;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.MenuSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.ServicoGenerico;
import core.Oab;
import core.dao.TransferObject;
import core.excecoes.RegraNegocioException;
import core.jsf.FacesUtil;
import core.mensagem.MensagemLista;
import core.resource.ResourceServiceUtil;
import core.utilitario.RandomIntGenerator;

/**
 * <b>Classe:</b> SATIMenuBarra.java <br>
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
public class SATIMenuBarra extends Oab{

	
	private static final String 	SATI_COMPONENT_ID 				= "sati_";
	
	
	private MenuSistemaTO	menuPai;
	private SistemaTO		sistema;
	private PerfilAcessoTO	perfilAcesso;
	private HtmlToolBar 	menuBarra;
	
	private MenuSistema		negocioMenu;
	private ServicoGenerico	servicoGenerico;
	private FacesContext	context;
	
	/**
	 * Construtor para a criação do componente de MenuBarra.
	 * 
	 * @param menuPai 		- MenuSistemaTO 			- Menu pai, todos os menus abaixo deste serão construidos.
	 * @param perfilAcesso 	- PerfilAcessoTO 	- Perfil de acesso do usuário que esta requisitando a construção do menu.
	 * @param context 		- FacesContext 		- Contexto JSF para criar a navegação.
	 * @throws RegraNegocioException 
	 */
	public SATIMenuBarra(MenuSistemaTO menuPai, PerfilAcessoTO perfilAcesso, FacesContext	context) throws RegraNegocioException {
		this.menuPai 			= menuPai;
		this.perfilAcesso 		= perfilAcesso;
		this.context 			= context;
		this.negocioMenu 		= SATIBusinessFactory.getInstance().createMenuSistema();
		this.servicoGenerico	= SATIBusinessFactory.getInstance().createServicoGenerico();
		
		construirMenuBarraFilhos();
	}

	/**
	 * Construtor para a criação do componente de MenuBarra.
	 * 
	 * @param sistema 		- SistemaTO 		- Sistema controlado que está requisitando a construção do menu.
	 * @param perfilAcesso 	- PerfilAcessoTO 	- Perfil de acesso do usuário que esta requisitando a construção do menu.
	 * @param context 		- FacesContext 		- Contexto JSF para criar a navegação.
	 * @throws RegraNegocioException 
	 */
	public SATIMenuBarra(SistemaTO sistema, PerfilAcessoTO perfilAcesso, FacesContext	context) throws RegraNegocioException {
		this.sistema 			= sistema;
		this.perfilAcesso 		= perfilAcesso;
		this.context 			= context;
		this.negocioMenu 		= SATIBusinessFactory.getInstance().createMenuSistema();
		this.servicoGenerico	= SATIBusinessFactory.getInstance().createServicoGenerico();
		
		construirMenuBarraSistema();
	}


	/**
	 * Dispara a construção da barra de menu.
	 * @throws RegraNegocioException - 
	 * @return void
	 */
	private void construirMenuBarraFilhos() throws RegraNegocioException{
		//tratamento caso aconteça LAZY INICIALIZATION EXCEPTION ou apenas o ID do menu
		// tenha sido passado
		try{
			if(menuPai.getListaMenuFilho() == null || menuPai.getListaMenuFilho().isEmpty()){
				menuPai = negocioMenu.consultar(menuPai);
			}
		}catch (Exception e) {
			menuPai = negocioMenu.consultar(menuPai);
		}
		
		Set<MenuSistemaTO> listaMenu = menuPai.getListaMenuFilho();
		if(!listaMenu.isEmpty()){
			//itera sobre os registros contruido os menus
			Iterator<MenuSistemaTO> iter = listaMenu.iterator();
			try{
				HtmlToolBar barra = new HtmlToolBar();
				barra.setItemSeparator("grid");
				while(iter.hasNext()){
					MenuSistemaTO menu = iter.next();
					//o menu so é construido se o usuário possuir acesso
					if(menu.getBitAtivo() && negocioMenu.possuiAcessoMenu(this.perfilAcesso, menu)){
						if(menu.getBitAgrupador()){
							HtmlDropDownMenu dropDow = new HtmlDropDownMenu();
							dropDow.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDow.setValue(getMessage(menu.getVchChaveTitulo()));
							//aplica o estilo ao menu
							if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
								dropDow.setStyleClass(menu.getVchClasseEstiloCss());
							}
							//constroi a lista de filhos
							if(menu.getListaMenuFilho() != null && !menu.getListaMenuFilho().isEmpty()){
								criarComponentesFilhos(menu.getListaMenuFilho(), dropDow, this.perfilAcesso);
							}
							//insere o style na barra de menu
							if(barra.getStyleClass() == null){
								barra.setStyleClass(menu.getVchClasseEstiloCss());
							}
							barra.getChildren().add(dropDow);
						}else{
							HtmlCommandLink item = new HtmlCommandLink();
							item.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							item.setImmediate(menu.getBitAcaoImmediate());
							item.setValue(getMessage(menu.getVchChaveTitulo()));
							item.setStyleClass("txtLinkMenuBarra");
							
							//aplica o estilo ao item de Menu
							if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
								item.setStyleClass(menu.getVchClasseEstiloCss());
							}
							
							//aplica o java script a ser executado ao botão
							if(menu.getVchAcaoOnClick() != null && !menu.getVchAcaoOnClick().isEmpty()){
								item.setOnclick(getMessage(menu.getVchAcaoOnClick()));
							}
							
							//insere o método alvo
							if(menu.getVchMetodoAlvo() != null){
								item.setActionExpression(createMethodExpression(menu.getVchMetodoAlvo(), String.class, new Class<?>[0]));
							}
							
//							//adiciona uma lista de parâmetros para o menu
//							if(menu.getListaParametroMenu() != null && !menu.getListaParametroMenu().isEmpty()){
//								for(ParametroMenuTO parametro : menu.getListaParametroMenu()){
//									SetPropertyActionListener event = new SetPropertyActionListener();
//									event.setTarget(getValueExpression(parametro.getTarget(), String.class));
//									event.setValue(getValueExpression(parametro.getValue(), String.class));
//									item.addActionListener(event);
//								}
//							}
							
							barra.getChildren().add(item);
						}
					}
				}
				this.menuBarra = barra;
			}catch (Exception e) {
				MensagemLista lista = new MensagemLista();
				lista.addMensagem("SATI ERRO: Erro na criação do MENU BARRA do sistema "+sistema.getVchNome());
				lista.addMensagem(e.getMessage());
				throw new RegraNegocioException(lista);
			}
		}
	}
	
	/**
	 * Dispara a construção da barra de menu.
	 * @throws RegraNegocioException - 
	 * @return void
	 */
	private void construirMenuBarraSistema() throws RegraNegocioException{
		//retorna os registros base do MENU
		List<TransferObject> listaMenu = negocioMenu.listarMenuPerfilAcessoBase(this.perfilAcesso, this.sistema);
		if(!listaMenu.isEmpty()){
			//itera sobre os registros contruido os menus
			Iterator<TransferObject> iter = listaMenu.iterator();
			try{
				HtmlToolBar barra = new HtmlToolBar();
				barra.setItemSeparator("grid");
				while(iter.hasNext()){
					MenuSistemaTO menu = (MenuSistemaTO)iter.next();
					//o menu só será construido se o usuário possuir acesso
					if(menu.getBitAtivo() && negocioMenu.possuiAcessoMenu(perfilAcesso, menu)){
						if(menu.getBitAgrupador()){
							HtmlDropDownMenu dropDow = new HtmlDropDownMenu();
							dropDow.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDow.setValue(getMessage(menu.getVchChaveTitulo()));
							//aplica o estilo ao menu
							if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
								dropDow.setStyleClass(menu.getVchClasseEstiloCss());
							}
							//constroi a lista de filhos
							if(menu.getListaMenuFilho() != null && !menu.getListaMenuFilho().isEmpty()){
								criarComponentesFilhos(menu.getListaMenuFilho(), dropDow, perfilAcesso);
							}
							//insere o style na barra de menu
							if(barra.getStyleClass() == null){
								barra.setStyleClass(menu.getVchClasseEstiloCss());
							}
							barra.getChildren().add(dropDow);
						}else{
							HtmlCommandLink item = new HtmlCommandLink();
							item.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							item.setImmediate(menu.getBitAcaoImmediate());
							item.setStyleClass("txtLinkMenuBarra");
							item.setValue(getMessage(menu.getVchChaveTitulo()));
							
							//aplica o estilo ao item de Menu
							if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
								item.setStyleClass(menu.getVchClasseEstiloCss());
							}
	
							//aplica o java script a ser executado ao botão
							if(menu.getVchAcaoOnClick() != null && !menu.getVchAcaoOnClick().isEmpty()){
								item.setOnclick(getMessage(menu.getVchAcaoOnClick()));
							}
							
							//insere o método alvo
							if(menu.getVchMetodoAlvo() != null){
								item.setActionExpression(createMethodExpression(menu.getVchMetodoAlvo(), String.class, new Class<?>[0]));
							}
//							//adiciona uma lista de parâmetros para o menu
//							if(menu.getListaParametroMenu() != null && !menu.getListaParametroMenu().isEmpty()){
//								for(ParametroMenuTO parametro : menu.getListaParametroMenu()){
//									SetPropertyActionListener event = new SetPropertyActionListener();
//									event.setTarget(getValueExpression(parametro.getTarget(), String.class));
//									event.setValue(getValueExpression(parametro.getValue(), String.class));
//									item.addActionListener(event);
//								}
//							}
							barra.getChildren().add(item);
						}
					}
					servicoGenerico.retirarObjetoSessao(menu);
				}
				this.menuBarra = barra;
			}catch (Exception e) {
				MensagemLista lista = new MensagemLista();
				lista.addMensagem("SATI ERRO: Erro na criação do MENU BARRA do sistema "+sistema.getVchNome());
				lista.addMensagem(e.getMessage());
				throw new RegraNegocioException(lista);
			}
		}
	}
	
	/**
	 * Metodo chamado recursivamente para popular a lista de menus.
	 *
	 * @param listaMenu - {@link Set}
	 * @param dropDowMenu - {@link UIComponent}
	 * @return void
	 */
	private void criarComponentesFilhos(Set<MenuSistemaTO> listaMenu, UIComponent dropDowMenu, PerfilAcessoTO perfilAcesso) throws Exception{
		Iterator<MenuSistemaTO> iter = listaMenu.iterator();
		while(iter.hasNext()){
			MenuSistemaTO menu = (MenuSistemaTO)iter.next();
			if(menu.getBitAtivo() && negocioMenu.possuiAcessoMenu(perfilAcesso, menu)){
				if(menu.getBitAgrupador()){
					HtmlMenuGroup grupo = new HtmlMenuGroup();
					grupo.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
					grupo.setValue(getMessage(menu.getVchChaveTitulo()));
					//aplica o estilo ao grupo
					if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
						grupo.setStyleClass(menu.getVchClasseEstiloCss());
					}
					//insere o icone
					if(menu.getVchIcone() != null){
						grupo.setIcon(getMessage(menu.getVchIcone()));
					}
					criarComponentesFilhos(menu.getListaMenuFilho(), grupo, perfilAcesso);
					dropDowMenu.getChildren().add(grupo);
					//insere o separador caso existam mais registros
					if(iter.hasNext()){
						HtmlMenuSeparator separador = new HtmlMenuSeparator();
						separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
						dropDowMenu.getChildren().add(separador);
					}
				}else{			
					HtmlMenuItem item = new HtmlMenuItem();
					item.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
					item.setImmediate(menu.getBitAcaoImmediate());
					item.setValue(getMessage(menu.getVchChaveTitulo()));
					
					//aplica o java script a ser executado ao botão
					if(menu.getVchAcaoOnClick() != null && !menu.getVchAcaoOnClick().isEmpty()){
						item.setOnselect(getMessage(menu.getVchAcaoOnClick()));
					}
					
					//aplica o estilo ao item de Menu
					if(menu.getVchClasseEstiloCss() != null && !menu.getVchClasseEstiloCss().isEmpty()){
						item.setStyleClass(menu.getVchClasseEstiloCss());
					}
					
					//insere o icone
					if(menu.getVchIcone() != null){
						item.setIcon(getMessage(menu.getVchIcone()));
					}
					//insere o método alvo
					if(menu.getVchMetodoAlvo() != null){
						item.setActionExpression(createMethodExpression(menu.getVchMetodoAlvo(), String.class, new Class<?>[0]));
					}
//					//cria a lista de parâmetros para o MENU
//					if(menu.getListaParametroMenu() != null && !menu.getListaParametroMenu().isEmpty()){
//						for(ParametroMenuTO parametro : menu.getListaParametroMenu()){
//							SetPropertyActionListener event = new SetPropertyActionListener();
//							event.setTarget(getValueExpression(parametro.getTarget(), String.class));
//							event.setValue(getValueExpression(parametro.getValue(), String.class));
//							item.addActionListener(event);
//						}
//					}
										
					dropDowMenu.getChildren().add(item);

					//insere o separador caso existam mais registros
//					if(iter.hasNext()){
//						HtmlMenuSeparator separador = new HtmlMenuSeparator();
//						separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
//						dropDowMenu.getChildren().add(separador);
//					}
				}
			}
			//retira o objeto da sessão
			servicoGenerico.retirarObjetoSessao(menu);
		}
	}
	
	
	/**
	 * Retorna um String do arquivo de mensagens.
	 * @param key - String
	 * @return String
	 */
	private String getMessage(String key){
		return ResourceServiceUtil.getMessageResourceString(key, null);
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
	private MethodExpression createMethodExpression(String expressao, Class tipoRetorno, Class[] argumentos) throws Exception {
		return this.context.getApplication().getExpressionFactory().createMethodExpression(this.context.getELContext(), expressao, tipoRetorno, argumentos);
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
	@SuppressWarnings({ "unchecked", "unused" })
	private ValueExpression getValueExpression(String expressao, Class tipoRetorno) throws Exception {
		return FacesUtil.getValueExpression(this.context, expressao, tipoRetorno);
	} 
	
	public HtmlToolBar getMenuBarra() {
		return menuBarra;
	}

}
