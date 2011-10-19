package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AcaoMenu;
import br.org.oabgo.sati.negocio.controle.persistencia.AcaoMenuPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> AcaoMenuBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service
public class AcaoMenuBO implements AcaoMenu {

	private static final long serialVersionUID = -6137413362653990464L;
	
	@Autowired
	private AcaoMenuPO	persistencia;

	/**
	 * Persiste as alterações no objeto passado como parâmetro.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void alterar(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado)	throws RegraNegocioException, BDException {
		this.persistencia.alterar(acaoMenu, usuarioLogado);
	}
	
	/**
	 * Exclui um objeto AcaoMenuTO.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void excluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.excluir(acaoMenu, usuarioLogado);
	}
	
	/**
	 * Persiste as informações no objeto passado como parâmetro.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void incluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado)throws RegraNegocioException, BDException {
		this.persistencia.incluir(acaoMenu, usuarioLogado);
	}

	/**
	 * Consulta um objeto AcaoMenuTO com base no ID.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @return AcaoMenuTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcaoMenuTO consultar(AcaoMenuTO acaoMenu)throws BDException {
		return this.persistencia.consultar(acaoMenu);
	}
	
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
	public List<TransferObject> listar(AcaoMenuTO acaoMenu, String orderBy)throws BDException {
		return this.persistencia.listar(acaoMenu, orderBy);
	}
	
	/**
	 * Verifica se o perfil de acesso tem permissão para executar a ação.
	 *
	 * @param perfil - {@link PerfilAcessoTO}
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @return {@link Boolean}
	 * @throws BDException
	 */
	public boolean possuiAcessoAcao(PerfilAcessoTO perfil, AcaoMenuTO acaoMenu) throws BDException{
		return this.persistencia.possuiAcessoAcao(perfil, acaoMenu);
	}
	
	/**
	 * Retorna a ultima posição valida para um menu de ação agrupador.
	 * @param acaoMenu - {@link AcaoMenuTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public Integer buscarUltimaPosicaoAcaoAgrupador(AcaoMenuTO acaoMenu) throws BDException{
		return this.persistencia.buscarUltimaPosicaoAcaoAgrupador(acaoMenu);
	}

	/**
	 * Lista as ações base associadas ao sistema.
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param sistema - {@link String}
	 * @return List<TransferObject>
	 */
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, String sistema, String pagina)throws BDException{
		return this.persistencia.listarAcaoUsuarioBase(perfilAcesso, objetoTO, faseObjeto, sistema, pagina);
	}

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
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, SistemaTO sistema, String pagina)throws BDException{
		return this.persistencia.listarAcaoUsuarioBase(perfilAcesso, objetoTO, faseObjeto, sistema, pagina);
	}
	
	/**
	 * Retorna o primeiro objeto que satisfaça os parâmetros de busca.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @return AcaoMenuTO
	 * 
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public AcaoMenuTO buscarObjeto(AcaoMenuTO acaoMenu)throws BDException {
		return this.persistencia.buscarObjeto(acaoMenu);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcaoMenuTO acaoMenu) throws BDException{
		this.persistencia.retirarObjetoSessao(acaoMenu);
	}

	/**
	 * Inicializa as propriedades de um obejto.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void inicializarObjeto(AcaoMenuTO acaoMenu) throws BDException{
		this.persistencia.inicializarObjeto(acaoMenu);
	}

	/**
	 * Retorna o acaoMenu com base no NOME - Constante definida no projeto.
	 * @param nome - {@link String} 
	 * @return AcaoMenuTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
	public AcaoMenuTO consultarAcaoMenuNome(String nome) throws BDException {
		return this.persistencia.consultarAcaoMenuNome(nome);
	}

}
