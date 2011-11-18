package br.com.flavios.pbpf.web.controle.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import br.com.flavios.pbpf.negocio.controle.PBPFLDAPParametrosConfig;
import br.com.flavios.pbpf.negocio.controle.PBPFLDAPServiceLocator;
import br.com.flavios.pbpf.negocio.controle.PBPFLDAPUsuario;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.web.controle.PBPFManagedBean;
import core.dao.TransferObject;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> ActiveDirectoryBean.java <br>
 * <b>Descrição:</b>		   <br>
 *
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.web.controle.bean <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 *    Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@ManagedBean
@RequestScoped
public class ActiveDirectoryBean extends PBPFManagedBean {

	private static final long serialVersionUID = -7789413386568073808L;

	private PBPFLDAPUsuario usuarioLDAP;

	private PBPFLDAPServiceLocator ldap;

	private Integer itensPorPagina = 15;

	private List<PBPFLDAPUsuario> listaUsuarioLDAPDataTable;

	private List<TransferObject> listaUsuarioCadastro;
	
	private String filter;

	private String loginFiltro;

	private String nomeFiltro;
	
	@SuppressWarnings("unchecked")
	public String listar() {

		try {
			
			DirContext ctx = this.getLdap().getContext();

			SearchControls ctls = new SearchControls();

			ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration answer = ctx.search(PBPFLDAPParametrosConfig.BASE_DN,
					pegarValorFiltro(), ctls);
			
			this.setListaUsuarioLDAPDataTable(new ArrayList<PBPFLDAPUsuario>());

			while (answer.hasMoreElements()) {

				SearchResult sr = (SearchResult) answer.next();
				Attributes attribs = sr.getAttributes();
				setUsuarioLDAP(new PBPFLDAPUsuario());

				this.getUsuarioLDAP().setDisplayName(getAtributo(PBPFLDAPParametrosConfig.displayName,attribs));
				this.getUsuarioLDAP().setGivenName(getAtributo(PBPFLDAPParametrosConfig.givenName, attribs));
				this.getUsuarioLDAP().setMemberOf(getAtributo(PBPFLDAPParametrosConfig.memberOf, attribs));
				this.getUsuarioLDAP().setScriptPath(getAtributo(PBPFLDAPParametrosConfig.scriptPath, attribs));
				this.getUsuarioLDAP().setUserPrincipalName(getAtributo(PBPFLDAPParametrosConfig.sAMAccountName, attribs));
				this.getUsuarioLDAP().setWhenCreated(getAtributo(PBPFLDAPParametrosConfig.whenCreated,attribs));
				this.getUsuarioLDAP().setAdminCount(getAtributoAsBoolean(PBPFLDAPParametrosConfig.adminCount, attribs));

				this.getListaUsuarioLDAPDataTable().add(this.getUsuarioLDAP());

			}
			
			this.retirarUsuariosJaCadastrados();

			return "listarUsuarioLDAP";

		} catch (Exception e) {
			return tratarExcecao(e);
		}

	}

	private void retirarUsuariosJaCadastrados() {
		Usuario negocioUsuario = getPBPFBusinessFactory().createUsuario();

		UsuarioTO usuario = new UsuarioTO();
		this.setListaUsuarioCadastro(negocioUsuario.listar(usuario));

		for (Iterator<TransferObject> iterator = this.getListaUsuarioCadastro().iterator(); iterator.hasNext();) {
			usuario = (UsuarioTO) iterator.next();
			for (int i = 0; i < this.getListaUsuarioLDAPDataTable().size(); i++) {
				if (this.getListaUsuarioLDAPDataTable().get(i).getUserPrincipalName().equals(usuario.getQdfLoginAd())) {
					this.getListaUsuarioLDAPDataTable().remove(i);
				}
			}
		}
	}
	
	private String pegarValorFiltro() {
		
		if (this.getLoginFiltro() != null && !this.getLoginFiltro().isEmpty()) {
			this.setFilter("(sAMAccountName=" + this.getLoginFiltro() + ")");
		} else {
			if (this.getNomeFiltro() != null && !this.getNomeFiltro().isEmpty()) {
				this.setFilter("(displayName=" + this.getNomeFiltro() + ")");
			} else {
				this.setFilter(PBPFLDAPParametrosConfig.filter);
			}
			
		}
		
		return this.getFilter();
	}

	private String getAtributo(String nomedoatributo, Attributes attribs) {
		try {
			Attribute attrdisplayName = attribs.get(nomedoatributo);

			if (attrdisplayName != null) {
				return String.valueOf(attrdisplayName.get());
			} else {
				return "";
			}

		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	private Boolean getAtributoAsBoolean(String nomedoatributo, Attributes attribs) throws RegraNegocioException {

		try {
			Attribute attrdisplayName = attribs.get(nomedoatributo);

			if (attrdisplayName != null && attrdisplayName.get().equals("1")) {
					return true;
			}
		} catch (NamingException e) {
			throw new RegraNegocioException(e.getMessage());
		}
		return false;
	}

	public void setUsuarioLDAP(PBPFLDAPUsuario usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

	public PBPFLDAPUsuario getUsuarioLDAP() {
		if (usuarioLDAP == null) {
			usuarioLDAP = new PBPFLDAPUsuario();
		}
		return usuarioLDAP;
	}

	public void setLdap(PBPFLDAPServiceLocator ldap) {
		this.ldap = ldap;
	}

	public PBPFLDAPServiceLocator getLdap() {
		if (ldap == null) {
			ldap = new PBPFLDAPServiceLocator();
		}
		return ldap;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaUsuarioLDAPDataTable(List<PBPFLDAPUsuario> listaUsuarioLDAPDataTable) {
		this.listaUsuarioLDAPDataTable = listaUsuarioLDAPDataTable;
	}

	public List<PBPFLDAPUsuario> getListaUsuarioLDAPDataTable() {
		if (listaUsuarioLDAPDataTable == null) {
			listaUsuarioLDAPDataTable = new ArrayList<PBPFLDAPUsuario>();
		}
		return listaUsuarioLDAPDataTable;
	}

	public void setListaUsuarioCadastro(List<TransferObject> listaUsuarioCadastro) {
		this.listaUsuarioCadastro = listaUsuarioCadastro;
	}

	public List<TransferObject> getListaUsuarioCadastro() {
		return listaUsuarioCadastro;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	public String getFilter() {
		return filter;
	}

	public void setLoginFiltro(String loginFiltro) {
		this.loginFiltro = loginFiltro;
	}

	public String getLoginFiltro() {
		return loginFiltro;
	}

	public void setNomeFiltro(String nomeFiltro) {
		this.nomeFiltro = nomeFiltro;
	}

	public String getNomeFiltro() {
		return nomeFiltro;
	}
}
