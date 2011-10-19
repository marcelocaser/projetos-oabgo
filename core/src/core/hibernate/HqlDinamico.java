package core.hibernate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.utilitario.Util;


/**
 * <b>Classe:</b> HqlDinamico.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.hibernate <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author leonardop
 * @version Revision: $ Date: $
 */
public class HqlDinamico {
	
	private TransferObject			bean;
	private Session   				session;
	private Query					query;
	private List<Relacionamento> 	relacionamentos = new ArrayList<Relacionamento>();
	private Criteria				criteria;
	private Criteria				count;
	private boolean					like = true;
	private OrderType				order;
	private String				 	orderBy;
	
	//parametros de construção LOCAL
	private List<Property>			listProperty;
	
	
	/**
	 * Contrutor da classe OqlFormat.java
	 * @param session - Session
	 * @param bean - TransferObject
	 */
	public HqlDinamico(Session session, TransferObject bean) {
		this.bean = bean;
		this.session = session;
		this.listProperty = this.formatCriteria(bean);
	}


	/**
	 * Contrutor da classe OqlFormat.java
	 * @param session - Session
	 * @param bean - TransferObject
	 */
	public HqlDinamico(Session session, TransferObject bean, boolean like ) {
		this.bean = bean;
		this.session = session;
		this.like = like;
		this.listProperty = this.formatCriteria(bean);
		
	}

	
	/**
	 * Contrutor da classe OqlFormat.java
	 * @param session - Session
	 * @param bean - TransferObject
	 * @param orderBy - String - EL de Oredenação
	 * @param orderType - OrderType - Constante que indica se a ordenação é ASC ou DESC
	 */
	public HqlDinamico(Session session, TransferObject bean, String orderBy, OrderType orderType) {
		this.bean = bean;
		this.session = session;
		this.orderBy = orderBy;
		this.order = orderType;
		this.listProperty = this.formatCriteria(bean);
	}

	/**
	 * Contrutor da classe OqlFormat.java
	 * @param session - Session
	 * @param bean - TransferObject
	 * @param orderBy - String
	 * @param like - boolean
	 */
	public HqlDinamico(Session session, TransferObject bean, boolean like,  String orderBy, OrderType order) {
		this.bean = bean;
		this.session = session;
		this.orderBy = orderBy;
		this.like = like;
		this.order = order;
		this.listProperty = this.formatCriteria(bean);
	}
	
	/**
	 * Cria um objeto criteria para a busca.
	 */
	private void createCriteria(){
		Criteria criteria = session.createCriteria(bean.getClass());
		Criteria count = session.createCriteria(bean.getClass());
		
		//insere os parâmetros de filtragem WHERE
		for(Property prop : this.listProperty){
			//se for uma propriedade do tipo STRING realiza a busca com LIKE
			if(prop.getType() != null && prop.getType() instanceof String && getLike()){
				criteria.add((Criterion)Restrictions.like(prop.getCaminho(), prop.getValue().toString(), MatchMode.ANYWHERE).ignoreCase());	
				count.add((Criterion)Restrictions.like(prop.getCaminho(), prop.getValue().toString(), MatchMode.ANYWHERE).ignoreCase());	
			}else{
				criteria.add((Criterion)Restrictions.eq(prop.getCaminho(), prop.getValue()));
				count.add((Criterion)Restrictions.eq(prop.getCaminho(), prop.getValue()));
			}
		}
		
		//se existir uma parâmetro de ordenação, insere na Query
		if(getOrderBy() != null && !getOrderBy().equals("null") && !getOrderBy().equals("")){
			try{
				//se existir "." é propriedade composta
				if(getOrderBy().indexOf('.') != -1){
					criarOrdenacaoComposta(criteria, getOrderBy());
				}else{
					//verifica se a propriedade primitiva existe realmente no TO
					if(PropertyUtils.isReadable(this.bean, getOrderBy())){
						//verifica se a ordenação e ASC ou DESC
						if(getOrder() == OrderType.ASC){
							criteria.addOrder(Order.asc(getOrderBy()).ignoreCase());
						}else{
							criteria.addOrder(Order.desc(getOrderBy()).ignoreCase());						
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("A Consulta não pode ser ordenada pelo atributo: "+getOrderBy());

			}
		}
		
		//insere os relacionamento no objeto Critéria
		for(Relacionamento relacionamento : this.relacionamentos){
			criteria.createCriteria(relacionamento.getObjectName(), relacionamento.getObjectAlias(), Criteria.LEFT_JOIN);
			count.createCriteria(relacionamento.getObjectName(), relacionamento.getObjectAlias(), Criteria.LEFT_JOIN);
		}
		
		//insere o projection de Distinct para não trazer objetos repetidos
		this.distictCriteriaCount(count);
		//Critéria utilizado na busca
		this.setCriteria(criteria);
		//critéria utilizado na contagem dos registros
		this.setCount(count);
	}
	


	/**
	 * Insere a cláusula DISTINCT para o Criteria de contagem
	 * @param criteria - Criteria
	 */
	private void distictCriteriaCount(Criteria count){
		String id = this.session.getSessionFactory().getClassMetadata(getBean().getClass()).getIdentifierPropertyName();
		count.setProjection(Projections.countDistinct(id)); 
	}
	
	/**
	 * Retorna o atributo identificador do objeto
	 *
	 * @param bean - {@link TransferObject}
	 * @return String
	 */
	public String getAtributoIdentificadorTransferObject(TransferObject bean){
		return this.session.getSessionFactory().getClassMetadata(getBean().getClass()).getIdentifierPropertyName();
	}
	

	
	/**
	 * Constroi um objeto Query dinâmico para o TO passado como parâmetro
	 */
	private void createQuery(){
		StringBuffer hql = new StringBuffer();
		hql.append(" from ").append(bean.getClass().getName()).append(" BEAN ");
		//insere os atributos da cláusula WHERE
		if(!this.listProperty.isEmpty()){
			hql.append(" where ");
			int loop = 0;
			for(int i = 0; i < this.listProperty.size(); i++){
				Property prop = this.listProperty.get(i);
				//se a filtragem utilizar LIKe para campos STRING
				if(prop.getType() != null && prop.getType() instanceof String && getLike()){
					hql.append(" BEAN."+prop.getCaminho()+" like '%"+prop.getValue()+"%' ");	
					//remove a propriedade para não replicar o parâmetro
					this.listProperty.remove(i);
				}else{
					//seta um parâmetro simples
					hql.append(" BEAN."+prop.getCaminhoCompleto()+" = :"+prop.getCaminhoCompleto().replace('.', '_')+" ");					
				}
				loop++;
				//se existirem mais propriedades concatena um AND
				if(this.listProperty.size() > loop){
					hql.append(" and ");
				}
			}
		}
		//insere a cláusula de ordenação
		if(getOrderBy() != null && !getOrderBy().equals("null") && !getOrderBy().equals("")){
			if(getOrder() == OrderType.ASC){
				hql.append(" order by ").append(getOrder()).append(" ASC ");
			}else{
				hql.append(" order by ").append(getOrder()).append(" DESC ");
			}
		}
		//insere os PARÂMETROS na QUERY
		try{
			Query query = session.createQuery(hql.toString());
			if(!this.listProperty.isEmpty()){
				for(Property prop : this.listProperty){
					query.setParameter(prop.getCaminhoCompleto().replace('.', '_'), prop.getValue());
				}
			}
			this.query = query;
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retorna um LIST com objetos do tipo PROPERTY (Inner Class) contendo as
	 * propriedades dos InnerJoin para a construção do HQL.
	 * 
	 * @param bean - TransferObject
	 * @return List
	 */
	protected List<Property> formatCriteria(TransferObject bean){
		List<Property> list = new ArrayList<Property>();
		//retorna um Array contendo todos os atributos do objeto
		Field[] fields = construirListaFields(bean);
		//popula com a lista de fields do objeto primitivo
		popularListaParametros(bean, fields, list);
		//retorna um lista contendo objetos do tipo Porperty
		return list;
	}
	
	/**
	 * Constroi o caminho crítico com base nos FIELD'S da entidade passada como parâmetro.
	 * @param bean - TransferObject
	 * @param fields - Field[]
	 * @param list - List<Property>
	 */
	private void popularListaParametros(TransferObject bean, Field[] fields, List<Property> list){
		for(Field field : fields){
			try{
				//pega o nome do atributo
				String fieldName = field.getName();
				//pega o valor do atributo
				Object fieldValue = PropertyUtils.getSimpleProperty(bean, fieldName);
				//verifica se o atributo possui um valor
				if(fieldValue != null){
					//verifica se o valor do atributo é um TO
					if(fieldValue instanceof TransferObject){
						//busca o caminho crítico das porpriedades do  objeto interno
						List<Property> innerProperts = this.construirCaminhoCritico((TransferObject)fieldValue, fieldName, fieldName);
						//insere a lista de propriedades no objeto de retorno
						list.addAll(innerProperts);
					}else{
						//verifica se o valor do atributo e diferente de ""
						if(!fieldValue.equals("")){
							//tenta criar uma nova instância do tipo do valor do campo, se não conseguir insere NULL
							try{
								Property prop = new Property(fieldName, fieldName, fieldValue, fieldValue.getClass().newInstance());
								list.add(prop);
							}catch (InstantiationException e) {
								Property prop = new Property(fieldName, fieldName, fieldValue, null);
								list.add(prop);
							}
						}
					}
				}
			}catch (NoSuchMethodException e) {
				e.printStackTrace();
			}catch (InvocationTargetException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Método recursivo para busca em objeto composto (TO).
	 * 
	 * @param bean
	 * @param alias
	 * @return List
	 */
	private List<Property> construirCaminhoCritico(TransferObject bean, String objectName, String alias){
		//insere um novo objeto de relacionamento para que o Criteria possa
		//localizar o objeto
		String aliasRelacionamento = createAlias(alias);
		this.relacionamentos.add(new Relacionamento(objectName, aliasRelacionamento));
		List<Property> list = new ArrayList<Property>();
		//captura os atributos do objeto
		Field[] fields = construirListaFields(bean);
		
		//percorre todos os atributos do objeto
		for(Field field : fields){
			try{
				//pega o nome do atributo
				String fieldName = field.getName();
				//pega o valor do atributo
				Object fieldValue = PropertyUtils.getSimpleProperty(bean, fieldName);
				//verifica se o atributo possui um valor
				if(fieldValue != null){
					//insere o objeto interno como objeto de relacionamento
					//verifica se o valor do atributo é um TO
					if(fieldValue instanceof TransferObject){
						//adiciona um novo objeto de relacionamento ao List para o Criteria
						//busca o caminho crítico das porpriedades do  objeto interno
						List<Property> innerProperts = this.construirCaminhoCritico((TransferObject)fieldValue, aliasRelacionamento+"."+fieldName, fieldName);
						//insere a lista de propriedades no objeto de retorno
						list.addAll(innerProperts);
					}else{
						//verifica se o valor do atributo e diferente de ""
						if(!fieldValue.equals("")){
							//tenta criar uma nova instância do tipo do valor do campo, se não conseguir insere NULL
							try{
								Property prop = new Property(objectName+"."+fieldName, aliasRelacionamento+"."+fieldName, fieldValue, fieldValue.getClass().newInstance());
								list.add(prop);
							}catch (InstantiationException e) {
								Property prop = new Property(objectName+"."+fieldName, aliasRelacionamento+"."+fieldName, fieldValue, null);
								list.add(prop);
							}
						}
					}
				}
			}catch (NoSuchMethodException e) {
				e.printStackTrace();
			}catch (InvocationTargetException e) {
				e.printStackTrace();
			}catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Constroi a lista de FIELDS de um objeto para o HQL Dinâmico
	 * @param bean - Class
	 * @return Field[]
	 */
	@SuppressWarnings("unchecked")
	private Field[] construirListaFields(TransferObject bean){
		List<Field> retorno = new ArrayList<Field>();
		//busca os fields da entidade Base
		for(int i = 0; i < bean.getClass().getDeclaredFields().length; i++){
			retorno.add(bean.getClass().getDeclaredFields()[i]);
		}

		//busca os fields das entidade superiores até (TransferObject)
		Class superClass = bean.getClass().getSuperclass();
		while(!superClass.getName().equals(TransferObject.class.getName())){
			for(int i = 0; i < superClass.getDeclaredFields().length; i++){
				retorno.add(superClass.getDeclaredFields()[i]);
			}
			superClass = superClass.getSuperclass();
		}
		return convertListToArray(retorno);
	}
	
	private Field[] convertListToArray(List<Field> lista){
		Field[] retorno = new Field[lista.size()];
		for(int i = 0; i < lista.size(); i++){
			retorno[i] = lista.get(i);
		}
		return retorno;
	}

	
	/**
	 * Constroi um mecanismo para ordenação por um atributo composto
	 * @param criteria - Criteria
	 * @param property - String que referencia uma propriedade Composta. 
	 * 			Ex. "cargo.departamento.departamentoPai.id" orderna pelo id do Departamento PAI.
	 */
	@SuppressWarnings("unchecked")
	private void criarOrdenacaoComposta(Criteria criteria, String property) throws Exception{
		List<String> array = Util.split(property, '.');
		Class lastObject = null;
		String lastAlias = null;
		//percorre o grafo de propriedades inserindo os relacionamentos
		for(int i = 0 ; i < array.size(); i ++){
			String propertyName = array.get(i);
			//verifica se a propriedade é um TO
			if(lastAlias == null){
				lastObject = PropertyUtils.getPropertyType(this.bean, propertyName);
			}else{
				TransferObject obj = (TransferObject)lastObject.newInstance();
				lastObject = PropertyUtils.getPropertyType(obj, propertyName);
			}
			//se for um TO continua a iteração, se não for insere a cláusula de ORDENAÇÃO com base nos relacionamentos
			if(lastObject != null && Util.isTO(lastObject)){
				//verifica se ja existe uma associação com o objeto
				String alias = getRelacionamento(propertyName);
				if(alias == null){
					//se o objeto não estiver relacionado insere o relacionamento
					String aliasRelacionamento = createAlias(propertyName);
					if(lastAlias != null && !lastAlias.equals("")){
						this.relacionamentos.add(new Relacionamento(lastAlias+"."+propertyName, aliasRelacionamento));
					}else{
						this.relacionamentos.add(new Relacionamento(propertyName, aliasRelacionamento));						
					}
					//armazena o alias do relacionamento para utilizar na próxima iteração
					lastAlias = aliasRelacionamento;
				}else{
					//se a associação ja existir então aproveita o mesmo ALIAS
					lastAlias = alias;
				}
			}else{
				if(lastAlias != null){
					String orderBy = lastAlias+"."+propertyName;
					if(getOrder() == OrderType.ASC){
						criteria.addOrder(Order.asc(orderBy).ignoreCase());
					}else{
						criteria.addOrder(Order.desc(orderBy).ignoreCase());						
					}
				}
				//ao encontrar a primeira propriedade != de TO o laço tem que ser finalizado
				break;
			}
			
		}
	}
	
	/**
	 * Verifica se a propriedade passada como parâmetro ja esta associada entre os relacionamentos do TO
	 * 
	 * @param propriedade - String
	 * @return boolean
	 */
	private String getRelacionamento(String propriedade){
		String alias = null;
		for(Relacionamento relacionamento : this.relacionamentos){
			if(relacionamento.getObjectAlias().equals(propriedade)){
				alias = relacionamento.getObjectAlias();
				break;
			}
		}
		return alias;
	}
	
	/**
	 * Gera um Alias com base na sugestão passada como parametro,
	 * se o alias passado como parâmetro já estiver sendo utilizado a engine retorna
	 * uma sugestão a ser utilizada para que não existão ALIAS duplicados
	 * @param aliasSugestao
	 * @return
	 */
	private String createAlias(String aliasSugestao){
		String aliasRetorno = null;
		int contador = 1;
		while(aliasRetorno == null){
			if(getRelacionamento(aliasSugestao) != null){
				contador++;
				aliasSugestao = aliasSugestao+contador;
			}else{
				aliasRetorno = aliasSugestao;
			}
		}
		return aliasRetorno;
	}
	
	/**
	 *	InnerClass que encapsula as propriedades de um determinado campo.
	 * 
	 * @author Ciro Macedo
	 * @version $Revision: 1.24 $, $Date: 2008/11/28 19:20:29 $
	 */
	protected class Property{
		
		private Object value;
		private Object type;
		private String caminho;
		private String caminhoCompleto;

		public Property(String caminhoCompleto, String caminho, Object value, Object type){
			this.caminho = caminho;
			this.caminhoCompleto = caminhoCompleto;
			this.value = value;
			this.type = type;
		}
		
		public Object getValue() {
			return value;
		}
		public void setValue(Object value) {
			this.value = value;
		}
		public String getCaminho() {
			return caminho;
		}
		public void setCaminho(String caminho) {
			this.caminho = caminho;
		}
		public Object getType() {
			return type;
		}
		public void setType(Object type) {
			this.type = type;
		}
		/**
		 * Retorna o valor armazenado na variável caminhoCompleto.
		 * @return String
		 */
		public String getCaminhoCompleto() {
			return caminhoCompleto;
		}
		/**
		 * Insere um valor ao atributo caminhoCompleto
		 * @param caminhoCompleto - String
		 * 
		 */
		public void setCaminhoCompleto(String caminhoCompleto) {
			this.caminhoCompleto = caminhoCompleto;
		}
	}

	/**
	 * 
	 * <b>Classe:</b> HqlDinamico.java <br>
	 * <b>Descrição:</b>     <br>
	 *
	 * <b>Projeto:</b> core <br>
	 * <b>Pacote:</b> core.hibernate <br>
	 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
	 * 
	 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
	 * 
	 * @author leonardop
	 * @version Revision: $ Date: $
	 */
	private class Relacionamento{
		private String objectName;
		private String objectAlias;
		
		/**
		 * Cria um novo objeto de relacionamento com o Nome do TO, e o nome do field
		 * @param objectName
		 * @param objectAlias
		 */
		public Relacionamento(String objectName, String objectAlias) {
			this.objectName = objectName;
			this.objectAlias = objectAlias;
		}
		
		public String getObjectName() {
			return objectName;
		}
		@SuppressWarnings("unused")
		public void setObjectName(String objectName) {
			this.objectName = objectName;
		}
		public String getObjectAlias() {
			return objectAlias;
		}
		@SuppressWarnings("unused")
		public void setObjectAlias(String objectAlias) {
			this.objectAlias = objectAlias;
		}
	}

	/**
	 * Retorna o valor armazenado na variável bean.
	 * @return TransferObject
	 */
	protected TransferObject getBean() {
		return bean;
	}

	/**
	 * Insere um valor ao atributo bean
	 * @param bean - TransferObject
	 */
	protected void setBean(TransferObject bean) {
		this.bean = bean;
	}

	/**
	 * Retorna o valor armazenado na variável session.
	 * @return Session
	 */
	protected Session getSession() {
		return session;
	}

	/**
	 * Insere um valor ao atributo session
	 * @param session - Session
	 */
	protected void setSession(Session session) {
		this.session = session;
	}
	
	/**
	 * Retorna o valor armazenado na variável query.
	 * @return Query
	 */
	protected Query getQuery() {
		//constroi o objeto QUERY
		createQuery();
		return query;
	}

	/**
	 * Insere um valor ao atributo query
	 * @param query - Query
	 */
	protected void setQuery(Query query) {
		this.query = query;
	}

	/**
	 * Retorna o valor armazenado na variável QueryFormat.java.
	 * @return Criteria
	 */
	public Criteria getCriteria() {
		//constroi o objeto CRITERIA
		createCriteria();
		return criteria;
	}

	/**
	 * Verifica se o objeto CRITERIA já possui uma associação mapeada para o caminho passado.
	 * 	Ex: se o Critéria referenciar um objeto DespachoDocumentoTO e a String passada como parâmetro for 
	 * "documento.autor" vai retornar o ALIAS se o InnerJoin já existir ou Null caso não existe.
	 * 
	 * @param caminhoObjeto - String
	 * @return String
	 */
	public String buscarJoin(String caminhoObjeto){
		String retorno = null;
		List<String> array = Util.split(caminhoObjeto, '.');
		for(String property : array){
			retorno = this.getRelacionamento(property);
			if(retorno == null){
				break;
			}
		}
		return retorno;
	}

	/**
	 * Verifica se o objeto CRITERIA já possui uma associação mapeada para o caminho passado.
	 * 	Ex: se o Critéria referenciar um objeto DespachoDocumentoTO e a String passada como parâmetro for 
	 * "documento.autor" vai retornar TRUE, se não retorna FALSE.
	 * 
	 * @param caminhoObjeto - String
	 * @return boolean
	 */
	public boolean existeJoin(String caminhoObjeto){
		String retorno = null;
		List<String> array = Util.split(caminhoObjeto, '.');
		for(String property : array){
			retorno = this.getRelacionamento(property);
			if(retorno == null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Insere um valor ao atributo QueryFormat.java
	 * @param criteria - Criteria
	 * 
	 */
	public void setCriteria(Criteria criteria) {
		this.criteria = criteria;
	}

	/**
	 * Retorna o valor armazenado na variável HqlFormat.java.
	 * @return String
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * Insere um valor ao atributo HqlFormat.java
	 * @param orderBy - String
	 * 
	 */
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * Indica se a busca sera realizada por LIKE ou EQUALS
	 * @return boolean
	 */
	public boolean getLike() {
		return like;
	}

	/**
	 * 
	 * @param like
	 */
	public void setLike(boolean like) {
		this.like = like;
	}

	/**
	 * Retorna o valor armazenado na variável HqlFormat.java.
	 * @return List<Relacionamento>
	 */
	public List<Relacionamento> getRelacionamentos() {
		return relacionamentos;
	}

	/**
	 * Insere um valor ao atributo HqlFormat.java
	 * @param relacionamentos - List<Relacionamento>
	 * 
	 */
	public void setRelacionamentos(List<Relacionamento> relacionamentos) {
		this.relacionamentos = relacionamentos;
	}

	/**
	 * Retorna o valor armazenado na variável order.
	 * @return int
	 */
	public OrderType getOrder() {
		return order;
	}

	/**
	 * Insere um valor ao atributo order
	 * @param order - int
	 * 
	 */
	public void setOrder(OrderType order) {
		this.order = order;
	}


	/**
	 * Retorna o valor armazenado na variável count.
	 * @return Criteria
	 */
	public Criteria getCount() {
		return count;
	}


	/**
	 * Insere um valor ao atributo count
	 * @param count - Criteria
	 * 
	 */
	public void setCount(Criteria count) {
		this.count = count;
	}

	/**
	 * Retorna o valor armazenado na variável listProperty.
	 * @return listProperty
	 */
	protected List<Property> getListProperty() {
		return listProperty;
	}
}
