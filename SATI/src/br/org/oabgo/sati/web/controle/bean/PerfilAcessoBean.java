package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.PerfilAcesso;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

/**
 * <b>Classe:</b> PerfilAcessoBean.java <br>
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
public class PerfilAcessoBean extends SATIManagedBean {

	private static final long serialVersionUID = -6347181912214495096L;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaPerfilAcesso;

	private PerfilAcessoTO perfilAcesso;

	private List<TransferObject> listaPerfilAcessoDataTable;

	// valores dos Filtros
	private String nomeFiltro;

	private String descricaoFiltro;

	/**
	 * Lista todos os perfis de acesso cadastrados.
	 * 
	 * @return String
	 */
	public String listar() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			PerfilAcessoTO perfilAcesso = this.pegarValorFiltro();
			this.setListaPerfilAcessoDataTable(negocioPerfilAcesso.listar(perfilAcesso, "vchNome"));

			return "listarPerfilAcesso";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Retorna um Objeto com os valores dos filtros setados.
	 */
	public PerfilAcessoTO pegarValorFiltro() {
		PerfilAcessoTO perfilAcesso = new PerfilAcessoTO();
		perfilAcesso.setVchNome(Util.setString(this.getNomeFiltro()));
		perfilAcesso.setVchDescricao(Util.setString(this.getDescricaoFiltro()));
		return perfilAcesso;
	}

	/**
	 * Inclui um novo objeto.
	 * 
	 * @return String
	 */
	public String incluir() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			negocioPerfilAcesso.incluir(this.getPerfilAcesso(), this.getUsuarioLogado());
			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);

			return this.novo();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Exclui um objeto.
	 * 
	 * @return String
	 */
	public String excluir() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			negocioPerfilAcesso.excluir(this.getPerfilAcesso(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Salva as alterações realizadas em um registro.
	 * 
	 * @return String
	 */
	public String salvar() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			negocioPerfilAcesso.alterar(this.getPerfilAcesso(), this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			return this.consultar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Direciona para a página de edição.
	 * 
	 * @return String
	 */
	public String editar() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			this.setPerfilAcesso(negocioPerfilAcesso.consultar(new PerfilAcessoTO(this.getPerfilAcesso().getId())));

			return "editarPerfilAcesso";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Direciona para a página de inclusão.
	 * 
	 * @return String
	 */
	public String novo() {
		this.setPerfilAcesso(new PerfilAcessoTO());

		return "novoPerfilAcesso";
	}

	/**
	 * Consulta um objeto Usuario.
	 * 
	 * @return String
	 */
	public String consultar() {
		PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
		try {
			negocioPerfilAcesso.retirarObjetoSessao(this.getPerfilAcesso());
			this.setPerfilAcesso(negocioPerfilAcesso.consultar(new PerfilAcessoTO(this.getPerfilAcesso().getId())));

			return "consultarPerfilAcesso";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Retorna o valor armazenado no atributo perfilAcesso.
	 * 
	 * @return PerfilAcessoTO
	 */
	public PerfilAcessoTO getPerfilAcesso() {
		if (perfilAcesso == null) {
			perfilAcesso = new PerfilAcessoTO();
		}
		return perfilAcesso;
	}

	/**
	 * Insere um valor ao atributo perfilAcesso.
	 * 
	 * @param usuario
	 *            - PerfilAcessoTO
	 */
	public void setPerfilAcesso(PerfilAcessoTO perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	/**
	 * Retorna o valor armazenado no atributo listaPerfilAcesso.
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaPerfilAcesso() {
		if (listaPerfilAcesso == null) {
			PerfilAcesso negocioPerfilAcesso = getSATIBusinessFactory().createPerfilAcesso();
			List<TransferObject> lista = negocioPerfilAcesso
					.listar(new PerfilAcessoTO(), "vchNome");
			listaPerfilAcesso = Util.createListSelectItens(lista, "id", "vchNome");
		}
		return listaPerfilAcesso;
	}

	/**
	 * Insere um valor ao atributo listaPerfilAcesso.
	 * 
	 * @param listaPerfilAcesso
	 *            - List<SelectItem>
	 */
	public void setListaPerfilAcesso(List<SelectItem> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

	public List<TransferObject> getListaPerfilAcessoDataTable() {
		if (listaPerfilAcessoDataTable == null) {
			listaPerfilAcessoDataTable = new ArrayList<TransferObject>();
		}
		return listaPerfilAcessoDataTable;
	}

	public void setListaPerfilAcessoDataTable(List<TransferObject> listaPerfilAcessoDataTable) {
		this.listaPerfilAcessoDataTable = listaPerfilAcessoDataTable;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getDescricaoFiltro() {
		return descricaoFiltro;
	}

	public void setDescricaoFiltro(String descricaoFiltro) {
		this.descricaoFiltro = descricaoFiltro;
	}

}
