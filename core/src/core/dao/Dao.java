package core.dao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import core.enumeration.OrderType;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;


/**
 * <b>Titulo:</b> Dao.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public interface Dao {
	

	/**
	 * Retorna a quantidade de registros existentes. 
	 * @param criteria - Criteria
	 * @param id - String
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(Criteria criteria, String id) throws BDException, ApplicationException;

	/**
	 *Retorna a quantidade de registros existentes. 
	 *
	 * @param tranferObject - TranferObject
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Executa um comando SQL nativo na Banco de Dados.
	 * @param sql - String
	 * @throws ApplicationException
	 */
	public void executarSQL(String sql) throws BDException, ApplicationException;
	
	/**
	 * Executa o hql passado como par�metro
	 * @param hql - String
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return List<Object>
	 */
	public List<Object> executar(String hql) throws BDException, ApplicationException;
	
	/**
	 * Executa o objeto Query
	 *
	 * @param query - Query
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return List<Object>
	 */
	public List<Object> executar(Query query) throws BDException, ApplicationException;	
	
	/**
	 * Executa o objeto Criteria
	 * @param criteria - Criteria
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> executar(Criteria criteria) throws BDException, ApplicationException;
	
	/**
	 * Retorna o primeiro objeto encontrado na execu��o do Query passado como par�metro
	 * @param query - Query
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(Query query) throws BDException, ApplicationException;
	
	/**
	 * Retorna o primeiro objeto encontrado na execu��o do HQL passado como par�metro
	 * @param hql - String
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(String hql) throws BDException, ApplicationException;
	
	/**
	 * Retorna o primeiro objeto encontrado na execu��o do Criteria passado como par�metro
	 * @param criteria - Criteria
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(Criteria criteria) throws BDException, ApplicationException;
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como par�metro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(TransferObject transferObject) throws BDException, ApplicationException;
	
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
	 * M�todo respons�vel por listar todos os objetos semelhantes ao TransferObject 
	 * passado como par�mentro utilizando como filtro as propriedades inseridas no
	 * objeto passado como par�metro.
	 *
	 * @param tranferObject - TranferObject
	 * @return List
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Retorna uma lista de registros baseados nos parametros passados no TO e ordenador pela propriedade
	 * indicada como par�metro.
	 *
	 * @param transferObject - TransferObject
	 * @param orderBy - String
	 * @param orderType - OrderType
	 * @throws BDException 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy, OrderType orderType) throws BDException;
	
	/**
	 * Lista todos os objetos TransferObject semelhantes ao objeto passado como par�metro utilizando como filtro de busca
	 * as propriedades inseridas no mesmo ordenando a listagem pela propriedade orderBy.
	 *
	 * @param tranferObject - TranferObject - Objeto que representa a Entidade que ser� listada
	 * @param orderBy - {@link String} - Nome da propriedade pela qual a listagem ser� ordenada.
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws HibernateException, ApplicationException;
	
	/**
	 * Retorna uma lista de TO espec�fico ordenada por um determinado par�metro
	 * @param transferObject - Objeto a ser listado
	 * @param orderBy - Atributo do TO que sera utilizado como par�metro de cl�usula ORDER
	 * @param classe - Class<T> - T extends TransferObject
	 * @return List
	 * @throws BDException
	 */
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException;
	
	/**
	 * Lista todos os objetos TransferObject semelhantes ao objeto passado como par�metro utilizando como filtro de busca.
	 * as propriedades inseridas no mesmo, paginando a listagem de acordo com os par�metros passados no objeto ListaPaginadaInfo. 
	 * O objeto info carrega informa��es como (Ordena��o, Tipo de Ordena��o: ASC / DESC, Quantidade de registros apresentados por requisi��o, etc).
	 *
	 * @param tranferObject - TranferObject - Objeto que representa a Entidade que ser� listada.
	 * @param info - {@link PaginacaoInfo} - Objeto que carrega informa��es utilizadas na pagina��o (Ordena��o, Tipo de Ordena��o: ASC / DESC, Quantidade de registros apresentados por requisi��o, etc).
	 * @return Paginacao
	 * @throws BDException
	 */
	public Paginacao listar(TransferObject transferObject, PaginacaoInfo info) throws BDException, ApplicationException;
	
	/**
	 * Persiste as informa��es contidas no objeto TransferObject passado como par�metro.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void incluir(TransferObject tranferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as altera��es contidas no objeto TransferObject passado como par�metro.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void alterar(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as altera��es contidas no objeto TransferObject passado como par�metro utilizando o
	 * m�todo MERGE do Hibernate.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void merge(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as altera��es contidas no objeto TransferObject passado como par�metro utilizando o
	 * m�todo SAVEORUPDATE do Hibernate.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void saveOrUpdate(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Apaga o objeto passado como par�metro
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void excluir(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Apaga todos os objetos passados como par�metro.
	 *
	 * @param listaItens - Collection<TransferObject>
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void excluir(Collection<TransferObject> listaItens) throws BDException, ApplicationException;
	
	/**
	 * Consulta o objeto passado como par�metro com base no ID.
	 *
	 * @param classe - Class<? extends TransferObject>
	 * @param transferObject - TransferObject
	 * @throws BDException
	 * @return TransferObject
	 * @throws ApplicationException 
	 */
	public TransferObject consultar(Class<? extends TransferObject> classe, TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado
	 * como par�metro.
	 * 
	 * @param bean -
	 *            TransferObject
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeInsert(TransferObject bean) throws BDException;
	
	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado como par�metro.
	 * 
	 * @param bean - TransferObject - TO que ser� persistido
	 * @param beanTmp - TransferObject - TO com as propriedades a serem comparadas
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeUpdate(TransferObject bean, TransferObject beanTmp) throws BDException;
	
	/**
	 * Inicializa as propriedades do TransferObject passado como par�metro.
	 *
	 * @param bean - {@link Object}
	 * @throws BDException
	 * @return void
	 */
	public void inicializar(Object bean) throws BDException;
	
	/**
	 * Realiza opera��o de load
	 * 
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	public <T extends TransferObject> T load(Class<T> classe, TransferObject transferObject) throws BDException;
	
	/**
	 * Realiza opera��o de get. Caso n�o exista no banco de dados o objeto com id especificado
	 * retorna null.
	 * 
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	public <T extends TransferObject> T get(Class<T> classe, TransferObject transferObject) throws BDException;
	
	/**
	 * For�a a execu��o de um FLUSH no Hibernate executando todos os SQL'S armazenados em mem�ria
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return void
	 */
	public void flush() throws BDException, ApplicationException;
	
	/**
	 * For�a a execu��o de um CLEAR na Ses�o atual do Hibernate
	 *
	 * @throws BDException
	 * @throws ApplicationException
	 * @return void
	 */
	public void clear() throws BDException, ApplicationException;
	
	/**
	 * Retorna o objeto SessionFactory atual fornecido pelo Spring
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return SessionFactory
	 */
	public SessionFactory getSessionFactory() throws BDException, ApplicationException;
	
	/**
	 * Retorna o objeto Session atual fornecido pelo Spring
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public Session getSession() throws BDException, ApplicationException;
	
	/**
	 * Cria um obejto Query com base em uma String passada como par�metro.
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public Query createQuery(String str) throws BDException, ApplicationException;
	
	/**
	 * Cria um obejto Criteria com base em um Class passado como par�metro.
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public Criteria createCriteria(Class<? extends TransferObject> classe) throws BDException, ApplicationException;
	
	/**
	 * Remove um TransferObject sincronizado da sess�o atual do Hibernate
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public void retirarObjetoSessao(TransferObject bean) throws ApplicationException;
	
	/**
	 * Rotorna o nome do atributo identificador de um determinado TransferObject.
	 *
	 * @param bean - {@link TransferObject}
	 * @throws ApplicationException - 
	 * @return Object
	 */
	public Object getAtributoIdentificadorTransferObject(TransferObject bean) throws ApplicationException;
	
}
