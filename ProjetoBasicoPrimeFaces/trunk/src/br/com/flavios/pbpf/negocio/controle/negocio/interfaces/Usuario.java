package br.com.flavios.pbpf.negocio.controle.negocio.interfaces;

import java.util.List;

import br.com.flavios.pbpf.negocio.controle.entidade.FilialTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;

public interface Usuario {

	/**
	 * Persiste as altera��es no objeto UsuarioTO.
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
	 * Persiste as altera��es no objeto UsuarioTO.
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
	 * Apaga as informa��es do objeto UsuarioTO.
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
	 * Inclui as informa��es contidas no objeto passado como par�metro.
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
	 * Inclui as informa��es contidas no objeto passado como par�metro.
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
	 * TransferObject passado como par�metro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException;
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como par�metro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException;
	
	/**
	 * Retorna a lista de usu�rios cadastrados.
	 *
	 * @return
	 * @throws BDException - 
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listar() throws BDException;
	
	/**
	 *  Retorna objeto UsuarioTO cadastrado e ativo.
	 *
	 * @param usuario
	 * @return
	 * @throws BDException - 
	 * @return List<TransferObject>
	 */
	public UsuarioTO buscarUsuarioAtivo(UsuarioTO usuario) throws BDException;
	
	/**
	 * Retorna objeto UsuarioTO cadastrado e vendedor.
	 *
	 * @param usuario
	 * @return
	 * @throws BDException - 
	 * @return UsuarioTO
	 */
	public UsuarioTO buscarUsuarioVendedor(UsuarioTO usuario, FilialTO filial) throws BDException, RegraNegocioException;
	
	/**
	 * Remove um objeto da sess�o do Hibernate.
	 */
	public void retirarObjetoSessao(UsuarioTO usuario);

}
