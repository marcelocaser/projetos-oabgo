package core.security;

import java.lang.reflect.Method;

import core.excecoes.AcessoException;
import core.excecoes.BDException;



public interface SecurityProvider {
	
	/**
	 * M�todo que implementa o controle de acesso baseado nos m�todos das classes de neg�cio
	 * @param usuarioLogado - Object
	 * @param business - Negocio
	 * @param method - Method
	 * @param args - Object[]
	 * @throws CtrAcessoException - Caso o usu�rio logado n�o possa acessar o recurso.
	 */
	public void checkPermission(Object usuarioLogado, Object business, Method method, Object[] args ) throws AcessoException, BDException;

}
