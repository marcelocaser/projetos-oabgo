package br.org.oabgo.saeo.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import br.org.oabgo.saeo.negocio.controle.entidade.CertameTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Certame;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumTipoMensagem;
import br.org.oabgo.saeo.web.controle.SAEOManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class CertameBean extends SAEOManagedBean {

	private static final long serialVersionUID = -19304840609432994L;
	
	private CertameTO certame;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaCertameDataTable;
	
	private String descricaoFiltro;
	
	public String listar() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			CertameTO certame = this.pegarValorFiltro();
			this.setListaCertameDataTable(negocioCertame.listar(certame, "vchDescricao"));
			return "listarCertame";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public CertameTO pegarValorFiltro() {
		CertameTO certame = new CertameTO();
		certame.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return certame;
	}
	
	public String incluir() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			
			negocioCertame.incluir(this.getCertame(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SAEOEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			negocioCertame.excluir(this.getCertame(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SAEOEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			
			negocioCertame.alterar(this.getCertame(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SAEOEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			this.setCertame(negocioCertame.consultar(new CertameTO(this.getCertame().getId())));
			
			return "editarCertame";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setCertame(new CertameTO());

		return "novoCertame";
	}
	
	public String consultar() {
		Certame negocioCertame = getSAEOBusinessFactory().createCertame();
		try {
			negocioCertame.retirarObjetoSessao(this.getCertame());
			this.setCertame(negocioCertame.consultar(new CertameTO(this.getCertame().getId())));

			return "consultarCertame";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public CertameTO getCertame() {
		if (certame == null) {
			certame = new CertameTO();
		}
		return certame;
	}

	public void setCertame(CertameTO certame) {
		this.certame = certame;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaCertameDataTable() {
		if (listaCertameDataTable == null) {
			listaCertameDataTable = new ArrayList<TransferObject>();
		}
		return listaCertameDataTable;
	}

	public void setListaCertameDataTable(List<TransferObject> listaCertameDataTable) {
		this.listaCertameDataTable = listaCertameDataTable;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}
	
}
