package br.org.oabgo.siged.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;

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
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	

	/**
	 * Persiste as alterações no objeto UsuarioTO.
	 *
	 * @param usuario
	 * @param usuarioLogado
	 * @param confirmaSenha
	 * @param senhaAtual
	 * @throws RegraNegocioException
	 * @throws BDException - 
	 * @return void
	 */
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado, String confirmaSenha, String senhaAtual)
			throws RegraNegocioException, BDException;
	
	/**
	 * Apaga as informações do objeto UsuarioTO.
	 * 
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @param usuarioLogado
	 *            - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	
	/**
	 * Inclui as informações contidas no objeto passado como parâmetro.
	 * 
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @param info
	 *            - {@link PaginacaoInfo}
	 * @return {@link Paginacao}
	 * @throws BDException
	 */
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;
	
	/**
	 * VInclui as informações contidas no objeto passado como parâmetro.
	 *
	 * @param usuario
	 * @param senhaAtual
	 * @param confirmaSenha
	 * @throws RegraNegocioException
	 * @throws BDException - 
	 * @return void
	 */
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado, String confirmaSenha)
			throws RegraNegocioException, BDException;

	/**
	 * Consulta um objeto UsuarioTO com base no ID do objeto.
	 * 
	 * @param usuario
	 *            - {@link UsuarioTO}
	 * @return {@link UsuarioTO}
	 * @throws BDException
	 */
	public UsuarioTO consultar(UsuarioTO usuario) throws BDException;
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException;
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException;
	
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
	
	/**
	 * Verifica se a senha informada é válida.
	 *
	 * @param userName - String
	 * @param senha - String
	 * @return boolean
	 */
	public boolean isSenhaValida(String userName, String senha)throws RegraNegocioException;

}
