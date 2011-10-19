package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.TipoUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoUnidadeAdministrativa;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class TipoUnidadeAdministrativaBean extends SATIManagedBean {

	private static final long serialVersionUID = 8551080504736149572L;
	
	private TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaTipoUnidadeAdministrativaDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa = this.pegarValorFiltro();
			this.setListaTipoUnidadeAdministrativaDataTable(negocioTipoUnidadeAdministrativa.listar(tipoUnidadeAdministrativa, "vchDescricao"));
			return "listarTipoUnidadeAdministrativa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public TipoUnidadeAdministrativaTO pegarValorFiltro() {
		TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa = new TipoUnidadeAdministrativaTO();
		tipoUnidadeAdministrativa.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return tipoUnidadeAdministrativa;
	}
	
	public String incluir() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			negocioTipoUnidadeAdministrativa.incluir(this.getTipoUnidadeAdministrativa(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			negocioTipoUnidadeAdministrativa.excluir(this.getTipoUnidadeAdministrativa(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			negocioTipoUnidadeAdministrativa.alterar(this.getTipoUnidadeAdministrativa(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			this.setTipoUnidadeAdministrativa(negocioTipoUnidadeAdministrativa.consultar(new TipoUnidadeAdministrativaTO(this.getTipoUnidadeAdministrativa().getId())));

			return "editarTipoUnidadeAdministrativa";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setTipoUnidadeAdministrativa(new TipoUnidadeAdministrativaTO());

		return "novoTipoUnidadeAdministrativa";
	}
	
	public String consultar() {
		TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
		try {
			negocioTipoUnidadeAdministrativa.retirarObjetoSessao(this.getTipoUnidadeAdministrativa());
			this.setTipoUnidadeAdministrativa(negocioTipoUnidadeAdministrativa.consultar(new TipoUnidadeAdministrativaTO(this.getTipoUnidadeAdministrativa().getId())));

			return "consultarTipoUnidadeAdministrativa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public void setTipoUnidadeAdministrativa(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) {
		this.tipoUnidadeAdministrativa = tipoUnidadeAdministrativa;
	}

	public TipoUnidadeAdministrativaTO getTipoUnidadeAdministrativa() {
		if (tipoUnidadeAdministrativa == null) {
			tipoUnidadeAdministrativa = new TipoUnidadeAdministrativaTO();
		}
		return tipoUnidadeAdministrativa;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaTipoUnidadeAdministrativaDataTable(
			List<TransferObject> listaTipoUnidadeAdministrativaDataTable) {
		this.listaTipoUnidadeAdministrativaDataTable = listaTipoUnidadeAdministrativaDataTable;
	}

	public List<TransferObject> getListaTipoUnidadeAdministrativaDataTable() {
		if (listaTipoUnidadeAdministrativaDataTable == null) {
			listaTipoUnidadeAdministrativaDataTable = new ArrayList<TransferObject>();
		}
		return listaTipoUnidadeAdministrativaDataTable;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

}
