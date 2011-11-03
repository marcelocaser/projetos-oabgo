package br.com.flavios.pbpf.web.controle.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;

import br.com.flavios.pbpf.negocio.controle.PBPFLDAPUsuario;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.negocio.enumerador.PBPFEnumTipoMensagem;
import br.com.flavios.pbpf.web.controle.PBPFManagedBean;
import core.dao.TransferObject;
import core.utilitario.Util;

/**
 * <b>Classe:</b> UsuarioBean.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.web.controle.bean <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 * Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@ManagedBean
@RequestScoped
public class UsuarioBean extends PBPFManagedBean {

	private static final long serialVersionUID = 1336108950785391584L;

	private Integer itensPorPagina = 15;

	private List<SelectItem> listaUsuario;

	private UsuarioTO usuario;

	private PBPFLDAPUsuario usuarioLDAP;

	private List<TransferObject> listaUsuarioDataTable;

	// valores dos Filtros
	private String userNameFiltro;

	private String nomeFiltro;

	/**
	 * Lista todos os usuarios cadastrados.
	 * 
	 * @return String
	 */
	public String listar() {
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();
		try {
			UsuarioTO usuario = this.pegarValorFiltro();
			this.setListaUsuarioDataTable(negocioUsuario.listar(usuario));

			return URL_USUARIO_LISTAR;
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
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();
		try {

			negocioUsuario.incluir(this.getUsuario(), this.getUsuarioLogado());

			setMessage("msgSucessoIncluir", PBPFEnumTipoMensagem.SUCESSO);

			this.setUsuarioLDAP(new PBPFLDAPUsuario());

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
		Usuario negocio = getPBPFBusinessFactory().createUsuario();
		try {
			negocio.excluir(this.getUsuario(), getUsuarioLogado());
			setMessage("msgSucessoExcluir", PBPFEnumTipoMensagem.SUCESSO);
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
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();
		try {
			UsuarioTO usuario = getUsuario();

			negocioUsuario.alterar(usuario, this.getUsuarioLogado());
			setMessage("msgSucessoAlterar", PBPFEnumTipoMensagem.SUCESSO);

			this.setUsuarioLDAP(new PBPFLDAPUsuario());

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
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();
		try {
			this.setUsuario(negocioUsuario.consultar(new UsuarioTO(this
					.getUsuario().getId())));

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
		Usuario negocio = getPBPFBusinessFactory().createUsuario();
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
			Usuario negocio = getPBPFBusinessFactory().createUsuario();
			List<TransferObject> lista = negocio.listar(new UsuarioTO(),
					"vchNome");
			this.listaUsuario = Util.createListSelectItens(lista, "id",
					"vchNome");
		}
		return listaUsuario;
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

	public void setUsuarioLDAP(PBPFLDAPUsuario usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

	public PBPFLDAPUsuario getUsuarioLDAP() {
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

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

}
