package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> Sistema.java <br>
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
public interface Sistema {

	/**
	 * Persiste as alterações no objeto SistemaTO.
	 * 
	 * @param sistema - {@link SistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;
	
	/**
	 * Apaga as informações do objeto SistemaTO.
	 * 
	 * @param sistema - {@link SistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException,BDException;
	
	/**
	 * Persiste as informações do objeto SistemaTO.
	 * 
	 * @param sistema - {@link SistemaTO}
	 * @param usuarioLogado - {@link UsuarioTO}
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException;

	/**
	 * Consulta um objeto SistemaTO com base no ID do objeto.
	 * 
	 * @param sistema - {@link SistemaTO}
	 * @return {@link SistemaTO}
	 * @throws BDException
	 */
	public SistemaTO consultar(SistemaTO sistema) throws BDException;
	
	/**
	 * Retorna uma lista de objetos SistemaTO.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @return List<TransferObject>
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException;
	
	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param sistema - SistemaTO
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO buscarObjeto(SistemaTO sistema)throws BDException;
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException;
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(SistemaTO sistema) throws BDException;
	
	/**
	 * Retorna o sistema gerenciado com base no NOME - Constante definida no projeto.
	 * 
	 * @param nome - {@link String} 
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO consultarSistemaNome(String nome) throws BDException;
	
	/**
	 * Retorna o sistema com base no Contexto.
	 * @param contexto - {@link String} 
	 * @return SistemaTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public SistemaTO consultarSistemaContexto(String contexto) throws BDException;

}
