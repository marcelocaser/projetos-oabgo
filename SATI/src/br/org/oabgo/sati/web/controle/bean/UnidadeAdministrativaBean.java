package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.TipoUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoUnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UnidadeAdministrativa;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

/**
 * <b>Classe:</b> UnidadeAdministrativaBean.java <br>
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
public class UnidadeAdministrativaBean extends SATIManagedBean {

	private static final long serialVersionUID = -4325839843754943642L;
	
	private UnidadeAdministrativaTO unidadeAdministrativa;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaUnidadeAdministrativa;
	
	private List<SelectItem> listaTpUnidadesAdministrativa;

	private List<TransferObject> listaUnidadeAdministrativaDataTable;

	private String idTipoUnidadeAdministrativa;

	private String idUnidadeAdmSuperior;

	private String siglaFiltro;

	private String nomeFiltro;

	public String listar() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory()
				.createUnidadeAdministrativa();
		try {
			UnidadeAdministrativaTO unidadeAdministrativa = this.pegarValorFiltro();
			this.setListaUnidadeAdministrativaDataTable(negocioUnidadeAdministrativa.listar(unidadeAdministrativa, "vchNome"));
			return "listarUnidadeAdministrativa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public UnidadeAdministrativaTO pegarValorFiltro() {
		UnidadeAdministrativaTO unidadeAdministrativa = new UnidadeAdministrativaTO();
		unidadeAdministrativa.setVchSigla(Util.setString(this.getSiglaFiltro()));
		unidadeAdministrativa.setVchNome(Util.setString(this.getNomeFiltro()));
		return unidadeAdministrativa;
	}
	
	public String incluir() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
		try {

			this.getUnidadeAdministrativa().setTipoUnidadeAdministrativa(Util.setValueTO(this.getIdTipoUnidadeAdministrativa(), TipoUnidadeAdministrativaTO.class));
			
			this.getUnidadeAdministrativa().setUnidadeAdministrativaSuperior(Util.setValueTO(this.getIdUnidadeAdmSuperior(), UnidadeAdministrativaTO.class));
			
			negocioUnidadeAdministrativa.incluir(this.getUnidadeAdministrativa(), this.getUsuarioLogado());

			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);

			
			this.setIdTipoUnidadeAdministrativa(new String());
			this.setIdUnidadeAdmSuperior(new String());
			return this.novo();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String excluir() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
		try {
			negocioUnidadeAdministrativa.excluir(this.getUnidadeAdministrativa(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String salvar() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
		try {
			
			this.getUnidadeAdministrativa().setTipoUnidadeAdministrativa(Util.setValueTO(this.getIdTipoUnidadeAdministrativa(), TipoUnidadeAdministrativaTO.class));
			
			this.getUnidadeAdministrativa().setUnidadeAdministrativaSuperior(Util.setValueTO(this.getIdUnidadeAdmSuperior(), UnidadeAdministrativaTO.class));

			negocioUnidadeAdministrativa.alterar(this.getUnidadeAdministrativa(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String editar() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
		try {
			this.setUnidadeAdministrativa(negocioUnidadeAdministrativa.consultar(new UnidadeAdministrativaTO(this.getUnidadeAdministrativa().getId())));

			this.setIdUnidadeAdmSuperior(String.valueOf(this.getUnidadeAdministrativa().getUnidadeAdministrativaSuperior().getId()));
			
			this.setIdTipoUnidadeAdministrativa(String.valueOf(this.getUnidadeAdministrativa().getTipoUnidadeAdministrativa().getId()));

			return "editarUnidadeAdministrativa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	public String novo() {
		this.setUnidadeAdministrativa(new UnidadeAdministrativaTO());

		return "novaUnidadeAdministrativa";
	}

	public String consultar() {
		UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory()
				.createUnidadeAdministrativa();
		try {
			negocioUnidadeAdministrativa.retirarObjetoSessao(this.getUnidadeAdministrativa());
			this.setUnidadeAdministrativa(negocioUnidadeAdministrativa
					.consultar(new UnidadeAdministrativaTO(this.getUnidadeAdministrativa().getId())));

			return "consultarUnidadeAdministrativa";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public void setUnidadeAdministrativa(UnidadeAdministrativaTO unidadeAdministrativa) {
		this.unidadeAdministrativa = unidadeAdministrativa;
	}
	
	public UnidadeAdministrativaTO getUnidadeAdministrativa() {
		if (unidadeAdministrativa == null) {
			unidadeAdministrativa = new UnidadeAdministrativaTO();
		}
		return unidadeAdministrativa;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaUnidadeAdministrativa(List<SelectItem> listaUnidadeAdministrativa) {
		this.listaUnidadeAdministrativa = listaUnidadeAdministrativa;
	}

	public List<SelectItem> getListaUnidadeAdministrativa() {
		if (listaUnidadeAdministrativa == null) {
			UnidadeAdministrativa negocioUnidadeAdministrativa = getSATIBusinessFactory().createUnidadeAdministrativa();
			List<TransferObject> lista = negocioUnidadeAdministrativa.listar(new UnidadeAdministrativaTO(),
					"vchNome");
			listaUnidadeAdministrativa = Util.createListSelectItens(lista, "id",
					"vchNome");
		}
		return listaUnidadeAdministrativa;
	}

	public void setListaTpUnidadesAdministrativa(List<SelectItem> listaTpUnidadesAdministrativa) {
		this.listaTpUnidadesAdministrativa = listaTpUnidadesAdministrativa;
	}

	public List<SelectItem> getListaTpUnidadesAdministrativa() {
		if (listaTpUnidadesAdministrativa == null) {
			TipoUnidadeAdministrativa negocioTipoUnidadeAdministrativa = getSATIBusinessFactory().createTipoUnidadeAdministrativa();
			List<TransferObject> lista = negocioTipoUnidadeAdministrativa.listar(new TipoUnidadeAdministrativaTO(), "vchDescricao");
			listaTpUnidadesAdministrativa = Util.createListSelectItens(lista, "id", "vchDescricao");
		}
		return listaTpUnidadesAdministrativa;
	}

	public void setListaUnidadeAdministrativaDataTable(
			List<TransferObject> listaUnidadeAdministrativaDataTable) {
		this.listaUnidadeAdministrativaDataTable = listaUnidadeAdministrativaDataTable;
	}

	public List<TransferObject> getListaUnidadeAdministrativaDataTable() {
		if (listaUnidadeAdministrativaDataTable == null) {
			listaUnidadeAdministrativaDataTable = new ArrayList<TransferObject>();
		}
		return listaUnidadeAdministrativaDataTable;
	}

	public void setIdTipoUnidadeAdministrativa(String idTipoUnidadeAdministrativa) {
		this.idTipoUnidadeAdministrativa = idTipoUnidadeAdministrativa;
	}

	public String getIdTipoUnidadeAdministrativa() {
		return idTipoUnidadeAdministrativa;
	}

	public void setIdUnidadeAdmSuperior(String idUnidadeAdmSuperior) {
		this.idUnidadeAdmSuperior = idUnidadeAdmSuperior;
	}

	public String getIdUnidadeAdmSuperior() {
		return idUnidadeAdmSuperior;
	}

	public void setSiglaFiltro(String siglaFiltro) {
		this.siglaFiltro = siglaFiltro;
	}

	public String getSiglaFiltro() {
		return siglaFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

}
