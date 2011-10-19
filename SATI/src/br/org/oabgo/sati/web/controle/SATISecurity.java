package br.org.oabgo.sati.web.controle;

import java.lang.reflect.Method;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.excecoes.AcessoException;
import core.excecoes.BDException;
import core.security.SecurityProvider;

public class SATISecurity implements SecurityProvider {

	/**
	 * Método responsável por verificar se o usuário tem permissão para executar a operação chamada.
	 * 
	 * @param user - UsuarioTO - Usuario logado na aplicação.
	 * @param business - Negocio - Classe de negócio que esta sendo chamada.
	 * @param metodo - Method - Método que será executado se o controle de acesso permitir.
	 * @param args - Argumentos do método.
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
	 * Verifica se o método chamada esta entre os métodos controlados pelo Controle de Acesso.
	 * 
	 * @param classe - Class - Classe de negocio referênciada
	 * @param metodo - Method - Metodo chamada
	 * @param usuarioLogado - UsuarioTO
	 * @throws AcessoException
	 * @throws BDException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void medotoControlado(Class classe, Method metodo, UsuarioTO usuario) throws AcessoException, BDException{
		//verifica se o método é controlado pelo CtrAcesso, se não for dispara a exceção
	}
	
	/**
	 * Verifica se o usuário logado possui permissão para acessar o recurso chamado.
	 * 
	 * @param classe - Class - Classe de negocio referênciada
	 * @param metodo - Method - Metodo chamada
	 * @param usuarioLogado - UsuarioTO
	 * @throws AcessoException
	 * @throws BDException
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	private void checkPermission(Class classe, Method metodo, UsuarioTO uruario) throws AcessoException, BDException{
		//verifica se o método é controlado pelo CtrAcesso, se não for dispara a exceção
		
	}

}
