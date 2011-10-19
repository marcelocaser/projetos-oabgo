package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> Usuario.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public interface Usuario {

	/**
	 * Persiste as alterações no objeto UsuarioTO.
	 * 
	 * @param usuarioTO
	 *            - {@link UsuarioTO}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void alterar(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	/**
	 * Apaga as informações do objeto UsuarioTO.
	 * 
	 * @param usuarioTO
	 *            - {@link UsuarioTO}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	/**
	 * Lista os objetos UsuarioTO com paginação.
	 * 
	 * @param usuarioTO
	 *            - {@link UsuarioTO}
	 * @param info
	 *            - {@link PaginacaoInfo}
	 * @return {@link Paginacao}
	 * @throws BDException
	 */
	public void incluir(UsuarioTO usuarioTO, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

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
			PerfilAcessoTO perfilAcesso) throws BDException;

	/**
	 * Consulta um objeto UsuarioTO com base no ID do objeto.
	 * 
	 * @param usuarioTO
	 *            - {@link UsuarioTO}
	 * @return {@link UsuarioTO}
	 * @throws BDException
	 */
	public UsuarioTO consultar(UsuarioTO usuarioTO) throws BDException;

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
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException;

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
			throws BDException;

	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject.
	 *
	 * @throws BDException - 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar() throws BDException;

	/**
	 * Remove um objeto da sessão do Hibernate.
	 */
	public void retirarObjetoSessao(UsuarioTO usuario);

	/**
	 * Retorna um usuário com base no Login do mesmo.
	 * 
	 * @param userName
	 *            String
	 * @return {@link UsuarioTO}
	 * @throws BDException
	 */
	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException;

}
