package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class SistemaBean extends SATIManagedBean {

	private static final long serialVersionUID = 6558649584219323819L;
	
	private SistemaTO sistema;
	
	private Integer itensPorPagina = 15;
	
	private List<SelectItem> listaSistema;
	
	private List<TransferObject> listaSistemaDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {
			SistemaTO sistema = this.pegarValorFiltro();
			this.setListaSistemaDataTable(negocioSistema.listar(sistema, "vchDescricao"));
			return "listarSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public SistemaTO pegarValorFiltro() {
		SistemaTO sistema = new SistemaTO();
		sistema.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return sistema;
	}
	
	public String incluir() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {
			
			negocioSistema.incluir(this.getSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {
			negocioSistema.excluir(this.getSistema(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {

			negocioSistema.alterar(this.getSistema(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {
			this.setSistema(negocioSistema.consultar(new SistemaTO(this.getSistema().getId())));
			
			return "editarSistema";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setSistema( new SistemaTO());

		return "novoSistema";
	}
	
	public String consultar() {
		Sistema negocioSistema = getSATIBusinessFactory().createSistema();
		try {
			negocioSistema.retirarObjetoSessao(this.getSistema());
			this.setSistema(negocioSistema.consultar(new SistemaTO(this.getSistema().getId())));

			return "consultarSistema";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public void setSistema(SistemaTO sistema) {
		this.sistema = sistema;
	}

	public SistemaTO getSistema() {
		if (sistema == null) {
			sistema = new SistemaTO();
		}
		return sistema;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaSistemaDataTable(List<TransferObject> listaSistemaDataTable) {
		this.listaSistemaDataTable = listaSistemaDataTable;
	}

	public List<TransferObject> getListaSistemaDataTable() {
		if (listaSistemaDataTable == null) {
			listaSistemaDataTable = new ArrayList<TransferObject>();
		}
		return listaSistemaDataTable;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}
	
	public List<SelectItem> getListaSistema() {
		if(listaSistema == null){
			Sistema negocio = getSATIBusinessFactory().createSistema();
			List<TransferObject> lista = negocio.listar(new SistemaTO(), "vchNome");
			this.listaSistema = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaSistema;
	}
	
	public void setListaSistema(List<SelectItem> listaSistema) {
		this.listaSistema = listaSistema;
	}
	
}
