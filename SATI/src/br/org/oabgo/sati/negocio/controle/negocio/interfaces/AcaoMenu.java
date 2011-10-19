package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> AcaoMenu.java <br>
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
public interface AcaoMenu {

	/**
	 * Persiste as alterações no objeto AcaoMenuTO.
	 * 
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void alterar(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;
	
	/**
	 * Apaga as informações do objeto AcaoMenuTO.
	 * 
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	/**
	 * Persiste as informações do objeto AcaoMenuTO.
	 * 
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void incluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	/**
	 * Consulta um objeto AcaoMenuTO com base no ID do objeto.
	 * 
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @return {@link AcaoMenuTO}
	 * @throws BDException
	 */
	public AcaoMenuTO consultar(AcaoMenuTO acaoMenu) throws BDException;
	
	/**
	 * Retorna uma lista de objetos AcaoMenuTO.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(AcaoMenuTO acaoMenu, String orderBy)throws BDException;
	
	/**
	 * Verifica se o perfil de acesso tem permissão para executar a ação.
	 *
	 * @param perfil - {@link PerfilAcessoTO}
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @return {@link Boolean}
	 * @throws BDException
	 */
	public boolean possuiAcessoAcao(PerfilAcessoTO perfil, AcaoMenuTO acaoMenu) throws BDException;
	
	/**
	 * Retorna a ultima posição valida para um menu de ação agrupador.
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public Integer buscarUltimaPosicaoAcaoAgrupador(AcaoMenuTO acaoMenu) throws BDException;

	/**
	 * Lista as ações base associadas ao sistema.
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param sistema - {@link String}
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, String sistema, String pagina)throws BDException;

	/**
	 * Lista todas as ações de um determinado usuário.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param objetoTO - String
	 * @param target - String
	 * @param sistema - SistemaTO
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, SistemaTO sistema, String pagina)throws BDException;
	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @return AcaoMenuTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcaoMenuTO buscarObjeto(AcaoMenuTO acaoMenu)throws BDException;
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcaoMenuTO acaoMenu) throws BDException;
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void inicializarObjeto(AcaoMenuTO acaoMenu) throws BDException;
	
	/**
	 * Retorna o acaoMenu gerenciado com base no NOME - Constante definida no projeto.
	 * 
	 * @param nome - {@link String} 
	 * @return AcaoMenuTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcaoMenuTO consultarAcaoMenuNome(String nome) throws BDException;

}
