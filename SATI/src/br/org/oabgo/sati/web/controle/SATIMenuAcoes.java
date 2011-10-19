package br.org.oabgo.sati.web.controle;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlGraphicImage;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;

import org.richfaces.component.html.HtmlDropDownMenu;
import org.richfaces.component.html.HtmlMenuGroup;
import org.richfaces.component.html.HtmlMenuItem;
import org.richfaces.component.html.HtmlMenuSeparator;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.component.html.HtmlToolBarGroup;

import br.org.oabgo.sati.negocio.controle.SATIBusinessFactory;
import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcaoMenu;
import core.Oab;
import core.dao.TransferObject;
import core.excecoes.RegraNegocioException;
import core.jsf.FacesUtil;
import core.mensagem.MensagemLista;
import core.resource.ResourceServiceUtil;
import core.utilitario.RandomIntGenerator;
import core.utilitario.Util;

/**
 * <b>Classe:</b> SATIMenuAcoes.java <br>
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
public class SATIMenuAcoes extends Oab{

	private static final String 				SATI_COMPONENT_ID 				= "sati_";
	public static  final String 				FASE_LISTAGEM 					= "0";
	public static  final String 				FASE_CONSULTA 					= "1";
	public static  final String 				FASE_INCLUSAO 					= "2";
	public static  final String 				FASE_EDICAO 					= "3";
	
	private List<UIComponent> 			listaAcoes;
	private HtmlToolBar 				acoes;
	private String 						objetoTO;
	private String 						faseObjeto;
	private SistemaTO 					sistema;
	private PerfilAcessoTO 				perfilAcesso;
	private String 						pagina;
	private AcaoMenu					negocioAcao;
	private FacesContext				context;
	
	/**
	 * Construtor da classe ACSMenuAcoes.java - "TipoDoParâmetro".
	 */
	public SATIMenuAcoes(){}
	
	
	/**
	 * Constroi o menu de ações de uma pagina.
	 * @param context 		- FacesContext
	 * @param sistema 		- SistemaTO
	 * @param perfilAcesso 	- PerfilAcessoTO
	 * @param objetoTO
	 * @param faseObjeto
	 * @param pagina - "TipoDoParâmetro"
	 */
	public SATIMenuAcoes(FacesContext	context, 
						SistemaTO 		sistema, 
						PerfilAcessoTO 	perfilAcesso, 
						String 			objetoTO, 
						String 			faseObjeto, 
						String 			pagina) {
		
		this.listaAcoes		= new ArrayList<UIComponent>();
		this.context 		= context;
		this.faseObjeto 	= faseObjeto;
		this.objetoTO 		= objetoTO;
		this.sistema 		= sistema;
		this.pagina 		= pagina;
		this.perfilAcesso 	= perfilAcesso;
		this.negocioAcao 	= SATIBusinessFactory.getInstance().createAcaoMenu(); 
		
		criarMenuAcoes();
	}

	/**
	 * Constroi o menu de ações com base nos parâmetros passados.
	 * @return void
	 */
	private void criarMenuAcoes(){
		//Cria um gerador randomico de inteiros na faixa de 100 a 9999
		//RandomIntGenerator gen = new RandomIntGenerator(100, 9999);
		if(objetoTO != null && faseObjeto != null && sistema != null){
			//busca o perfil de acesso com base no usuário logado
			try{
				//busca as ações que o usuário pode visualizar
				List<TransferObject> listaAcao = negocioAcao.listarAcaoUsuarioBase(	this.perfilAcesso, 
																					objetoTO, 
																					getNumeroFaseACS(faseObjeto), 
																					sistema, 
																					pagina);
				HtmlToolBar barra = new HtmlToolBar();
				barra.setItemSeparator("line");
				HtmlToolBarGroup grupoBarra = new HtmlToolBarGroup();
				grupoBarra.setLocation("right");
				//constroi a lista de ações do usuário
				if(!listaAcao.isEmpty()){
					//itera sobre os registros contruido os menus
					Iterator<TransferObject> iter = listaAcao.iterator();
					try{
						while(iter.hasNext()){
							AcaoMenuTO acaoMenu = (AcaoMenuTO)iter.next();
							HtmlDropDownMenu dropDow = new HtmlDropDownMenu();
							dropDow.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDow.setValue(getMessage(acaoMenu.getVchChaveTitulo()));
							//constroi a lista de filhos
							if(acaoMenu.getListaAcaoFilho() != null && !acaoMenu.getListaAcaoFilho().isEmpty()){
								criarComponentesFilhosMenuAcoes(acaoMenu.getListaAcaoFilho(), dropDow, objetoTO, faseObjeto, this.perfilAcesso);
							}
							grupoBarra.getChildren().add(dropDow);
						}
						barra.getChildren().add(grupoBarra);
						this.acoes = barra;
					}catch (Exception e) {
						MensagemLista lista = new MensagemLista();
						lista.addMensagem("ACS ERRO: Erro na criação do Menu de Ações do sistema "+sistema.getVchNome());
						lista.addMensagem(e.getMessage());
						throw new RegraNegocioException(lista);
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Metodo chamado recursivamente para popular a lista de menus.
	 *
	 * @param listaMenu - {@link @param acaoMenuPai - {@link AcaoMenuTO}}
	 * @param dropDowMenu - {@link UIComponent}
	 * @return void
	 */
	private void criarComponentesFilhosMenuAcoes(Set<AcaoMenuTO> listaAcao , UIComponent dropDowMenu, String objeto, String fase, PerfilAcessoTO perfilAcesso) throws Exception{
		int qtdMenusNivel = 0;
		if(dropDowMenu.getChildren() != null){
			qtdMenusNivel = dropDowMenu.getChildren().size();
		}
		
		Iterator<AcaoMenuTO> iter = listaAcao.iterator();
		while(iter.hasNext()){
			AcaoMenuTO acaoMenu = iter.next();
			if(negocioAcao.possuiAcessoAcao(perfilAcesso, acaoMenu)){
				if(acaoMenu.getBitAgrupador()){
					HtmlMenuGroup grupo = new HtmlMenuGroup();
					grupo.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
					grupo.setValue(getMessage(acaoMenu.getVchChaveTitulo()));
					//insere o icone
					if(acaoMenu.getVchIcone() != null){
						grupo.setIcon(getMessage(acaoMenu.getVchIcone()));
					}
					//executa a operação auxilar para renderizar o botão
					if(acaoMenu.getVchRenderedJSF() != null && !acaoMenu.getVchRenderedJSF().isEmpty()){
						boolean rendered = (Boolean)getValueExpression(acaoMenu.getVchRenderedJSF(), Boolean.class).getValue(this.context.getELContext());
						grupo.setRendered(rendered);
						//os componenetes filhos não precisam ser construidos se o agrupador não vai ser
						if(rendered){
							criarComponentesFilhosMenuAcoes(acaoMenu.getListaAcaoFilho(), grupo, objeto, fase, perfilAcesso);
						}
						//adiciona o separador somente se o menu for renderizado
						if(rendered && qtdMenusNivel > 0){
							HtmlMenuSeparator separador = new HtmlMenuSeparator();
							separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDowMenu.getChildren().add(separador);
						}
					}else{
						criarComponentesFilhosMenuAcoes(acaoMenu.getListaAcaoFilho(), grupo, objeto, fase, perfilAcesso);	
						if(qtdMenusNivel > 0){
							//adiciona o separador somente se o menu for renderizado
							HtmlMenuSeparator separador = new HtmlMenuSeparator();
							separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDowMenu.getChildren().add(separador);
						}
					}
					dropDowMenu.getChildren().add(grupo);
				}else{		
					HtmlMenuItem item = new HtmlMenuItem();
					item.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
					item.setImmediate(acaoMenu.getBitAcaoImmediate());
					item.setValue(getMessage(acaoMenu.getVchChaveTitulo()));
					
					//insere o javaScript que será disparado no onclick do menu
					if(acaoMenu.getVchAcaoOnClick() != null && !acaoMenu.getVchAcaoOnClick().isEmpty()){
						item.setOnselect(getMessage(acaoMenu.getVchAcaoOnClick()));
					}
					//insere o icone
					if(acaoMenu.getVchIcone() != null && !acaoMenu.getVchIcone().isEmpty()){
						if(acaoMenu.getVchRenderedIcone() != null && !acaoMenu.getVchRenderedIcone().isEmpty()){
							//verifica se o ícone deve ser renderizado
							if((Boolean)getValueExpression(acaoMenu.getVchRenderedIcone(), Boolean.class).getValue(this.context.getELContext())){
								item.setIcon(getMessage(acaoMenu.getVchIcone()));
							}
						}else{
							item.setIcon(getMessage(acaoMenu.getVchIcone()));							
						}
					}
					//insere o método alvo
					if(acaoMenu.getVchMetodoAlvo() != null && !acaoMenu.getVchMetodoAlvo().isEmpty()){
						item.setActionExpression(createMethodExpression(acaoMenu.getVchMetodoAlvo(), String.class, new Class<?>[0]));
					}
					//insere o submitMode para o item
					if(acaoMenu.getVchSubmitModeJSF() != null && !acaoMenu.getVchSubmitModeJSF().isEmpty()){
						item.setSubmitMode(acaoMenu.getVchSubmitModeJSF());
					}
					
					//executa a operação auxilar para renderizar o botão
					if(acaoMenu.getVchRenderedJSF() != null && !acaoMenu.getVchRenderedJSF().isEmpty()){
						Boolean rendered = (Boolean)getValueExpression(acaoMenu.getVchRenderedJSF(), Boolean.class).getValue(this.context.getELContext()); 
						item.setRendered(rendered);
						if(rendered &&  qtdMenusNivel > 0){
							//adiciona o separador somente se o menu for renderizado
							HtmlMenuSeparator separador = new HtmlMenuSeparator();
							separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDowMenu.getChildren().add(separador);
						}
					}else{
						//insere o rendered para o item de menu
						item.setRendered(true);						
						//insere o separador para o item
						if(qtdMenusNivel > 0){
							HtmlMenuSeparator separador = new HtmlMenuSeparator();
							separador.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
							dropDowMenu.getChildren().add(separador);
						}
					}
					
//					//adiciona uma lista de parâmetros para o menu
//					if(acaoMenu.getListaParametroMenu() != null && !acaoMenu.getListaParametroMenu().isEmpty()){
//						for(ParametroMenuTO parametro : acaoMenu.getListaParametroMenu()){
//							SetPropertyActionListener event = new SetPropertyActionListener();
//							event.setTarget(getValueExpression(parametro.getTarget(), Class.forName(parametro.getType())));
//							event.setValue(getValueExpression(parametro.getValue(), Class.forName(parametro.getType())));
//							item.addActionListener(event);
//						}
//					}
					dropDowMenu.getChildren().add(item);
					//cria o COMMAND LINK
					criarCommandLink(acaoMenu);
				}
			}
		}
	}
	
	/**
	 * Cria um commandLInk com a ação a ser executada.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws Exception 
	 * @return void
	 */
	private void criarCommandLink(AcaoMenuTO acaoMenu) throws Exception{
		
		HtmlCommandLink item = new HtmlCommandLink();
		item.setId(SATI_COMPONENT_ID + String.valueOf(RandomIntGenerator.proximo()));
		item.setImmediate(acaoMenu.getBitAcaoImmediate());
		item.setValue(getMessage(acaoMenu.getVchChaveTitulo()));
		//insere o estilo no componente
		item.setStyle("font-size: 11px;	font-weight: normal; text-decoration: none;");
				
		//aplica o java script a ser executado ao botão
		if(acaoMenu.getVchAcaoOnClick() != null && !acaoMenu.getVchAcaoOnClick().isEmpty()){
			item.setOnclick(getMessage(acaoMenu.getVchAcaoOnClick()));
		}
		
		//insere o método alvo
		if(Util.stringOk(acaoMenu.getVchMetodoAlvo())){
			item.setActionExpression(createMethodExpression(acaoMenu.getVchMetodoAlvo(), String.class, new Class<?>[0]));
		}
		
//		//adiciona uma lista de parâmetros para o menu
//		if(acaoMenu.getListaParametroMenu() != null && !acaoMenu.getListaParametroMenu().isEmpty()){
//			for(ParametroMenuTO parametro : acaoMenu.getListaParametroMenu()){
//				SetPropertyActionListener event = new SetPropertyActionListener();
//				event.setTarget(getValueExpression(parametro.getTarget(), String.class));
//				event.setValue(getValueExpression(parametro.getValue(), String.class));
//				item.addActionListener(event);
//			}
//		}
		
		//executa a operação auxilar para renderizar o botão
		Boolean rendered = true;
		if(acaoMenu.getVchRenderedJSF() != null && !acaoMenu.getVchRenderedJSF().isEmpty()){
			rendered = (Boolean)getValueExpression(acaoMenu.getVchRenderedJSF(), Boolean.class).getValue(this.context.getELContext()); 
		}
		
		if(acaoMenu.getVchIcone() != null && !acaoMenu.getVchIcone().isEmpty()){
			HtmlPanelGrid grid = new HtmlPanelGrid();
			grid.setColumns(2);
			grid.setRendered(rendered);
			
			//rendered para o icone a ser renderizado
			if(acaoMenu.getVchRenderedIcone() != null && !acaoMenu.getVchRenderedIcone().isEmpty()){
				//verifica se o ícone deve ser renderizado
				if((Boolean)getValueExpression(acaoMenu.getVchRenderedIcone(), Boolean.class).getValue(this.context.getELContext())){
					HtmlGraphicImage image = new HtmlGraphicImage();
					image.setUrl(getMessage(acaoMenu.getVchIcone()));
					
					
					grid.getChildren().add(image);
					grid.getChildren().add(item);
					this.listaAcoes.add(grid);			
				}
			}else{
				HtmlGraphicImage image = new HtmlGraphicImage();
				image.setUrl(getMessage(acaoMenu.getVchIcone()));				

				grid.getChildren().add(image);
				grid.getChildren().add(item);
				this.listaAcoes.add(grid);			
			}
		}else{
			item.setRendered(rendered);
			this.listaAcoes.add(item);			
		}
		
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
	@SuppressWarnings("unchecked")
	private ValueExpression getValueExpression(String expressao, Class tipoRetorno) throws Exception {
		return FacesUtil.getValueExpression(this.context, expressao, tipoRetorno);
	} 
	
	/**
	 * Retorna o numero da fase com base na descrição.
	 * @param idFase
	 * @return - 
	 * @return String
	 */
	public String getNumeroFaseACS(String idFase){
		if(idFase.equals("FASE_CONSULTA")){
			return getMessage(FASE_CONSULTA);
		}else if(idFase.equals("FASE_EDICAO")){
			return getMessage(FASE_EDICAO);
		}else if(idFase.equals("FASE_INCLUSAO")){
			return getMessage(FASE_INCLUSAO);
		}else if(idFase.equals("FASE_LISTAGEM")){
			return getMessage(FASE_LISTAGEM);
		}
		return null;
	}
	
	/**
	 * Retorna o nome da Fase do objeto para o controle de acesso.
	 *
	 * @param idFase - {@link String}
	 * @return String
	 */
	public String getNomeFaseACS(String idFase){
		if(idFase.equals(FASE_CONSULTA)){
			return getMessage("acsFaseConsultar");
		}else if(idFase.equals(FASE_EDICAO)){
			return getMessage("acsFaseEditar");
		}else if(idFase.equals(FASE_INCLUSAO)){
			return getMessage("acsFaseInclusao");
		}else if(idFase.equals(FASE_LISTAGEM)){
			return getMessage("acsFaseListar");
		}
		return null;
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
	 * Retorna o objeto que representa a barra de menu.
	 * @return HtmlToolBar
	 */
	public HtmlToolBar getMenuAcoes(){
		return acoes;
	}

	/**
	 * Retorna a lista contendo os CoomandLink's que representam as ações.
	 * @return List<UIComponent>
	 */
	public List<UIComponent> getLinkAcoes(){
		return listaAcoes;
	}
}
