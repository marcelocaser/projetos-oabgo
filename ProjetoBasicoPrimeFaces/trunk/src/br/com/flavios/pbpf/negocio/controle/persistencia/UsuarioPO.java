package br.com.flavios.pbpf.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.com.flavios.pbpf.negocio.controle.PBPFPersistencia;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service("usuarioPO")
public class UsuarioPO extends PBPFPersistencia {

	/**
	 * Altera um objeto UsuarioTO.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.alterar(usuario, usuarioLogado);
	}
	
	
	/**
	 * Exclui um objeto UsuarioTO.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.excluir(usuario, usuarioLogado);
	}
	
	/**
	 * Inclui um objeto UsuarioTO.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws BDException {
		super.incluir(usuario, usuarioLogado);
	}

	/**
	 * Consulta um objeto UsuarioTO.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @return UsuarioTO
	 * @throws BDException
	 */
	public UsuarioTO consultar(UsuarioTO usuario) throws BDException {
		return (UsuarioTO) this.getDaoHibernate().consultar(UsuarioTO.class,
				usuario);
	}
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException{
		return this.getDaoHibernate().listar(usuario);
	}
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException{
		return this.getDaoHibernate().listar(usuario, orderBy);
	}
	
	/**
	 * Retorna a lista de usuários cadastrados.
	 *
	 * @return
	 * @throws BDException - 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar() throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(
				UsuarioTO.class);
		criteria.addOrder(Order.asc("vchLogin"));
		return (List<TransferObject>) this.getDaoHibernate().executar(criteria);
	}

	/**
	 * Retorna um usuário com base no Login do mesmo.
	 * 
	 * @param userName
	 *            String
	 * @return {@link UsuarioTO}
	 * @throws BDException
	 */
	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(
				UsuarioTO.class);
		criteria.add(Restrictions.eq("bitAtivo", Boolean.TRUE));
		criteria.add(Restrictions.eq("vchLogin", userName));
		return (UsuarioTO) this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	/**
	 * Retorna o nome da operação de alteração.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Usuario";
	}

	/**
	 * Retorna o nome da operação de exclusão.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Usuario";
	}

	/**
	 * Retorna o nome da operação de inclusão.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Usuario";
	}
}
