package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import br.org.oabgo.sati.negocio.controle.entidade.AcessoSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.utilitario.BeanUtil;

/**
 * <b>Classe:</b> AcessoSistema.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio.interfaces <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public interface AcessoSistema {
	
	/**
	 * Express�o que programa a execu��o da tarefa de gerenciamento
	 * de acessoSistema todos os dias as 00:00 Hs.
	 */
	public final String		EXPRESSAO_CRON_TAREFA_ACESSO = "0 00 00 * * ?";

	/**
	 * Quantidade de dias que os acessoSistemas ser�o excluidos.	
	 */
	public final Integer	DIAS_ACESSO = 30;
	
	/**
	 * Persiste as altera��es no objeto AcessoSistemaTO.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws BDException
	 */
	public void alterar(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws BDException;
	
	/**
	 * Apaga as informa��es do objeto AcessoSistemaTO.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	/**
	 * Persiste as informa��es do objeto AcessoSistemaTO.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void incluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws  BDException;
	
	/**
	 * Consulta um objeto AcessoSistemaTO com base no ID do objeto.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @return {@link AcessoSistemaTO}
	 * @throws BDException
	 */
	public AcessoSistemaTO consultar(AcessoSistemaTO acessoSistema) throws BDException;
	
	/**
	 * Lista os objetos AcessoSistemaTO ordenadas por um determinada atributo.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws BDException
	 */
	public List<TransferObject> listar(AcessoSistemaTO acessoSistema, String orderBy) throws BDException;

	/**
	 * Retorna o primeiro objeto encontrado que satisfa�a os par�metros de busca.
	 * 
	 * @param acessoSistema - {@link AcessoSistemaTO}
	 * @return {@link AcessoSistemaTO}
	 * @throws BDException
	 */
	public AcessoSistemaTO buscarObjeto(AcessoSistemaTO AcessoSistemaTO) throws BDException;
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - AcessoSistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcessoSistemaTO AcessoSistemaTO) throws BDException;
	
	/**
	 * Exclui todos os acessoSistemas realizados em um per�odo menor ou igual ao
	 * da data informada.
	 * 
	 * @param dataLimite - Date
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluirAcessosAntigos(Date dataLimite)throws RegraNegocioException, BDException;
	
	/**
	 * Exclui todos os acessoSistema vinculados a usu�rio passado como parametro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluirAcessoUsuario(UsuarioTO usuario, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException;
	
	/**
	 * Retorna a quantidade de registros para encontrada para o objeto.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(AcessoSistemaTO acessoSistema) throws BDException;
	
	/**
	 * Retorna uma lista contendo a quantidade de usu�rios on-line
	 * nas aplica��es ger�nciadas pelo ADM.
	 * @return List<BeanUtil>
	 */
	public List<BeanUtil> gerarListaAcessoAtivo();
	
	/**
	 * Registra o acessoSistema de um determinado usu�rio em aplica��o gerenciada pelo Controle de Acesso.
	 *
	 * @param usuario - {@link UsuarioTO}
	 * @param request - {@link HttpServletRequest}
	 * @param usuarioAdministrador - {@link UsuarioTO}
	 * @throws BDException 
	 * @return AcessoSistemaTO
	 */
	public AcessoSistemaTO registrarAcesso(UsuarioTO usuario, HttpServletRequest request, UsuarioTO usuarioAdministrador) throws RegraNegocioException, BDException;
	
	/**
	 * Desabilita o acessoSistema dos usu�rios �s aplica��es gerenciadas pelo ADM.
	 *
	 * @param usuario - {@link UsuarioTO}
	 * @throws BDException 
	 * @return void
	 */
	public void desativarAcessoUsuario(UsuarioTO usuario)throws BDException;
	
	/**
	 * Desativa o acessoSistema do usu�rio �s aplica��es gerenciadas pelo ADM.
	 * 
	 * @param usuario - {@link UsuarioTO}
	 * @param idSessao - {@link String}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void desativarAcessoUsuario(UsuarioTO usuario, String sessionId)throws BDException;
}
