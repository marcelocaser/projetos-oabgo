package core.hibernate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import core.dao.Parametro;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;
import core.utilitario.Data;
import core.utilitario.Util;


/**
 * 
 * <b>Titulo:</b> HibernateUtil.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class HibernateUtil extends HibernateDaoSupport{
	

	/**
	 * Conta os registros existentes para essa consulta. 
	 *
	 * @param criteria
	 * @param id
	 * @throws BDException
	 * @throws ApplicationException
	 * @return Integer
	 */
	public Integer count(Criteria criteria, String id) throws BDException, ApplicationException{
		//busca o atributo identificador pelo metada do Hibernate
		criteria.setProjection(Projections.countDistinct(id)); 
		Integer registros = ((Integer)criteria.list().get(0)).intValue();
		return registros;
	}

	/**
	 * Conta os registros existentes para essa consulta. 
	 *
	 * @param tranferObject - TranferObject
	 * @return Integer
	 * @throws BDException
	 * @throws ApplicationException
	 */
	public Integer count(TransferObject transferObject) throws BDException, ApplicationException{
		HqlDinamico hql = new HqlDinamico(this.getSession(), transferObject);
		Criteria criteria = hql.getCriteria();
		//busca o atributo identificador pelo metada do Hibernate
		String id = hql.getAtributoIdentificadorTransferObject(transferObject);
		criteria.setProjection(Projections.countDistinct(id)); 
		Integer registros = ((Integer)criteria.list().get(0)).intValue();
		return registros;
	}
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param hql - String
	 * @return List
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<Object> executar(String hql) throws BDException, ApplicationException{
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}

	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param hql - String
	 * @return List
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<Object> executar(Query query) throws BDException, ApplicationException{
		return query.list();
	}

	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param hql - String
	 * @return List
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> executar(Criteria criteria) throws BDException, ApplicationException{
		return criteria.list();
	}

	/**
	 * Executa um comando SQL nativo na Banco de Dados.
	 * @param sql - String
	 * @throws ApplicationException
	 */
	public void executarSQL(String sql) throws BDException, ApplicationException{
		SQLQuery sqlQuery = this.getSession().createSQLQuery(sql);
		sqlQuery.executeUpdate();
	}
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return Object
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public Object buscarObjeto(String hql) throws BDException, ApplicationException{
		Query query = this.getSession().createQuery(hql);
		List<Object> resultado = query.list();
		if(resultado.size() > 0){
			return resultado.get(0);
		}else{
			return null;			
		}
	}
	
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return Object
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public Object buscarObjeto(TransferObject transferObject) throws BDException, ApplicationException{
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject);
		Criteria criteria = format.getCriteria();
		List<Object> resultado = criteria.list();
		if(resultado.size() > 0){
			return resultado.get(0);
		}else{
			return null;			
		}
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
	@SuppressWarnings(value="unchecked")
	public <T extends TransferObject> T buscarObjeto(TransferObject transferObject, Class<T> classe) throws BDException {
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject);
		Criteria criteria = format.getCriteria();
		criteria.setMaxResults(1);
		return (T)criteria.uniqueResult();
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
	@SuppressWarnings(value="unchecked")
	public List<TransferObject> listar(TransferObject transferObject) throws BDException, ApplicationException{
		List<TransferObject> resultado = new ArrayList<TransferObject>();
		//aciona o objeto responsável por gerar um objeto Query dinâmico
		// contendo as cláusulas de restrições baseadas nas propriedades do objeto
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject);
		Criteria criteria = format.getCriteria();
		resultado = criteria.list();
		return resultado;
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
	@SuppressWarnings(value="unchecked")
	public List<TransferObject> listar(TransferObject transferObject, String orderBy, OrderType orderType) throws BDException{
		List<TransferObject> resultado = new ArrayList<TransferObject>();
		//aciona o objeto responsável por gerar um objeto Query dinâmico
		// contendo as cláusulas de restrições baseadas nas propriedades do objeto
		//ordenando pelo parâmetro passado
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject, orderBy, orderType);
		Criteria criteria = format.getCriteria();
		resultado = criteria.list();
		return resultado;
	}
	
	/**
	 * Retorna uma lista de TO ordenadas por um determinado parâmetro
	 * @param transferObject - Objeto a ser listado
	 * @param orderBy - Atributo do TO que sera utilizado como parâmetro de cláusula ORDER
	 * @return List
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public List<TransferObject> listar(TransferObject transferObject, String orderBy) throws BDException{
		List<TransferObject> resultado = new ArrayList<TransferObject>();
		//aciona o objeto responsável por gerar um objeto Query dinâmico
		// contendo as cláusulas de restrições baseadas nas propriedades do objeto
		//ordenando pelo parâmetro passado
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject, orderBy, OrderType.ASC);
		Criteria criteria = format.getCriteria();
		resultado = criteria.list();
		return resultado;
	}
	
	/**
	 * Retorna uma lista de TO específico ordenada por um determinado parâmetro
	 * @param transferObject - Objeto a ser listado
	 * @param orderBy - Atributo do TO que sera utilizado como parâmetro de cláusula ORDER
	 * @param classe - Class<T> - T extends TransferObject
	 * @return List
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public <T extends TransferObject> List<T> listar(TransferObject transferObject, Class<T> classe, String orderBy) throws BDException{
		List<T> resultado = new ArrayList<T>();
		//aciona o objeto responsável por gerar um objeto Query dinâmico
		// contendo as cláusulas de restrições baseadas nas propriedades do objeto
		//ordenando pelo parâmetro passado
		HqlDinamico format = new HqlDinamico(this.getSession(), transferObject, orderBy, OrderType.ASC);
		Criteria criteria = format.getCriteria();
		resultado = criteria.list();
		return resultado;
	}
	
	/**
	 * Retorna os objetos utilizando recursos de paginação
	 * @param transferObject
	 * @param info
	 * @return ListaPaginada
	 * @throws BDException
	 */
	public Paginacao listar(TransferObject transferObject, PaginacaoInfo info) throws BDException, ApplicationException{
		return this.listar(transferObject, info, null);
	}
	
	/**
	 * Retorna os objetos utilizando recursos de paginação
	 * @param transferObject
	 * @param info - ListaPaginadaInfo
	 * @param param - Parametro
	 * 
	 * @return ListaPaginada
	 * @throws BDException
	 */
	public Paginacao listar(TransferObject transferObject, PaginacaoInfo info, Parametro param) throws BDException, ApplicationException{
		//configura o rótulo dinâmico do DataScroller
		if(Util.possuiAtributos(transferObject)){
			info.setRotuloDataScroller(info.getRotuloFiltro());
		}
		HqlDinamico hql = new HqlDinamico(this.getSession(), transferObject, info.getOrderBy(), info.getOrderType());
		Criteria criteria = hql.getCriteria();
		Criteria count = hql.getCount();
		//insere os parâmetros adicionais no objeto de consulta
		this.addParamCriteria(criteria, param);
		//insere os parâmetros adicionais no objeto de CONTAGEM
		this.addParamCriteria(count, param);
		return this.construirPaginacao(criteria, count, info);
	}
	
	/**
	 * Inicializa as propriedades do TransferObject passado como parâmetro.
	 *
	 * @param bean - {@link Object}
	 * @throws BDException
	 * @return void
	 */
	public void inicializar(Object bean) throws BDException{
		this.getHibernateTemplate().initialize(bean);
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
		//Objeto que constroi um CRITERIA dinamico recebendo um SESSION, um TO
		//contendo as propriedades e um boolean que indica que se os campos String serão
		//consultados com LIKE ou EQ.
		HqlDinamico hql = new HqlDinamico(this.getSession(), bean, false);
		Criteria criteria = hql.getCriteria();
		return this.buscarObjeto(criteria) != null ? true : false;
	}

	/**
	 * Verifica se existe um outro TO com as mesmas propriedades do TO passado como parâmetro.
	 * 
	 * @param bean - TransferObject - TO que será persistido
	 * @param beanTmp - TransferObject - TO com as propriedades a serem comparadas
	 * @return boolean
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public boolean beforeUpdate(TransferObject bean, TransferObject beanTmp) throws BDException{
		boolean retorno = true;
		HqlDinamico hql = new HqlDinamico(this.getSession(), beanTmp, false);
		Criteria criteria = hql.getCriteria();
		List<TransferObject> lista = criteria.list();
		if(lista.isEmpty()){
			retorno = false;
		}else if(lista.size() > 1){
			//retira todos os objetos da sessão
			for(TransferObject to : lista){
				this.retirarObjetoSessao(to);
			}
			retorno = true;
		}else{
			TransferObject to = lista.get(0);
			if(to.getKey().equals(bean.getKey())){
				retorno = false;
			}
			//retira o objeto da sessão
			this.retirarObjetoSessao(to);
		}
		return retorno;
	}
	
	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return TransferObject
	 * @throws BDException
	 */
	public Object buscarObjeto(Query query) throws BDException, ApplicationException{
		List resultado = query.list();
		if(resultado.size() > 0){
			return resultado.get(0);
		}else{
			return null;			
		}
	}

	/**
	 * Realiza uma com base nas propriedades do TO passado como parâmetro e retorna o primeiro objeto encontrado
	 *
	 * @param tranferObject - TranferObject
	 * @return TransferObject
	 * @throws BDException
	 */
	@SuppressWarnings(value="unchecked")
	public Object buscarObjeto(Criteria criteria) throws BDException, ApplicationException{
		List<TransferObject> resultado = criteria.list();
		if(resultado.size() > 0){
			return resultado.get(0);
		}else{
			return null;			
		}
	}
	
	/**
	 * Retorna o atributo identificador do objeto
	 *
	 * @param bean - {@link TransferObject}
	 * @return String
	 */
	public String getAtributoIdentificadorTransferObject(TransferObject bean){
		return this.getSessionFactory().getClassMetadata(bean.getClass()).getIdentifierPropertyName();
	}
	
	/**
	 * Persiste as informações contidas em uma objeto TrasferObject para o Banco.
	 * 
	 * @param tranferObject
	 * @throws BDException
	 */
	public void incluir(TransferObject tranferObject)throws BDException {
		this.getHibernateTemplate().save(tranferObject);
	}
	
	/**
	 * Exclui um objeto TranferObject
	 * 
	 * @param transferObject
	 * @throws BDException
	 */
	public void excluir(TransferObject transferObject)throws BDException {
		this.getHibernateTemplate().delete(transferObject);
	}
	
	/**
	 * Deleta uma coleção de itens passados como parâmetro
	 * @param listaItens - List<TransferObject>
	 * @throws BDException
	 */
	public void excluir(Collection<TransferObject> listaItens) throws BDException{
		this.getHibernateTemplate().deleteAll(listaItens);
	}
	
	/**
	 * Persiste as alterações contidas no TranferObject passado como parâmetro
	 * 
	 * @param transferObject
	 * @throws BDException
	 */
	public void alterar(TransferObject transferObject)throws BDException{
		this.getHibernateTemplate().update(transferObject);
	}

	/**
	 * Persiste as alterações contidas no TranferObject passado como parâmetro realizando um "Merge"
	 * entre as propriedades.
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void merge(TransferObject transferObject)throws BDException{
		this.getHibernateTemplate().merge(transferObject);
	}

	/**
	 * Persiste as alterações contidas no TranferObject passado como parâmetro realizando um "Merge"
	 * entre as propriedades.
	 * 
	 * @param transferObject - TransferObject
	 * @throws BDException
	 */
	public void saveOrUpdate(TransferObject transferObject)throws BDException{
		this.getHibernateTemplate().saveOrUpdate(transferObject);
	}
	
	/**
	 * Consulta um objeto com base na propriedade chave.
	 * 
	 * @param classe - Class
	 * @param transferObject - TransferObject
	 * @return TransferObject
	 * @throws BDException
	 */
	public TransferObject consultar(Class<? extends TransferObject> classe, TransferObject transferObject) throws BDException{
		TransferObject bean = (TransferObject)this.getHibernateTemplate().load(classe, transferObject.getKey());
		return bean;
	}
	
	/**
	 * Força a execução de um flush() nas informações armazenadas em memória
	 * @throws BDException
	 */
	public void flush() throws BDException{
		SessionFactoryUtils.getSession(this.getSessionFactory(), false).flush();
	}
	
	/**
	 * Retira um objeto da sessão atual do hibernate
	 * @param bean - TransferObject
	 */
	public void retirarObjetoSessao(TransferObject bean) {
		this.getHibernateTemplate().evict(bean);
	}
	
	/**
	 * Constroi o objeto LISTAPAGINADA listando uma consulta com paginação.
	 * 
	 * @param criteria - Criteria que realiza as consultas
	 * @param count - Criteria que realiza a contagem dos objetos
	 * @param info - Objeto contendo as informações para a configuração da lista
	 * @return ListaPaginada
	 * @throws BDException
	 */
	private Paginacao construirPaginacao(Criteria criteria, Criteria count, PaginacaoInfo info) throws BDException {
		Paginacao resultado = new Paginacao();
		// configura o rótulo dinâmico do DataScroller
		info.setRotuloDataScroller(info.getRotuloFiltro());
		//executa o HQL de contagem dos objetos
		int qtdRegistros =  ((Integer)count.list().get(0)).intValue();
		resultado.setSize(qtdRegistros);
		info.setQtdItens(qtdRegistros);
		
		// se a quantidade de registros necessitar de paginação
		if (qtdRegistros > info.getLinhasPorPagina()) {
			criteria.setFirstResult(info.getStart());
			criteria.setMaxResults(info.getLinhasPorPagina());
		} 
		
		List<TransferObject> lista = montarListaObjetos(criteria);
		resultado.setList(lista);
		resultado.setRows(lista.size());
		resultado.setPaginaAtual(info.getPaginaAtual());
		resultado.setLinhasPorPagina(info.getLinhasPorPagina());
		return resultado;
	}	
	
	/**
	 * Constroi a lista de objetos dinamicamente baseada nos ID'S ou nos TO'S.
	 * @param criteria - Criteria
	 * @return - List<TransferObject>
	 */
	@SuppressWarnings("unchecked")
	private List<TransferObject> montarListaObjetos(Criteria criteria){
		List<TransferObject> lista = new ArrayList<TransferObject>();
		// realiza a busca
		List list = criteria.list();
		if(list == null || list.isEmpty()){
			return lista;
		}else{
			if(list.get(0) instanceof Number){
				//busca os objetos com base nos ID'S
				for(int i = 0; i < list.size(); i ++){
					TransferObject bean = (TransferObject)this.getHibernateTemplate().load(((CriteriaImpl)criteria).getEntityOrClassName(), (Number)list.get(i));
					lista.add(bean);
				}				
			}else{
				lista.addAll(list);
			}
			return lista;
		}
	}
	
	/**
	 * Insere os parametros passados no objeto Query para realizar a consulta
	 * @param criteria - Criteria
	 * @param param - Parametro
	 * @throws NullPointerException
	 */
	private void addParamCriteria(Criteria criteria, Parametro param) throws NullPointerException{
		//verifica se o objeto param e != de NULL para buscar os parametros passados
		if(param != null){
			//verifica a existencia dos campos correspondentes ao PERIODO 1
			if(param.getValor(Parametro.PRM_DATA1_INICIO) != null && param.getValor(Parametro.PRM_DATA1_FIM) != null){
				Date dataInicio = Data.zerarHoraMinutoSegundo((Date)param.getValor(Parametro.PRM_DATA1_INICIO));
				Date dataFim = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA1_FIM));
				criteria.add((Criterion)Restrictions.between(param.getValor(Parametro.PRM_NOME_ATRIBUTO1).toString(), dataInicio, dataFim));
			}else if(param.getValor(Parametro.PRM_DATA1_INICIO) != null && param.getValor(Parametro.PRM_DATA1_FIM) == null){
				Date dataInicio = Data.zerarHoraMinutoSegundo((Date)param.getValor(Parametro.PRM_DATA1_INICIO));
				criteria.add((Criterion)Restrictions.ge(param.getValor(Parametro.PRM_NOME_ATRIBUTO1).toString(), dataInicio));
			}else if(param.getValor(Parametro.PRM_DATA1_INICIO) == null && param.getValor(Parametro.PRM_DATA1_FIM) != null){
				Date dataFim = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA1_FIM));
				criteria.add((Criterion)Restrictions.le(param.getValor(Parametro.PRM_NOME_ATRIBUTO1).toString(), dataFim));
			}
	
			//verifica a existencia dos campos correspondentes ao PERIODO 2
			if(param.getValor(Parametro.PRM_DATA2_INICIO) != null && param.getValor(Parametro.PRM_DATA2_FIM) != null){
				Date dataInicio = (Date)param.getValor(Parametro.PRM_DATA2_INICIO);
				Date dataFim = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA2_FIM));
				criteria.add((Criterion)Restrictions.between(param.getValor(Parametro.PRM_NOME_ATRIBUTO2).toString(), dataInicio, dataFim));
			}else if(param.getValor(Parametro.PRM_DATA2_INICIO) != null && param.getValor(Parametro.PRM_DATA2_FIM) == null){
				Date dataInicio = (Date)param.getValor(Parametro.PRM_DATA2_INICIO);
				criteria.add((Criterion)Restrictions.ge(param.getValor(Parametro.PRM_NOME_ATRIBUTO2).toString(), Data.zerarHoraMinutoSegundo(dataInicio)));
			}else if(param.getValor(Parametro.PRM_DATA2_INICIO) == null && param.getValor(Parametro.PRM_DATA2_FIM) != null){
				Date dataFim = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA2_FIM));
				criteria.add((Criterion)Restrictions.le(param.getValor(Parametro.PRM_NOME_ATRIBUTO2).toString(), dataFim));
			}
	
			//verifica a existencia dos campos correspondentes ao PERIODO 3
			if(param.getValor(Parametro.PRM_DATA3_INICIO) != null && param.getValor(Parametro.PRM_DATA3_FIM) != null){
				Date dataInicio = Data.zerarHoraMinutoSegundo((Date)param.getValor(Parametro.PRM_DATA3_INICIO));
				Date dataFim    = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA3_FIM));
				criteria.add((Criterion)Restrictions.between(param.getValor(Parametro.PRM_NOME_ATRIBUTO3).toString(), dataInicio, dataFim));
			}else if(param.getValor(Parametro.PRM_DATA3_INICIO) != null && param.getValor(Parametro.PRM_DATA3_FIM) == null){
				Date dataInicio = Data.zerarHoraMinutoSegundo((Date)param.getValor(Parametro.PRM_DATA3_INICIO));
				criteria.add((Criterion)Restrictions.ge(param.getValor(Parametro.PRM_NOME_ATRIBUTO3).toString(), dataInicio));
			}else if(param.getValor(Parametro.PRM_DATA3_INICIO) == null && param.getValor(Parametro.PRM_DATA3_FIM) != null){
				Date dataFim = Data.setLimiar((Date)param.getValor(Parametro.PRM_DATA3_FIM));
				criteria.add((Criterion)Restrictions.le(param.getValor(Parametro.PRM_NOME_ATRIBUTO3).toString(), dataFim));
			}
			
			//verifica a existencia de campos relacionados a faixa de valores monetarios
			if(param.getValor(Parametro.PRM_VALOR_MINIMO) != null && param.getValor(Parametro.PRM_VALOR_MAXIMO) != null){
				criteria.add((Criterion)Restrictions.ge(param.getValor(Parametro.PRM_ATRIBUTO_VALOR).toString(), param.getValor(Parametro.PRM_VALOR_MINIMO)));
				criteria.add((Criterion)Restrictions.le(param.getValor(Parametro.PRM_ATRIBUTO_VALOR).toString(), param.getValor(Parametro.PRM_VALOR_MAXIMO)));
			}else if(param.getValor(Parametro.PRM_VALOR_MINIMO) != null && param.getValor(Parametro.PRM_VALOR_MAXIMO) == null){
				criteria.add((Criterion)Restrictions.ge(param.getValor(Parametro.PRM_ATRIBUTO_VALOR).toString(), param.getValor(Parametro.PRM_VALOR_MINIMO)));
			}else if(param.getValor(Parametro.PRM_VALOR_MINIMO) == null && param.getValor(Parametro.PRM_VALOR_MAXIMO) != null){
				criteria.add((Criterion)Restrictions.le(param.getValor(Parametro.PRM_ATRIBUTO_VALOR).toString(), param.getValor(Parametro.PRM_VALOR_MAXIMO)));
			}
		}
	}
}
