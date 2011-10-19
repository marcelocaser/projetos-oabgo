package core.dao;


import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import core.enumeration.OrderType;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;
import core.hibernate.HibernateUtil;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;



/**
 * <b>Titulo:</b> DaoHibernate.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> Core <br>
 * 
 * @author Leonardo Peixoto
 *
 * @spring.bean id="daoHibernate"
 */
@Repository(value="daoHibernate")
public class DaoHibernate implements Dao {
	
	@Autowired
	private HibernateUtil hibernateUtil;

	/**
	 * Retorna o valor armazenado na variável DaoHibernate.java.
	 * @return HibernateUtil
	 * 
	 * @spring.property name="hibernateUtil" ref="hibernateUtil"
	 */
	public HibernateUtil getHibernateUtil() {
		return hibernateUtil;
	}

	/**
	 * Insere um valor ao atributo DaoHibernate.java
	 * @param hibernateUtil - HibernateUtil
	 */
	@Resource(name="hibernateUtil")
	public void setHibernateUtil(HibernateUtil hibernateUtil) {
		this.hibernateUtil = hibernateUtil;
	}
	
	/**
	 * Persiste as informações contidas em uma objeto TrasferObject para o Banco.
	 * 
	 * @param tranferObject
	 * @throws BDException
	 */
	public void incluir(TransferObject tranferObject) throws BDException, ApplicationException{
		this.getHibernateUtil().incluir(tranferObject);
	}
	
	/**
	 * Persiste as alterações incluidas no TransferObject passado como parâmetro
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void alterar(TransferObject transferObject) throws BDException, ApplicationException{
		this.getHibernateUtil().alterar(transferObject);
	}

	/**
	 * Persiste as alterações incluidas no TransferObject passado como parâmetro
	 * realizando um "MERGE" entre as propriedades.
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void merge(TransferObject transferObject) throws BDException, ApplicationException{
		this.getHibernateUtil().merge(transferObject);
	}

	/**
	 * Persiste as alterações incluidas no TransferObject passado como parâmetro
	 * realizando um "MERGE" entre as propriedades.
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void saveOrUpdate(TransferObject transferObject) throws BDException, ApplicationException{
		this.getHibernateUtil().saveOrUpdate(transferObject);
	}
	
	/**
	 * Deleta o ojeto passado como parâmetro.
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void excluir(TransferObject transferObject) throws BDException, ApplicationException{
		this.getHibernateUtil().excluir(transferObject);
	}

	/**
	 * Deleta todos os objetos da coleção passada como parâmetro
	 * 
	 * @param listaItens - List<TransferObject>
	 * @throws BDException
	 */
	public void excluir(Collection<TransferObject> listaItens) throws BDException, ApplicationException{
		this.getHibernateUtil().excluir(listaItens);
	}
	
	/**
	 * Consulta um TransferObject com base no valor retornado pelo método getKey().
	 * 
	 * @param classe - Class
	 * @param transferObject - TransferObject
	 * @return TransferObject
	 * @throws BDException
	 */
	public TransferObject consultar(Class<? extends TransferObject> classe, TransferObject transferObject) throws BDException, ApplicationException{
		return this.getHibernateUtil().consultar(classe, transferObject);
	}
	
	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado
	 * como parâmetro.
	 * 
	 * @param bean -
	 *            TransferObject
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeInsert(TransferObject bean) throws BDException{
		return this.getHibernateUtil().beforeInsert(bean);
	}
	
	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado como parâmetro.
	 * 
	 * @param bean - TransferObject - TO que será persistido
	 * @param beanTmp - TransferObject - TO com as propriedades a serem comparadas
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeUpdate(TransferObject bean, TransferObject beanTmp) throws BDException{
		return this.getHibernateUtil().beforeUpdate(bean, beanTmp);
	}
	
	/**
	 * Inicializa as propriedades do TransferObject passado como parâmetro.
	 *
	 * @param bean - {@link Object}
	 * @throws BDException
	 * @return void
	 */
	public void inicializar(Object bean) throws BDException{
		this.getHibernateUtil().inicializar(bean);
	}
	
	/**
	 * Retorna o primeiro objeto encontrado que satisfaça as condições de busca.
	 * 
	 * @param query - Query
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(Query query) throws BDException, ApplicationException{
		return this.getHibernateUtil().buscarObjeto(query);
	}
	
	/**
	 *Retorna a quantidade de registros existentes. 
	 *
	 * @param tranferObject - TranferObject
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(Criteria criteria, String id) throws BDException, ApplicationException{
		return this.getHibernateUtil().count(criteria, id);
	}

	/**
	 *Retorna a quantidade de registros existentes. 
	 *
	 * @param tranferObject - TranferObject
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(TransferObject transferObject) throws BDException, ApplicationException{
		return this.getHibernateUtil().count(transferObject);
	}
	
	/**
	 * Retorna o primeiro objeto encontrado que satisfaça as condições de busca.
	 * 
	 * @param hql - String
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(String hql) throws BDException, ApplicationException{
		return this.getHibernateUtil().buscarObjeto(hql);
	}
	
	/**
	 * Executa um comando SQL nativo na Banco de Dados.
	 * @param sql - String
	 * @throws ApplicationException
	 */
	public void executarSQL(String sql) throws BDException, ApplicationException{
		this.getHibernateUtil().executarSQL(sql);
	}
	
	/**
	 * Retorna o primeiro objeto encontrado que satisfaça as condições de busca.
	 * 
	 * @param hql - String
	 * @return List<Object>
	 * @throws BDException
	 */
	public List<Object> executar(String hql) throws BDException, ApplicationException{
		return this.getHibernateUtil().executar(hql);
	}

	/**
	 * Retorna o primeiro objeto encontrado que satisfaça as condições de busca.
	 * 
	 * @param hql - String
	 * @return List<Object>
	 * @throws BDException
	 */
	public List<Object> executar(Query query) throws BDException, ApplicationException{
		return this.getHibernateUtil().executar(query);
	}
	
	/**
	 * Executa o objeto Criteria
	 * @param criteria - Criteria
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> executar(Criteria criteria) throws BDException, ApplicationException{
		return this.getHibernateUtil().executar(criteria);
	}

	/**
	 * Retorna o primeiro objeto encontrado que satisfaça as condições de busca.
	 * 
	 * @param criteria - Criteria
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(Criteria criteria) throws BDException, ApplicationException{
		return this.getHibernateUtil().buscarObjeto(criteria);
	}
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return Object
	 * @throws BDException
	 */
	public Object buscarObjeto(TransferObject transferObject) throws BDException, ApplicationException{
		return this.getHibernateUtil().buscarObjeto(transferObject);
	}
	
	/**
	 * Realiza um select baseado nas propriedades setadas do objeto passado como parâmetro. E retorna um resultado apenas.
	 * Obs: Popule o objeto de maneira que ele seja único. Dessa forma é garantido o retorno do objeto certo.
	 * 
	 * @param tranferObject - TranferObject
	 * @param classe - Class<T>
	 * @return T - <T extends TransferObject>
	 * @throws BDException
	 */
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException {
		return this.getHibernateUtil().buscarObjeto(transferObject, classe);
	}
	

	/**
	 * Método responsável por listar todos os objetos semelhantes ao TransferObject 
	 * passado como parâmentro utilizando como filtro as propriedades inseridas no
	 * objeto passado como parâmetro.
	 *
	 * @param tranferObject - TranferObject
	 * @return List
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject) throws BDException, ApplicationException{
		return this.getHibernateUtil().listar(transferObject);
	}
	
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
	public List<TransferObject> listar(TransferObject transferObject, String orderBy, OrderType orderType) throws BDException{
		return this.getHibernateUtil().listar(transferObject, orderBy, orderType);
	}

	/**
	 * Método responsável por listar todos os objetos semelhantes ao TransferObject 
	 * passado como parâmentro utilizando como filtro as propriedades inseridas no
	 * objeto passado como parâmetro.
	 *
	 * @param to - TranferObject
	 * @param orderBy - String, parâmetro de ordenação
	 * @return List
	 * @throws BDException
	 */
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws HibernateException, ApplicationException{
		return this.getHibernateUtil().listar(transferObject, orderBy);
	}
	
	/**
	 * Retorna uma lista de TO específico ordenada por um determinado parâmetro
	 * @param transferObject - Objeto a ser listado
	 * @param orderBy - Atributo do TO que sera utilizado como parâmetro de cláusula ORDER
	 * @param classe - Class<T> - T extends TransferObject
	 * @return List
	 * @throws BDException
	 */
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException{
		return this.getHibernateUtil().listar(transferObject, classe, orderBy);
	}
	
	/**
	 * Método responsável por retornar os objetos com paginação
	 * 
	 * @param transferObject - TransferObject
	 * @param info - PaginacaoInfo
	 * @return Paginacao
	 * @throws BDException
	 */
	public Paginacao listar(TransferObject transferObject, PaginacaoInfo info) throws BDException, ApplicationException{
		return this.getHibernateUtil().listar(transferObject, info);
	}
	
	/**
	 * Retorna o SessionFactory construido pelo Spring no star-up da aplicação.
	 * 
	 * @return SessionFactory
	 * @throws BDException
	 */
	public void flush() throws BDException, ApplicationException{
		this.getHibernateUtil().flush();
	}
	
	/**
	 * Limpa a sessão atualmente ativa com o banco de dados.
	 *
	 * @throws BDException
	 * @throws ApplicationException 
	 * @return void
	 */
	public void clear() throws BDException, ApplicationException {
		this.getSession().clear();
	}
	
	/**
	 * Retorna o objeto SessionFactory
	 * @return SessuionFactory
	 * @throws BDException
	 */
	public SessionFactory getSessionFactory() throws BDException, ApplicationException{
		return this.getHibernateUtil().getSessionFactory();
	}
	
	/**
	 * Retorna um objeto Session do Hibernate
	 * @return Session
	 * @throws BDException
	 * @throws ApplicationException
	 */
	public Session getSession() throws BDException, ApplicationException{
		return this.getSessionFactory().getCurrentSession();
	}
	/**
	 * Cria um objeto Query generico para o hibernate
	 * @param str - String
	 * @return Query
	 * @throws BDException
	 */
	public Query createQuery(String str) throws BDException, ApplicationException{
		return this.getHibernateUtil().getSessionFactory().getCurrentSession().createQuery(str);
	}
	
	/**
	 * Cria um objeto CRITERIA do Hibernate
	 * @param classe - Class
	 * @return Criteria
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public Criteria createCriteria(Class classe) throws BDException, ApplicationException{
		return this.getSessionFactory().getCurrentSession().createCriteria(classe);
	}
	
	/**
	 * Retira um objeto da sessão atual do Hibernate
	 * @param bean - TransferObject
	 */
	public void retirarObjetoSessao(TransferObject bean) throws ApplicationException{
		this.getHibernateUtil().retirarObjetoSessao(bean);
	}
	
	/**
	 * Retorna o ID do objeto controlado
	 * @param bean
	 * @return
	 */
	public Object getAtributoIdentificadorTransferObject(TransferObject bean) throws ApplicationException{
		return this.getHibernateUtil().getAtributoIdentificadorTransferObject(bean);
	}
	
	/**
	 * Realiza operação de load
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public <T extends TransferObject> T load(Class<T> classe, TransferObject transferObject) throws BDException{
		T bean = (T)this.getSession().load(classe, transferObject.getKey());
		return bean;
	}
	
	/**
	 * Realiza operação de get. Caso não exista no banco de dados o objeto com id especificado
	 * retorna null.
	 * @param classe - Class<T> - T extends TransferObject
	 * @param transferObject - TransferObject
	 * @return bean = T = TransferObject
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public <T extends TransferObject> T get(Class<T> classe, TransferObject transferObject) throws BDException{
		T bean = (T)this.getSession().get(classe, transferObject.getKey());
		return bean;
	}
	
}
