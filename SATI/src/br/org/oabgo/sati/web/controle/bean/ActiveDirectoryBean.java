package br.org.oabgo.sati.web.controle.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import br.org.oabgo.sati.negocio.controle.SATILDAPParametrosConfig;
import br.org.oabgo.sati.negocio.controle.SATILDAPServiceLocator;
import br.org.oabgo.sati.negocio.controle.SATILDAPUsuario;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.sati.web.controle.SATIManagedBean;
import core.dao.TransferObject;
import core.excecoes.RegraNegocioException;

public class ActiveDirectoryBean extends SATIManagedBean {

	private static final long serialVersionUID = -7789413386568073808L;

	private SATILDAPUsuario usuarioLDAP;

	private SATILDAPServiceLocator ldap;

	private Integer itensPorPagina = 15;

	private List<SATILDAPUsuario> listaUsuarioLDAPDataTable;

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

			NamingEnumeration answer = ctx.search(SATILDAPParametrosConfig.BASE_DN,
					pegarValorFiltro(), ctls);
			
			this.setListaUsuarioLDAPDataTable(new ArrayList<SATILDAPUsuario>());

			while (answer.hasMoreElements()) {

				SearchResult sr = (SearchResult) answer.next();
				Attributes attribs = sr.getAttributes();
				setUsuarioLDAP(new SATILDAPUsuario());

				this.getUsuarioLDAP().setDisplayName(getAtributo(SATILDAPParametrosConfig.displayName,attribs));
				this.getUsuarioLDAP().setGivenName(getAtributo(SATILDAPParametrosConfig.givenName, attribs));
				this.getUsuarioLDAP().setMemberOf(getAtributo(SATILDAPParametrosConfig.memberOf, attribs));
				this.getUsuarioLDAP().setScriptPath(getAtributo(SATILDAPParametrosConfig.scriptPath, attribs));
				this.getUsuarioLDAP().setUserPrincipalName(getAtributo(SATILDAPParametrosConfig.sAMAccountName, attribs));
				this.getUsuarioLDAP().setWhenCreated(getAtributo(SATILDAPParametrosConfig.whenCreated,attribs));
				this.getUsuarioLDAP().setAdminCount(getAtributoAsBoolean(SATILDAPParametrosConfig.adminCount, attribs));

				this.getListaUsuarioLDAPDataTable().add(this.getUsuarioLDAP());

			}
			
			this.retirarUsuariosJaCadastrados();

			return "listarUsuarioLDAP";

		} catch (Exception e) {
			return tratarExcecao(e);
		}

	}

	private void retirarUsuariosJaCadastrados() {
		Usuario negocioUsuario = getSATIBusinessFactory().createUsuario();

		UsuarioTO usuario = new UsuarioTO();
		this.setListaUsuarioCadastro(negocioUsuario.listar(usuario));

		for (Iterator<TransferObject> iterator = this.getListaUsuarioCadastro().iterator(); iterator.hasNext();) {
			usuario = (UsuarioTO) iterator.next();
			for (int i = 0; i < this.getListaUsuarioLDAPDataTable().size(); i++) {
				if (this.getListaUsuarioLDAPDataTable().get(i).getUserPrincipalName().equals(usuario.getVchLogin())) {
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
				this.setFilter(SATILDAPParametrosConfig.filter);
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

	public void setUsuarioLDAP(SATILDAPUsuario usuarioLDAP) {
		this.usuarioLDAP = usuarioLDAP;
	}

	public SATILDAPUsuario getUsuarioLDAP() {
		if (usuarioLDAP == null) {
			usuarioLDAP = new SATILDAPUsuario();
		}
		return usuarioLDAP;
	}

	public void setLdap(SATILDAPServiceLocator ldap) {
		this.ldap = ldap;
	}

	public SATILDAPServiceLocator getLdap() {
		if (ldap == null) {
			ldap = new SATILDAPServiceLocator();
		}
		return ldap;
	}

	public void setItensPorPagina(Integer itensPorPagina) {
		this.itensPorPagina = itensPorPagina;
	}

	public Integer getItensPorPagina() {
		return itensPorPagina;
	}

	public void setListaUsuarioLDAPDataTable(List<SATILDAPUsuario> listaUsuarioLDAPDataTable) {
		this.listaUsuarioLDAPDataTable = listaUsuarioLDAPDataTable;
	}

	public List<SATILDAPUsuario> getListaUsuarioLDAPDataTable() {
		if (listaUsuarioLDAPDataTable == null) {
			listaUsuarioLDAPDataTable = new ArrayList<SATILDAPUsuario>();
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
