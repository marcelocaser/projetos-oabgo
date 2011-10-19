package core.security;

import java.lang.reflect.Method;

import core.excecoes.AcessoException;
import core.excecoes.BDException;



public interface SecurityProvider {
	
	/**
	 * Método que implementa o controle de acesso baseado nos métodos das classes de negócio
	 * @param usuarioLogado - Object
	 * @param business - Negocio
	 * @param method - Method
	 * @param args - Object[]
	 * @throws CtrAcessoException - Caso o usuário logado não possa acessar o recurso.
	 */
	public void checkPermission(Object usuarioLogado, Object business, Method method, Object[] args ) throws AcessoException, BDException;

}
