package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.TipoMenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.MenuSistema;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

/**
 * <b>Classe:</b> MenuSistemaBean.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle.bean <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public class MenuSistemaBean extends SATIManagedBean {

	private static final long serialVersionUID = 2389134412801064255L;

	private MenuSistemaTO menuSistema;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaMenuSistemaSuperior;

	private List<SelectItem> listaSistema;

	private List<TransferObject> listaMenuSistemaDataTable;

	private String idMenuSistemaSuperior;

	private String idTipoMenu;
	
	private String chaveFiltro;

	private String descricaoFiltro;

	public String listar() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			MenuSistemaTO menuSistema = this.pegarValorFiltro();
			this.setListaMenuSistemaDataTable(negocioMenuSistema.listar(menuSistema,
					"VchChaveTitulo"));
			return "listarMenuSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public MenuSistemaTO pegarValorFiltro() {
		MenuSistemaTO menuSistema = new MenuSistemaTO();
		menuSistema.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		menuSistema.setVchChaveTitulo(Util.setString(this.getDescricaoFiltro()));
		return menuSistema;
	}
	
	public String incluir() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			
			this.getMenuSistema().setMenuPai(Util.setValueTO(this.getIdMenuSistemaSuperior(), MenuSistemaTO.class));
			
			this.getMenuSistema().setTipoMenuSistema(Util.setValueTO(this.getIdTipoMenu(), TipoMenuSistemaTO.class));
			
			negocioMenuSistema.incluir(this.getMenuSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdTipoMenu(new String());
			this.setIdMenuSistemaSuperior(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			negocioMenuSistema.excluir(this.getMenuSistema(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			
			this.getMenuSistema().setMenuPai(Util.setValueTO(this.getIdMenuSistemaSuperior(), MenuSistemaTO.class));
			
			if (this.getIdTipoMenu() != null) {
				this.getMenuSistema().setTipoMenuSistema(Util.setValueTO(this.getIdTipoMenu(), TipoMenuSistemaTO.class));
			}
			negocioMenuSistema.alterar(this.getMenuSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			this.setMenuSistema(negocioMenuSistema.consultar(new MenuSistemaTO(this.getMenuSistema().getId())));
			
			this.setIdMenuSistemaSuperior(String.valueOf(this.getMenuSistema().getMenuPai().getId()));
			
			this.setIdTipoMenu(String.valueOf(this.getMenuSistema().getTipoMenuSistema().getId()));
			
			return "editarMenuSistema";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setMenuSistema(new MenuSistemaTO());

		return "novoMenuSistema";
	}
	
	public String consultar() {
		MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
		try {
			negocioMenuSistema.retirarObjetoSessao(this.getMenuSistema());
			this.setMenuSistema(negocioMenuSistema.consultar(new MenuSistemaTO(this.getMenuSistema().getId())));

			return "consultarMenuSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public void setMenuSistema(MenuSistemaTO menuSistema) {
		this.menuSistema = menuSistema;
	}

	public MenuSistemaTO getMenuSistema() {
		if (menuSistema == null) {
			menuSistema = new MenuSistemaTO();
		}
		return menuSistema;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaMenuSistemaSuperior(List<SelectItem> listaMenuSistemaSuperior) {
		this.listaMenuSistemaSuperior = listaMenuSistemaSuperior;
	}

	public List<SelectItem> getListaMenuSistemaSuperior() {
		if (listaMenuSistemaSuperior == null) {
			MenuSistema negocioMenuSistema = getSATIBusinessFactory().createMenuSistema();
			List<TransferObject> lista = negocioMenuSistema.listar(new MenuSistemaTO(),
					"vchChaveTitulo");
			this.listaMenuSistemaSuperior = Util.createListSelectItens(lista, "id",
					"vchChaveTitulo");
		}
		return listaMenuSistemaSuperior;
	}

	public void setListaSistema(List<SelectItem> listaSistema) {
		this.listaSistema = listaSistema;
	}

	public List<SelectItem> getListaSistema() {
		if (listaSistema == null) {
			Sistema negocioSistema = getSATIBusinessFactory().createSistema();
			List<TransferObject> lista = negocioSistema.listar(new SistemaTO(),
					"vchSigla");
			this.listaSistema = Util.createListSelectItens(lista, "id",
					"vchSigla");
		}
		return listaSistema;
	}

	public void setListaMenuSistemaDataTable(List<TransferObject> listaMenuSistemaDataTable) {
		this.listaMenuSistemaDataTable = listaMenuSistemaDataTable;
	}

	public List<TransferObject> getListaMenuSistemaDataTable() {
		if (listaMenuSistemaDataTable == null) {
			listaMenuSistemaDataTable = new ArrayList<TransferObject>();
		}
		return listaMenuSistemaDataTable;
	}

	public void setIdMenuSistemaSuperior(String idMenuSistemaSuperior) {
		this.idMenuSistemaSuperior = idMenuSistemaSuperior;
	}

	public String getIdMenuSistemaSuperior() {
		return idMenuSistemaSuperior;
	}

	public String getIdTipoMenu() {
		return idTipoMenu;
	}

	public void setIdTipoMenu(String idTipoMenu) {
		this.idTipoMenu = idTipoMenu;
	}

	public void setChaveFiltro(String chaveFiltro) {
		this.chaveFiltro = chaveFiltro;
	}

	public String getChaveFiltro() {
		return chaveFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

}
