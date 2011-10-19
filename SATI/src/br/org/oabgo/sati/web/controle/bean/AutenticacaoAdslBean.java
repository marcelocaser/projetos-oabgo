package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.AutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.EmpresaTO;
import br.org.oabgo.sati.negocio.controle.entidade.StatusAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Empresa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.StatusAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UnidadeAdministrativa;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

public class AutenticacaoAdslBean extends SATIManagedBean {

	private static final long serialVersionUID = -744779652424356290L;
	
	private AutenticacaoAdslTO autenticacaoAdsl;
	
	private Integer itensPorPagina = 15;
	
	private List<TransferObject> listaAutenticacaoAdslDataTable;
	
	private List<SelectItem> listaEmpresa;
	
	private List<SelectItem> listaUnidadeAdministrativa;
	
	private List<SelectItem> listaStatusAutenticacaoAdsl;
	
	private String idEmpresa;
	
	private String idUnidadeAdministrativa;
	
	private String idStatusAutenticacaoAdsl;
	
	private String emailFiltro;
	
	public String listar() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			AutenticacaoAdslTO autenticacaoAdsl = this.pegarValorFiltro();
			this.setListaAutenticacaoAdslDataTable(negocioAutenticacaoAdsl.listar(autenticacaoAdsl, "vchContaEmail"));
			return "listarAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public AutenticacaoAdslTO pegarValorFiltro() {
		AutenticacaoAdslTO autenticacaoAdsl = new AutenticacaoAdslTO();
		autenticacaoAdsl.setVchContaEmail(Util.setString(this.getEmailFiltro()));
		return autenticacaoAdsl;
	}
	
	public String incluir() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			
			this.getAutenticacaoAdsl().setEmpresa(Util.setValueTO(this.getIdEmpresa(), EmpresaTO.class));
		
			this.getAutenticacaoAdsl().setStatusAutenticacaoADSL(Util.setValueTO(this.getIdStatusAutenticacaoAdsl(), StatusAutenticacaoAdslTO.class));
			
			this.getAutenticacaoAdsl().setUnidadeAdministrativa(Util.setValueTO(this.getIdUnidadeAdministrativa(), UnidadeAdministrativaTO.class));
			
			negocioAutenticacaoAdsl.incluir(this.getAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);
			
			this.setIdEmpresa(new String());
			this.setIdStatusAutenticacaoAdsl(new String());
			this.setIdUnidadeAdministrativa(new String());
			
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			negocioAutenticacaoAdsl.excluir(this.getAutenticacaoAdsl(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			
			return this.listar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			
			this.getAutenticacaoAdsl().setEmpresa(Util.setValueTO(this.getIdEmpresa(), EmpresaTO.class));
			
			this.getAutenticacaoAdsl().setStatusAutenticacaoADSL(Util.setValueTO(this.getIdStatusAutenticacaoAdsl(), StatusAutenticacaoAdslTO.class));
		
			this.getAutenticacaoAdsl().setUnidadeAdministrativa(Util.setValueTO(this.getIdUnidadeAdministrativa(), UnidadeAdministrativaTO.class));
			
			negocioAutenticacaoAdsl.alterar(this.getAutenticacaoAdsl(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			this.setAutenticacaoAdsl(negocioAutenticacaoAdsl.consultar(new AutenticacaoAdslTO(this.getAutenticacaoAdsl().getId())));
			
			this.setIdEmpresa(String.valueOf(this.getAutenticacaoAdsl().getEmpresa().getId()));
			
			this.setIdStatusAutenticacaoAdsl(String.valueOf(this.getAutenticacaoAdsl().getStatusAutenticacaoADSL().getId()));
			
			this.setIdUnidadeAdministrativa(String.valueOf(this.getAutenticacaoAdsl().getUnidadeAdministrativa().getId()));

			return "editarAutenticacaoAdsl";
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String novo() {
		this.setAutenticacaoAdsl(new AutenticacaoAdslTO());

		return "novoAutenticacaoAdsl";
	}
	
	public String consultar() {
		AutenticacaoAdsl negocioAutenticacaoAdsl = getSATIBusinessFactory().createAutenticacaoAdsl();
		try {
			negocioAutenticacaoAdsl.retirarObjetoSessao(this.getAutenticacaoAdsl());
			this.setAutenticacaoAdsl(negocioAutenticacaoAdsl.consultar(new AutenticacaoAdslTO(this.getAutenticacaoAdsl().getId())));

			return "consultarAutenticacaoAdsl";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public AutenticacaoAdslTO getAutenticacaoAdsl() {
		if (autenticacaoAdsl == null) {
			autenticacaoAdsl = new AutenticacaoAdslTO();
		}
		return autenticacaoAdsl;
	}

	public void setAutenticacaoAdsl(AutenticacaoAdslTO autenticacaoAdsl) {
		this.autenticacaoAdsl = autenticacaoAdsl;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public List<TransferObject> getListaAutenticacaoAdslDataTable() {
		if (listaAutenticacaoAdslDataTable == null) {
			listaAutenticacaoAdslDataTable = new ArrayList<TransferObject>();
		}
		return listaAutenticacaoAdslDataTable;
	}

	public void setListaAutenticacaoAdslDataTable(
			List<TransferObject> listaAutenticacaoAdslDataTable) {
		this.listaAutenticacaoAdslDataTable = listaAutenticacaoAdslDataTable;
	}

	public List<SelectItem> getListaEmpresa() {
		if (listaEmpresa == null) {
			Empresa negocioEmpresa = getSATIBusinessFactory().createEmpresa();
			List<TransferObject> lista = negocioEmpresa.listar(new EmpresaTO(),"vchNomeFantasia");
			listaEmpresa = Util.createListSelectItens(lista, "id", "vchNomeFantasia");
		}
		return listaEmpresa;
	}

	public void setListaEmpresa(List<SelectItem> listaEmpresa) {
		this.listaEmpresa = listaEmpresa;
	}

	public List<SelectItem> getListaUnidadeAdministrativa() {
		if (listaUnidadeAdministrativa == null) {
			UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
			List<TransferObject> lista = negocioUnidadeAdministrativa.listar(new UnidadeAdministrativaTO(), "vchNome");
			listaUnidadeAdministrativa = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaUnidadeAdministrativa;
	}

	public void setListaUnidadeAdministrativa(
			List<SelectItem> listaUnidadeAdministrativa) {
		this.listaUnidadeAdministrativa = listaUnidadeAdministrativa;
	}

	public List<SelectItem> getListaStatusAutenticacaoAdsl() {
		if (listaStatusAutenticacaoAdsl == null) {
			StatusAutenticacaoAdsl negocioStatusAutenticacaoAdsl = getSATIBusinessFactory().createStatusAutenticacaoAdsl();
			List<TransferObject> lista = negocioStatusAutenticacaoAdsl.listar(new StatusAutenticacaoAdslTO(),"vchDescricao");
			listaStatusAutenticacaoAdsl = Util.createListSelectItens(lista, "id", "vchDescricao");
		}
		return listaStatusAutenticacaoAdsl;
	}

	public void setListaStatusAutenticacaoAdsl(
			List<SelectItem> listaStatusAutenticacaoAdsl) {
		this.listaStatusAutenticacaoAdsl = listaStatusAutenticacaoAdsl;
	}

	public String getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(String idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getIdUnidadeAdministrativa() {
		return idUnidadeAdministrativa;
	}

	public void setIdUnidadeAdministrativa(String idUnidadeAdministrativa) {
		this.idUnidadeAdministrativa = idUnidadeAdministrativa;
	}

	public String getIdStatusAutenticacaoAdsl() {
		return idStatusAutenticacaoAdsl;
	}

	public void setIdStatusAutenticacaoAdsl(String idStatusAutenticacaoAdsl) {
		this.idStatusAutenticacaoAdsl = idStatusAutenticacaoAdsl;
	}

	public String getEmailFiltro() {
		return emailFiltro;
	}

	public void setEmailFiltro(String emailFiltro) {
		this.emailFiltro = emailFiltro;
	}
	
}
