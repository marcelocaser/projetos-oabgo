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
	 * Executa o hql passado como parâmetro
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
	 * Retorna o primeiro objeto encontrado na execução do Query passado como parâmetro
	 * @param query - Query
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(Query query) throws BDException, ApplicationException;
	
	/**
	 * Retorna o primeiro objeto encontrado na execução do HQL passado como parâmetro
	 * @param hql - String
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(String hql) throws BDException, ApplicationException;
	
	/**
	 * Retorna o primeiro objeto encontrado na execução do Criteria passado como parâmetro
	 * @param criteria - Criteria
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return Object
	 */
	public Object buscarObjeto(Criteria criteria) throws BDException, ApplicationException;
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Realiza um select baseado nas propriedades setadas do objeto passado como parâmetro. E retorna um resultado apenas.
	 * Obs: Popule o objeto de maneira que ele seja único. Dessa forma é garantido o retorno do objeto certo.
	 * 
	 * @param tranferObject - TranferObject
	 * @param classe - Class<T>
	 * @return T - <T extends TransferObject>
	 * @throws BDException
	 */
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException;
	
	/**
	 * Método responsável por listar todos os objetos semelhantes ao TransferObject 
	 * passado como parâmentro utilizando como filtro as propriedades inseridas no
	 * objeto passado como parâmetro.
	 *
	 * @param tranferObject - TranferObject
	 * @return List
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Retorna uma lista de registros baseados nos parametros passados no TO e ordenador pela propriedade
	 * indicada como parâmetro.
	 *
	 * @param transferObject - TransferObject
	 * @param orderBy - String
	 * @param orderType - OrderType
	 * @throws BDException 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy, OrderType orderType) throws BDException;
	
	/**
	 * Lista todos os objetos TransferObject semelhantes ao objeto passado como parâmetro utilizando como filtro de busca
	 * as propriedades inseridas no mesmo ordenando a listagem pela propriedade orderBy.
	 *
	 * @param tranferObject - TranferObject - Objeto que representa a Entidade que será listada
	 * @param orderBy - {@link String} - Nome da propriedade pela qual a listagem será ordenada.
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws HibernateException, ApplicationException;
	
	/**
	 * Retorna uma lista de TO específico ordenada por um determinado parâmetro
	 * @param transferObject - Objeto a ser listado
	 * @param orderBy - Atributo do TO que sera utilizado como parâmetro de cláusula ORDER
	 * @param classe - Class<T> - T extends TransferObject
	 * @return List
	 * @throws BDException
	 */
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException;
	
	/**
	 * Lista todos os objetos TransferObject semelhantes ao objeto passado como parâmetro utilizando como filtro de busca.
	 * as propriedades inseridas no mesmo, paginando a listagem de acordo com os parâmetros passados no objeto ListaPaginadaInfo. 
	 * O objeto info carrega informações como (Ordenação, Tipo de Ordenação: ASC / DESC, Quantidade de registros apresentados por requisição, etc).
	 *
	 * @param tranferObject - TranferObject - Objeto que representa a Entidade que será listada.
	 * @param info - {@link PaginacaoInfo} - Objeto que carrega informações utilizadas na paginação (Ordenação, Tipo de Ordenação: ASC / DESC, Quantidade de registros apresentados por requisição, etc).
	 * @return Paginacao
	 * @throws BDException
	 */
	public Paginacao listar(TransferObject transferObject, PaginacaoInfo info) throws BDException, ApplicationException;
	
	/**
	 * Persiste as informações contidas no objeto TransferObject passado como parâmetro.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void incluir(TransferObject tranferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as alterações contidas no objeto TransferObject passado como parâmetro.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void alterar(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as alterações contidas no objeto TransferObject passado como parâmetro utilizando o
	 * método MERGE do Hibernate.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void merge(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Persiste as alterações contidas no objeto TransferObject passado como parâmetro utilizando o
	 * método SAVEORUPDATE do Hibernate.
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void saveOrUpdate(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Apaga o objeto passado como parâmetro
	 *
	 * @param tranferObject - {@link TransferObject}
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void excluir(TransferObject transferObject) throws BDException, ApplicationException;
	
	/**
	 * Apaga todos os objetos passados como parâmetro.
	 *
	 * @param listaItens - Collection<TransferObject>
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void excluir(Collection<TransferObject> listaItens) throws BDException, ApplicationException;
	
	/**
	 * Consulta o objeto passado como parâmetro com base no ID.
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
	 * como parâmetro.
	 * 
	 * @param bean -
	 *            TransferObject
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeInsert(TransferObject bean) throws BDException;
	
	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado como parâmetro.
	 * 
	 * @param bean - TransferObject - TO que será persistido
	 * @param beanTmp - TransferObject - TO com as propriedades a serem comparadas
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeUpdate(TransferObject bean, TransferObject beanTmp) throws BDException;
	
	/**
	 * Inicializa as propriedades do TransferObject passado como parâmetro.
	 *
	 * @param bean - {@link Object}
	 * @throws BDException
	 * @return void
	 */
	public void inicializar(Object bean) throws BDException;
	
	/**
	 * Realiza operação de load
	 * 
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	public <T extends TransferObject> T load(Class<T> classe, TransferObject transferObject) throws BDException;
	
	/**
	 * Realiza operação de get. Caso não exista no banco de dados o objeto com id especificado
	 * retorna null.
	 * 
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	public <T extends TransferObject> T get(Class<T> classe, TransferObject transferObject) throws BDException;
	
	/**
	 * Força a execução de um FLUSH no Hibernate executando todos os SQL'S armazenados em memória
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return void
	 */
	public void flush() throws BDException, ApplicationException;
	
	/**
	 * Força a execução de um CLEAR na Sesão atual do Hibernate
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
	 * Cria um obejto Query com base em uma String passada como parâmetro.
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public Query createQuery(String str) throws BDException, ApplicationException;
	
	/**
	 * Cria um obejto Criteria com base em um Class passado como parâmetro.
	 *
	 * @throws BDException
	 * @throws ApplicationException - 
	 * @return Session
	 */
	public Criteria createCriteria(Class<? extends TransferObject> classe) throws BDException, ApplicationException;
	
	/**
	 * Remove um TransferObject sincronizado da sessão atual do Hibernate
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
