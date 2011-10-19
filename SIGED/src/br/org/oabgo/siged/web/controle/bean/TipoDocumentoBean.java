package br.org.oabgo.siged.web.controle.bean;

import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.TipoDocumentoTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.TipoDocumento;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import br.org.oabgo.siged.web.controle.SIGEDManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class TipoDocumentoBean extends SIGEDManagedBean {

	private static final long serialVersionUID = 3063183596195699305L;
	
	private TipoDocumentoTO tipoDocumento;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaTipoDocumentoDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			TipoDocumentoTO tipoDocumento = this.pegarValorFiltro();
			this.setListaTipoDocumentoDataTable(negocioTipoDocumento.listar(tipoDocumento, "vchDescricao"));
			return "listarTipoDocumento";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public TipoDocumentoTO pegarValorFiltro() {
		TipoDocumentoTO tipoDocumento = new TipoDocumentoTO();
		tipoDocumento.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return tipoDocumento;
	}
	
	public String incluir() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			
			negocioTipoDocumento.incluir(this.getTipoDocumento(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SIGEDEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			negocioTipoDocumento.excluir(this.getTipoDocumento(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SIGEDEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			
			negocioTipoDocumento.alterar(this.getTipoDocumento(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SIGEDEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			this.setTipoDocumento(negocioTipoDocumento.consultar(new TipoDocumentoTO(this.getTipoDocumento().getId())));
			
			return "editarTipoDocumento";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setTipoDocumento(new TipoDocumentoTO());

		return "novoTipoDocumento";
	}
	
	public String consultar() {
		TipoDocumento negocioTipoDocumento = getSIGEDBusinessFactory().createTipoDocumento();
		try {
			negocioTipoDocumento.retirarObjetoSessao(this.getTipoDocumento());
			this.setTipoDocumento(negocioTipoDocumento.consultar(new TipoDocumentoTO(this.getTipoDocumento().getId())));

			return "consultarTipoDocumento";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public TipoDocumentoTO getTipoDocumento() {
		if (tipoDocumento == null) {
			tipoDocumento = new TipoDocumentoTO();
		}
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaTipoDocumentoDataTable() {
		return listaTipoDocumentoDataTable;
	}

	public void setListaTipoDocumentoDataTable(
			List<TransferObject> listaTipoDocumentoDataTable) {
		this.listaTipoDocumentoDataTable = listaTipoDocumentoDataTable;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
