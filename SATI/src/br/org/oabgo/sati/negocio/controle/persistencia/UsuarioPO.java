package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> UsuarioPO.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service("usuarioPO")
public class UsuarioPO extends SATIPersistencia {

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
	 * Retorna o perfil de acesso do usuário.
	 * 
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @param perfilAcesso
	 *            - {@link PerfilAcessoTO}
	 * @return PerfilAcessoTO
	 */
	public PerfilAcessoTO retornaPerfilAcesso(UsuarioTO usuario,
			PerfilAcessoTO perfilAcesso) throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(
				PerfilAcessoTO.class);

		// insere as associações que serão utilizadas
		criteria.createCriteria("listaUsuario", "USUARIO");

		criteria.add(Restrictions.eq("id", perfilAcesso.getId()));
		criteria.add(Restrictions.eq("USUARIO.id", usuario.getId()));

		return (PerfilAcessoTO) criteria.uniqueResult();
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
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @param orderBy
	 *            - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException {
		return this.getDaoHibernate().listar(usuario);
	}

	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @param orderBy
	 *            - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy)
			throws BDException {
		return this.getDaoHibernate().listar(usuario, orderBy);
	}

	/**
	 * Retorna a lista de usuários cadastrados.
	 * 
	 * @return
	 * @throws BDException
	 *             -
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar() throws BDException {
		Criteria criteria = this.getDaoHibernate().createCriteria(
				UsuarioTO.class);
		criteria.add(Restrictions.eq("bitUsuarioSIGED", Boolean.FALSE));
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
	 * Retorna o nome da operações de alteração.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Usuario";
	}

	/**
	 * Retorna o nome da operações de exclusão.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Usuario";
	}

	/**
	 * Retorna o nome da operações de inclusão.
	 * 
	 * @return String
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Usuario";
	}
	
}
