package br.org.oabgo.sati.web.controle;

import java.lang.reflect.Method;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.excecoes.AcessoException;
import core.excecoes.BDException;
import core.security.SecurityProvider;

public class SATISecurity implements SecurityProvider {

	/**
	 * M�todo respons�vel por verificar se o usu�rio tem permiss�o para executar a opera��o chamada.
	 * 
	 * @param user - UsuarioTO - Usuario logado na aplica��o.
	 * @param business - Negocio - Classe de neg�cio que esta sendo chamada.
	 * @param metodo - Method - M�todo que ser� executado se o controle de acesso permitir.
	 * @param args - Argumentos do m�todo.
	 * 
	 * @return boolean
	 * 
	 * @throws AcessoException
	 * @throws BDException
	 */
	public void checkPermission(Object usuario, Object business, Method metodo,	Object[] args) throws AcessoException, BDException {
		//this.medotoControlado(metodo.getDeclaringClass(), metodo, (UsuarioTO)usuario);
		//this.checkPermission(metodo.getDeclaringClass(), metodo, (UsuarioTO)usuario);
	}
	
	/**
	 * Verifica se o m�todo chamada esta entre os m�todos controlados pelo Controle de Acesso.
	 * 
	 * @param classe - Class - Classe de negocio refer�nciada
	 * @param metodo - Method - Metodo chamada
	 * @param usuarioLogado - UsuarioTO
	 * @throws AcessoException
	 * @throws BDException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void medotoControlado(Class classe, Method metodo, UsuarioTO usuario) throws AcessoException, BDException{
		//verifica se o m�todo � controlado pelo CtrAcesso, se n�o for dispara a exce��o
	}
	
	/**
	 * Verifica se o usu�rio logado possui permiss�o para acessar o recurso chamado.
	 * 
	 * @param classe - Class - Classe de negocio refer�nciada
	 * @param metodo - Method - Metodo chamada
	 * @param usuarioLogado - UsuarioTO
	 * @throws AcessoException
	 * @throws BDException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void checkPermission(Class classe, Method metodo, UsuarioTO uruario) throws AcessoException, BDException{
		//verifica se o m�todo � controlado pelo CtrAcesso, se n�o for dispara a exce��o
		
	}

}
