package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> Pagina.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public interface Pagina {

	/**
	 * Persiste as alterações no objeto PaginaTO.
	 * 
	 * @param pagina - {@link PaginaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void alterar(PaginaTO pagina, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;
	
	/**
	 * Apaga as informações do objeto PaginaTO.
	 * 
	 * @param pagina - {@link PaginaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(PaginaTO pagina, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	/**
	 * Persiste as informações do objeto PaginaTO.
	 * 
	 * @param pagina - {@link PaginaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void incluir(PaginaTO pagina, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	/**
	 * Consulta um objeto PaginaTO com base no ID do objeto.
	 * 
	 * @param pagina - {@link PaginaTO}
	 * @return {@link PaginaTO}
	 * @throws BDException
	 */
	public PaginaTO consultar(PaginaTO pagina) throws BDException;
	
	/**
	 * Verifica se o perfil de acesso passado como parâmetro tem permissão para visualizar a página requisitada.
	 *
	 * @param pagina - {@link PaginaTO}
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public boolean possuiAcessoPagina(PaginaTO pagina, PerfilAcessoTO perfilAcesso) throws BDException;
	
	/**
	 * Retorna uma lista de objetos PaginaTO.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(PaginaTO pagina, String orderBy)throws BDException;
	
	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param pagina - PaginaTO
	 * @return PaginaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public PaginaTO buscarObjeto(PaginaTO pagina)throws BDException;
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(PaginaTO pagina) throws BDException;
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(PaginaTO pagina) throws BDException;
	
	/**
	 * Retorna o pagina gerenciado com base no NOME - Constante definida no projeto.
	 * 
	 * @param nome - {@link String} 
	 * @return PaginaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public PaginaTO consultarPaginaNome(String nome) throws BDException;

}
