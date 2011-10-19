package core.security;

import java.lang.reflect.Method;

import core.excecoes.AcessoException;
import core.excecoes.BDException;



/**
 * <b>Title:</b> CoreSecurityProxy.java <br> * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class CoreSecurityProxy implements SecurityProvider {

	/**
	 * Método executado a cada chamada à métodos de classe de negócio.
	 *
	 * @param usuarioLogado - {@link Object}
	 * @param business - {@link Negocio}
	 * @param method - {@link Method}
	 * @param args - {@link Object}
	 * @throws CtrAcessoException
	 * @throws BDException - 
	 */
	public void checkPermission(Object usuarioLogado, Object business,	Method method, Object[] args) throws AcessoException, BDException {
	}

}
