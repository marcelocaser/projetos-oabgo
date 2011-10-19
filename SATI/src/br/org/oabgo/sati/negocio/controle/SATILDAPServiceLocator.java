package br.org.oabgo.sati.negocio.controle;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import core.excecoes.RegraNegocioException;

public class SATILDAPServiceLocator {

	protected static SATILDAPServiceLocator instance;

	public SATILDAPServiceLocator() {
		super();
	}

	public static SATILDAPServiceLocator getInstance() {

		if (instance == null) {
			instance = new SATILDAPServiceLocator();
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	public DirContext getContext() throws RegraNegocioException {

		Hashtable env = new Hashtable();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				SATILDAPParametrosConfig.INITIAL_CTX);
		env.put(Context.PROVIDER_URL, SATILDAPParametrosConfig.SERVIDOR);
		env.put(Context.SECURITY_AUTHENTICATION,
				SATILDAPParametrosConfig.AUTENTICACAO);
		env.put(Context.SECURITY_PRINCIPAL, SATILDAPParametrosConfig.USER_DN);
		env.put(Context.SECURITY_CREDENTIALS, SATILDAPParametrosConfig.USER_PW);

		DirContext ctx = null;

		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			throw new RegraNegocioException("msgErroContextoLDAP");
		}
		return ctx;
	}

	@SuppressWarnings("unchecked")
	public DirContext getContext(String usuario, String senha)
			throws RegraNegocioException {

		Hashtable env = new Hashtable();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				SATILDAPParametrosConfig.INITIAL_CTX);
		env.put(Context.PROVIDER_URL, SATILDAPParametrosConfig.SERVIDOR);
		env.put(Context.SECURITY_AUTHENTICATION,
				SATILDAPParametrosConfig.AUTENTICACAO);
		env.put(Context.SECURITY_PRINCIPAL, usuario + "satiDominioOABGO");
		env.put(Context.SECURITY_CREDENTIALS, senha);

		DirContext ctx = null;

		try {
			ctx = new InitialDirContext(env);
		} catch (NamingException e) {
			throw new RegraNegocioException("msgErroContextoLDAP");
		}
		return ctx;
	}
}