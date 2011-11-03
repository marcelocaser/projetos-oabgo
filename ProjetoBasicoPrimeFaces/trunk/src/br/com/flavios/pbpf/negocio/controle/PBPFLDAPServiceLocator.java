package br.com.flavios.pbpf.negocio.controle;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import core.excecoes.RegraNegocioException;

public class PBPFLDAPServiceLocator {

	protected static PBPFLDAPServiceLocator instance;

	public PBPFLDAPServiceLocator() {
		super();
	}

	public static PBPFLDAPServiceLocator getInstance() {

		if (instance == null) {
			instance = new PBPFLDAPServiceLocator();
		}

		return instance;
	}

	@SuppressWarnings("unchecked")
	public DirContext getContext() throws RegraNegocioException {

		Hashtable env = new Hashtable();

		env.put(Context.INITIAL_CONTEXT_FACTORY,
				PBPFLDAPParametrosConfig.INITIAL_CTX);
		env.put(Context.PROVIDER_URL, PBPFLDAPParametrosConfig.SERVIDOR);
		env.put(Context.SECURITY_AUTHENTICATION,
				PBPFLDAPParametrosConfig.AUTENTICACAO);
		env.put(Context.SECURITY_PRINCIPAL, PBPFLDAPParametrosConfig.USER_DN);
		env.put(Context.SECURITY_CREDENTIALS, PBPFLDAPParametrosConfig.USER_PW);

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
				PBPFLDAPParametrosConfig.INITIAL_CTX);
		env.put(Context.PROVIDER_URL, PBPFLDAPParametrosConfig.SERVIDOR);
		env.put(Context.SECURITY_AUTHENTICATION,
				PBPFLDAPParametrosConfig.AUTENTICACAO);
		env.put(Context.SECURITY_PRINCIPAL, usuario + "pbpfDominioFlavios");
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