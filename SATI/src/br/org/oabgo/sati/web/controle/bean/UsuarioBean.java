package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import br.org.oabgo.sati.negocio.controle.SATILDAPUsuario;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.PerfilAcesso;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.sati.negocio.enumerador.SATIEnumTipoMensagem;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

/**
 * <b>Classe:</b> UsuarioBean.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.web.controle.bean <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class UsuarioBean extends SATIManagedBean {

	private static final long serialVersionUID = 1336108950785391584L;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaUsuario;

	private UsuarioTO usuario;

	private SATILDAPUsuario usuarioLDAP;

	private List<TransferObject> listaUsuarioDataTable;

	private List<SelectItem> listaPerfilAcesso;

	private String idPerfilAcesso;

	// valores dos Filtros
	private String userNameFiltro;

	private String nomeFiltro;

	/**
	 * Lista todos os usuarios cadastrados.
	 * 
	 * @return String
	 */
	public String listar() {
		Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();
		try {
			UsuarioTO usuario = this.pegarValorFiltro();
			this.setListaUsuarioDataTable(negocioUsuario.listar(usuario));

			return "listarUsuario";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Retorna um UsuarioTO com os valores dos filtros setados.
	 */
	public UsuarioTO pegarValorFiltro() {
		UsuarioTO usuario = new UsuarioTO();
		usuario.setVchLogin(Util.setString(this.getUserNameFiltro()));
		usuario.setVchNome(Util.setString(this.getNomeFiltro()));
		return usuario;
	}

	/**
	 * Inclui um novo UsuarioTO.
	 * 
	 * @return String
	 */
	public String incluir() {
		Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();
		try {

			this.getUsuario().setPerfilAcesso(
					Util.setValueTO(this.getIdPerfilAcesso(),
							PerfilAcessoTO.class));
			negocioUsuario.incluir(this.getUsuario(), this.getUsuarioLogado());

			setMessage("msgSucessoIncluir", SATIEnumTipoMensagem.SUCESSO);

			this.setUsuarioLDAP(new SATILDAPUsuario());
			this.setIdPerfilAcesso(new String());

			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Exclui um objeto UsuarioTO.
	 * 
	 * @return String
	 */
	public String excluir() {
		Usuario negocio = getSATIBusinessFactory().createUsuario();
		try {
			negocio.excluir(this.getUsuario(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", SATIEnumTipoMensagem.SUCESSO);
			return this.listar();
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Salva as alterações realizadas em um UsuarioTO.
	 * 
	 * @return String
	 */
	public String salvar() {
		Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();
		try {
			UsuarioTO usuario = getUsuario();

			// popula o perfil de acesso
			if (getIdPerfilAcesso() != null && !getIdPerfilAcesso().isEmpty()) {
				PerfilAcessoTO perfil = new PerfilAcessoTO();
				perfil.setId(Long.valueOf(getIdPerfilAcesso()));
				usuario.setPerfilAcesso(perfil);
			}

			negocioUsuario.alterar(usuario, this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", SATIEnumTipoMensagem.SUCESSO);

			this.setUsuarioLDAP(new SATILDAPUsuario());
			this.setIdPerfilAcesso(new String());

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
		Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();
		try {
			this.setUsuario(negocioUsuario.consultar(new UsuarioTO(this
					.getUsuario().getId())));

			if (this.getUsuario().getPerfilAcesso() != null) {
				this.setIdPerfilAcesso(this.getUsuario().getPerfilAcesso()
						.getId().toString());
			} else {
				this.setIdPerfilAcesso(null);
			}

			return "editarUsuario";
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
		this.setUsuario(new UsuarioTO());

		if (this.getUsuarioLDAP() != null) {
			this.getUsuario().setVchLogin(
					this.getUsuarioLDAP().getUserPrincipalName());
			this.getUsuario()
					.setVchNome(this.getUsuarioLDAP().getDisplayName());
		}

		return "novoUsuario";
	}

	/**
	 * Consulta um UsuarioTO.
	 * 
	 * @return String
	 */
	public String consultar() {
		Usuario negocio = getSATIBusinessFactory().createUsuario();
		try {
			negocio.retirarObjetoSessao(usuario);
			this.setUsuario(negocio.consultar(new UsuarioTO(this.getUsuario()
					.getId())));

			return "consultarUsuario";
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}

	/**
	 * Retorna o valor armazenado no atributo listaUsuario.
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaUsuario() {
		if (listaUsuario == null) {
			Usuario negocio = getSATIBusinessFactory().createUsuario();
			List<TransferObject> lista = negocio.listar(new UsuarioTO(),
					"vchNome");
			this.listaUsuario = Util.createListSelectItens(lista, "id",
					"vchNome");
		}
		return listaUsuario;
	}

	/**
	 * Retorna o valor armazenado no atributo listaPerfilAcesso.
	 * 
	 * @return List<SelectItem>
	 */
	public List<SelectItem> getListaPerfilAcesso() {
		if (listaPerfilAcesso == null) {
			// lista os atributos em função das páginas
			PerfilAcesso negocio = getSATIBusinessFactory()
					.createPerfilAcesso();
			List<TransferObject> lista = negocio.listar(new PerfilAcessoTO(),
					"vchDescricao");
			listaPerfilAcesso = new ArrayList<SelectItem>();
			for (TransferObject bean : lista) {
				PerfilAcessoTO atr = (PerfilAcessoTO) bean;
				listaPerfilAcesso.add(new SelectItem(atr.getId().toString(),
						atr.getVchDescricao()));
			}
		}
		return listaPerfilAcesso;
	}

	/**
	 * Insere um valor ao atributo listaUsuario.
	 * 
	 * @param listaUsuario
	 *            - List<SelectItem>
	 */
	public void setListaUsuario(List<SelectItem> listaUsuario) {
		this.listaUsuario = listaUsuario;
	}

	/**
	 * Retorna o valor armazenado no atributo de UsuarioTO.
	 * 
	 * @return UsuarioTO
	 */
	public UsuarioTO getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioTO();
		}
		return usuario;
	}

	/**
	 * Insere um valor ao atributo de UsuarioTO.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 */
	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public void setUsuarioLDAP(SATILDAPUsuario usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

	public SATILDAPUsuario getUsuarioLDAP() {
		return usuarioLDAP;
	}

	public List<TransferObject> getListaUsuarioDataTable() {
		if (listaUsuarioDataTable == null) {
			listaUsuarioDataTable = new ArrayList<TransferObject>();
		}
		return listaUsuarioDataTable;
	}

	public void setListaUsuarioDataTable(
			List<TransferObject> listaUsuarioDataTable) {
		this.listaUsuarioDataTable = listaUsuarioDataTable;
	}

	public String getUserNameFiltro() {
		return userNameFiltro;
	}

	public void setUserNameFiltro(String userNameFiltro) {
		this.userNameFiltro = userNameFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public void setListaPerfilAcesso(List<SelectItem> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

	public String getIdPerfilAcesso() {
		return idPerfilAcesso;
	}

	public void setIdPerfilAcesso(String idPerfilAcesso) {
		this.idPerfilAcesso = idPerfilAcesso;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

}
