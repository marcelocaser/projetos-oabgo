package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;
import java.util.Set;

import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> PerfilAcesso.java <br>
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
public interface PerfilAcesso {

	/**
	 * Persiste as alterações em PerfilAcessoTO.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void alterar(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	/**
	 * Exclui o objeto PerfilAcessoTO passado como parâmetro.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException ;
	
	/**
	 * Persiste as informações do objeto PerfilAcessoTO passado com parâmetro.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void incluir(PerfilAcessoTO perfilAcesso, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;
	
	/**
	 * Consulta um obejto PerfilAcessoTO com base no atributo ID.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @return  {@link PerfilAcessoTO}
	 * @throws BDException
	 */
	public PerfilAcessoTO consultar(PerfilAcessoTO perfilAcesso) throws BDException;
	
	/**
	 * associa os perfis de acesso a ação.
	 */
	public void associarPerfilAcao(List<String> listaPerfil, AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws Exception;
	
	/**
	 * associa os perfis de acesso ao MenuTO.
	 */
	public void associarPerfilMenu(List<String> listaPerfil, MenuSistemaTO menuSistema,UsuarioTO usuarioLogado) throws Exception;
	
	/**
	 * associa os perfis de acesso a PaginaTO.
	 */
	public void associarPerfilPagina(List<String> listaPerfil, PaginaTO pagina,UsuarioTO usuarioLogado) throws Exception;
	
	/**
	 * 
	 * Recebe uma List<String> com os Id's dos perfis e retorna uma
	 * Set<PerfilAcessoTO> com os PerfilAcessoTO sincronizados.
	 * 
	 * @return Set<PerfilAcessoTO>
	 */
	public Set<PerfilAcessoTO> pegarListaPerfil(List<String> listaPerfil);
	
	/**
	 * Lista os objetos PerfilAcessoTO ordenados por um atributo determinado.
	 * 
	 * @param perfil - {@link PerfilAcessoTO}
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws BDException
	 */
	public List<TransferObject> listar(PerfilAcessoTO perfil, String orderBy) throws BDException;
	
	/**
	 * Remove um objeto da sessão do Hibernate.
	 */
	public void retirarObjetoSessao(PerfilAcessoTO perfilAcesso);
	
}
