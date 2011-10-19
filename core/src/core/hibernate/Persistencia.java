package core.hibernate;

import org.springframework.beans.factory.annotation.Autowired;

import core.dao.Dao;
import core.dao.TransferObject;
import core.excecoes.ApplicationException;
import core.excecoes.BDException;



/**
 * <b>Titulo:</b> Persistencia.java <br>
 * <b>Descricao:</b>     <br>
 * <b>Projeto:</b> core <br>
 * 
 * @author Leonardo Peixoto
 */
public class Persistencia {
	
	//injeta a interface que provê os métodos de iteração com o BD.
	@Autowired(required=true)
	private Dao daoHibernate;
	
	public Persistencia() {}
	
	public Dao getDaoHibernate() {
		return daoHibernate;
	}
	
	public void setDaoHibernate(Dao daoHibernate) {
		this.daoHibernate = daoHibernate;
	}
	
	public void sincronizar() throws BDException{
		this.getDaoHibernate().flush();
	}
	
	public void retirarObjetoSessao(TransferObject bean) throws ApplicationException{
		this.getDaoHibernate().retirarObjetoSessao(bean);
	}
	
	/**
	 * Verifica se existe um TO com as mesmas informações do TO passado como parâmetro.
	 * @param bean - TransferObject
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeInsert(TransferObject bean) throws BDException, ApplicationException{
		return this.getDaoHibernate().beforeInsert(bean);
	}

	/**
	 * Verifica se existe um TO com as mesmas informações do TO passado como parâmetro.
	 * @param bean - TransferObject - TO que será persistido
	 * @param beanTmp - TransferObject - TO com as propriedades a serem comparadas
	 * @return boolean
	 * @throws BDException
	 */
	public boolean beforeUpdate(TransferObject bean, TransferObject beanTmp) throws BDException, ApplicationException{
		return this.getDaoHibernate().beforeUpdate(bean, beanTmp);
	}
}
