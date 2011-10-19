package br.org.oabgo.sati.web.controle.bean;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Empresa;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class EmpresaBean extends SATIManagedBean {

	private static final long serialVersionUID = -8166452886616764708L;
	
	private EmpresaTO empresa;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaEmpresaDataTable;
	
	private String cnpjFiltro;
	
	private String razaoSocialFiltro;
	
	private String nomeFantasiaFiltro;
	
	public String listar() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			EmpresaTO empresa = this.pegarValorFiltro();
			this.setListaEmpresaDataTable(negocioEmpresa.listar(empresa, "vchCNPJ"));
			this.setListaEmpresaDataTable(negocioEmpresa.listar(empresa, "vchRazaoSocial"));
			this.setListaEmpresaDataTable(negocioEmpresa.listar(empresa, "vchNomeFantasia"));
			return "listarEmpresa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public EmpresaTO pegarValorFiltro() {
		EmpresaTO empresa = new EmpresaTO();
		empresa.setVchCNPJ(Util.setString(this.getCnpjFiltro()));
		empresa.setVchRazaoSocial(Util.setString(this.getRazaoSocialFiltro()));
		empresa.setVchNomeFantasia(Util.setString(this.getNomeFantasiaFiltro()));
		return empresa;
	}
	
	public String incluir() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			
			negocioEmpresa.incluir(this.getEmpresa(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			negocioEmpresa.excluir(this.getEmpresa(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			
			negocioEmpresa.alterar(this.getEmpresa(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			this.setEmpresa(negocioEmpresa.consultar(new EmpresaTO(this.getEmpresa().getId())));
			
			return "editarEmpresa";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setEmpresa(new EmpresaTO());

		return "novaEmpresa";
	}
	
	public String consultar() {
		Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
		try {
			negocioEmpresa.retirarObjetoSessao(this.getEmpresa());
			this.setEmpresa(negocioEmpresa.consultar(new EmpresaTO(this.getEmpresa().getId())));

			return "consultarEmpresa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public EmpresaTO getEmpresa() {
		if (empresa == null) {
			empresa = new EmpresaTO();
		}
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaEmpresaDataTable() {
		return listaEmpresaDataTable;
	}

	public void setListaEmpresaDataTable(List<TransferObject> listaEmpresaDataTable) {
		this.listaEmpresaDataTable = listaEmpresaDataTable;
	}

	public String getCnpjFiltro() {
		return cnpjFiltro;
	}

	public void setCnpjFiltro(String cnpjFiltro) {
		this.cnpjFiltro = cnpjFiltro;
	}

	public String getRazaoSocialFiltro() {
		return razaoSocialFiltro;
	}

	public void setRazaoSocialFiltro(String razaoSocialFiltro) {
		this.razaoSocialFiltro = razaoSocialFiltro;
	}

	public String getNomeFantasiaFiltro() {
		return nomeFantasiaFiltro;
	}

	public void setNomeFantasiaFiltro(String nomeFantasiaFiltro) {
		this.nomeFantasiaFiltro = nomeFantasiaFiltro;
	}
	
}
