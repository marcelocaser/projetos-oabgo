package br.com.flavios.pbpf.negocio.controle;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.Criteria;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.hibernate.Persistencia;

public abstract class PBPFPersistencia extends Persistencia {

	/**
	 * Persiste as informações do objeto.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws BDException
	 */
	protected void incluir(TransferObject bean, UsuarioTO usuarioLogado)
			throws BDException {
		// inclui o TransferObject para depois gerar o log
		this.getDaoHibernate().incluir(bean);
	}

	/**
	 * Persiste as alterações no objeto.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws BDException
	 */
	protected void alterar(TransferObject bean, UsuarioTO usuarioLogado)
			throws BDException {
		// altera o TransferObject
		this.getDaoHibernate().alterar(bean);
	}

	/**
	 * Persiste as alterações no objeto.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws BDException
	 */
	protected void saveOrUpdate(TransferObject bean, UsuarioTO usuarioLogado)
			throws BDException {
		// altera o TransferObject
		this.getDaoHibernate().saveOrUpdate(bean);
	}

	/**
	 * Persiste as alterações no objeto.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @param transictionId
	 *            - {@link String} - Transiï¿½ï¿½o alvo para o BPM.
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws BDException
	 */
	protected void alterar(TransferObject bean, String transictionId,
			UsuarioTO usuarioLogado) throws BDException {
		// altera o TransferObject
		this.getDaoHibernate().alterar(bean);
	}

	/**
	 * Exclui um objeto.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws BDException
	 */
	protected void excluir(TransferObject bean, UsuarioTO usuarioLogado)
			throws BDException {
		// exclui o TransferObject
		this.getDaoHibernate().excluir(bean);
	}

	/**
	 * Exclui todos os obejtos da lista.
	 * 
	 * @param bean
	 *            - TransferObject
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws BDException
	 */
	protected void excluirLista(Collection<? extends TransferObject> lista,
			UsuarioTO usuarioLogado) throws BDException {
		// exclui a lista de entidades
		this.getDaoHibernate().excluir(new ArrayList<TransferObject>(lista));
	}

	/**
	 * Consulta um objeto TransferObject.
	 * 
	 * @param bean
	 *            - {@link TransferObject}
	 * @return {@link TransferObject}
	 */
	protected TransferObject consultar(TransferObject bean) {
		return this.getDaoHibernate().consultar(bean.getClass(), bean);
	}
	
	/**
	 * Inicializa as propriedades do TransferObject passado como parâmetro.
	 *
	 * @param bean - {@link Object}
	 * @throws BDException
	 * @return void
	 */
	protected void inicializar(Object bean) throws BDException{
		this.getDaoHibernate().inicializar(bean);
	}
	
	/**
	 * Retorna o primeiro {@link TransferObject} encontrado.
	 * 
	 * @param sHql
	 *            - String
	 * @return {@link TransferObject}
	 */
	protected TransferObject buscarObjeto(String sHql) {
		return (TransferObject) this.getDaoHibernate().buscarObjeto(sHql);
	}
	
	/**
	 * Retorna o primeiro {@link TransferObject} encontrado.
	 * @param bean - {@link TransferObject}
	 * @return {@link TransferObject}
	 */
	protected TransferObject buscarObjeto(TransferObject bean){
		return (TransferObject) this.getDaoHibernate().buscarObjeto(bean);
	}
	
	/**
	 * Retorna a quantidade de registros com base no TransferObject.
	 * 
	 * @param bean - TransferObject
	 * @throws BDException
	 * @return Integer
	 */
	protected Integer count(Criteria criteria, String id) throws BDException {
		return this.getDaoHibernate().count(criteria, id);
	}
	
	/**
	 * Retorna a quantidade de registros com base no TransferObject.
	 *
	 * @param bean - TransferObject
	 * @throws BDException 
	 * @return Integer
	 */
	protected Integer count(TransferObject bean) throws BDException{
		return this.getDaoHibernate().count(bean);
	}
	
	// Metodos implementados nas classes de persistencia para
	// retornar o nome da operacao a ser executada
	protected abstract String getNomeOperacaoInclusao();

	protected abstract String getNomeOperacaoAlteracao();

	protected abstract String getNomeOperacaoExclusao();
}
