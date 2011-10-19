package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.StatusAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.StatusAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class StatusAutenticacaoAdslBean extends SATIManagedBean {

	private static final long serialVersionUID = -616836915574159336L;
	
	private StatusAutenticacaoAdslTO statusAutenticacaoAdsl;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaStatusAutenticacaoAdslDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl = this.pegarValorFiltro();
			this.setListaStatusAutenticacaoAdslDataTable(negocioStatusAutenticacaoAdsl.listar(statusAutenticacaoAdsl, "vchDescricao"));
			return "listarStatusAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public StatusAutenticacaoAdslTO pegarValorFiltro() {
		StatusAutenticacaoAdslTO statusAutenticacaoAdsl = new StatusAutenticacaoAdslTO();
		statusAutenticacaoAdsl.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return statusAutenticacaoAdsl;
	}
	
	public String incluir() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			
			negocioStatusAutenticacaoAdsl.incluir(this.getStatusAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			negocioStatusAutenticacaoAdsl.excluir(this.getStatusAutenticacaoAdsl(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			
			negocioStatusAutenticacaoAdsl.alterar(this.getStatusAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			this.setStatusAutenticacaoAdsl(negocioStatusAutenticacaoAdsl.consultar(new StatusAutenticacaoAdslTO(this.getStatusAutenticacaoAdsl().getId())));

			return "editarStatusAutenticacaoAdsl";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setStatusAutenticacaoAdsl(new StatusAutenticacaoAdslTO());

		return "novoStatusAutenticacaoAdsl";
	}
	
	public String consultar() {
		StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
		try {
			negocioStatusAutenticacaoAdsl.retirarObjetoSessao(this.getStatusAutenticacaoAdsl());
			this.setStatusAutenticacaoAdsl(negocioStatusAutenticacaoAdsl.consultar(new StatusAutenticacaoAdslTO(this.getStatusAutenticacaoAdsl().getId())));

			return "consultarStatusAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public StatusAutenticacaoAdslTO getStatusAutenticacaoAdsl() {
		if (statusAutenticacaoAdsl == null) {
			statusAutenticacaoAdsl = new StatusAutenticacaoAdslTO();
		}
		return statusAutenticacaoAdsl;
	}

	public void setStatusAutenticacaoAdsl(
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl) {
		this.statusAutenticacaoAdsl = statusAutenticacaoAdsl;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaStatusAutenticacaoAdslDataTable() {
		if (listaStatusAutenticacaoAdslDataTable == null) {
			listaStatusAutenticacaoAdslDataTable = new ArrayList<TransferObject>();
		}
		return listaStatusAutenticacaoAdslDataTable;
	}

	public void setListaStatusAutenticacaoAdslDataTable(
			List<TransferObject> listaStatusAutenticacaoAdslDataTable) {
		this.listaStatusAutenticacaoAdslDataTable = listaStatusAutenticacaoAdslDataTable;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
