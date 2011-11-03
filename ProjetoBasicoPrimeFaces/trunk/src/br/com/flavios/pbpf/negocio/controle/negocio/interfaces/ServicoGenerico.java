package br.com.flavios.pbpf.negocio.controle.negocio.interfaces;

import java.util.List;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;

public interface ServicoGenerico {
	
	/**
	 * Realiza o evict. Retira o objeto da sess�o do Hibernate.
	 * 
	 * @param transferObject - {@link TransferObject}
	 * @throws BDException
	 * @return void
	 */
	public void retirarObjetoSessao(TransferObject transferObject) throws ApplicationException;
	
	/**
	 * Realiza o flush. Descarrega opera��es de banco armazenadas em mem�ria.
	 * 
	 * @throws BDException
	 * @return void
	 */
	public void flush() throws BDException;
	
	/**
	 * Conta o n�mero de registros no BD baseado nas propriedades do objeto passado como par�metro.
	 * 
	 * @param transferObject - {@link TransferObject}
	 * @throws BDException
	 * @return Integer
	 */
	public Integer count(TransferObject transferObject) throws BDException;
	
	/**
	 * Deleta o objeto do banco de dados.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 * @return void
	 */
	public void excluir(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	/**
	 * Realiza o update do objeto persistente. Use com modera��o, pois o before update n�o � chamado.
	 * Saiba que � um update seguro.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 * @return void
	 */
	public void update(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	/**
	 * Realiza o insert do objeto persistente. Use com modera��o, pois o before insert n�o � chamado.
	 * Antes de chamar, tenha certeza que � um insert ordin�rio que dispensa valida��o.
	 * 
	 * @param transferObject - TransferObject
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 * @return void
	 */
	public void save(TransferObject transferObject, UsuarioTO usuarioLogado) throws BDException;
	
	/**
	 * Realiza a opera��o de load do objeto.
	 * 
	 * @param transferObject - TransferObject
	 * @param classe - Class<T>
	 * @return T <T extends TransferObject>
	 */
	public <T extends TransferObject> T load(TransferObject transferObject, Class<T> classe);
	
	/**
	 * Executa opera��o de get em fun��o do id setado no objeto de busca.
	 * Retorna null, caso n�o existe registro com o id especificado.
	 * 
	 * @param classe - Class<T>
	 * @param transferObject - TransferObject
	 * 
	 * @return T - <T extends TransferObject>
	 */
	public <T extends TransferObject> T get(TransferObject transferObject, Class<T> classe);
	
	/**
	 * Executa um comando SQL nativo na Banco de Dados.
	 * @param sql - String
	 * @throws ApplicationException
	 */
	public void executarSQL(String sql) throws BDException, ApplicationException;
	
	/**
	 * Realiza um select baseado nas propriedades setadas do objeto passado como par�metro. E retorna um resultado apenas.
	 * Obs: Popule o objeto de maneira que ele seja �nico. Dessa forma � garantido o retorno do objeto certo.
	 * 
	 * @param tranferObject - TranferObject
	 * @param classe - Class<T>
	 * @return T - <T extends TransferObject>
	 * @throws BDException
	 */
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException;
	
	/**
	 * M�todo de listagem com ordena��o.
	 * 
	 * @param transferObject - TransferObject
	 * @param classe - Class<T>
	 * @param orderBy - String
	 * @throws BDException
	 * @return List<T>
	 */
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException;

	/**
	 * M�todo de listagem com ordena��o.
	 * 
	 * @param transferObject - TransferObject
	 * @param orderBy - String
	 * @throws BDException
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws BDException;
	
}
