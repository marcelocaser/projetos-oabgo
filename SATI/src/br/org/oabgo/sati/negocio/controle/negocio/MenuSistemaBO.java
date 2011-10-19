package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.MenuSistema;
import br.org.oabgo.sati.negocio.controle.persistencia.MenuSistemaPO;

/**
 * <b>Classe:</b> MenuSistemaBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class MenuSistemaBO implements MenuSistema {

	@Autowired
	private MenuSistemaPO persistencia;

	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(menuSistema);
		this.persistencia.alterar(menuSistema, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(menuSistema, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(menuSistema);
		this.persistencia.incluir(menuSistema, usuarioLogado);
	}

	public MenuSistemaTO consultar(MenuSistemaTO menuSistema) throws BDException {
		return this.persistencia.consultar(menuSistema);
	}
	
	/**
	 * Lista todos os menus associados a um perfil e pertencentes ao Sistema
	 * passados como parâmetro.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param sistema - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listarMenuPerfilAcesso(
			PerfilAcessoTO perfilAcesso, String sistema) throws BDException {
		return this.persistencia.listarMenuPerfilAcesso(perfilAcesso, sistema);
	}

	/**
	 * Verifica se o perfil de acesso pode visualizar o menu Passado como
	 * parâmetro
	 * 
	 * @param perfil
	 * @param menu
	 * @throws BDException
	 * @return boolean
	 */
	public boolean possuiAcessoMenu(PerfilAcessoTO perfil, MenuSistemaTO menu)
			throws BDException {
		return this.persistencia.possuiAcessoMenu(perfil, menu);
	}

	/**
	 * Retorna a lista de MENU base que o perfil de acesso pode visualizar.
	 * 
	 * @param perfilAcesso
	 * @param sistema
	 * @return
	 * @throws BDException
	 *             -
	 * @return List<TransferObject>
	 */

	public List<TransferObject> listarMenuPerfilAcessoBase(
			PerfilAcessoTO perfilAcesso, String sistema) throws BDException {
		return this.persistencia.listarMenuPerfilAcessoBase(perfilAcesso,
				sistema);
	}

	/**
	 * Retorna a lista de MENU base que o perfil de acesso pode visualizar.
	 * 
	 * @param perfilAcesso
	 * @param sistema
	 * @return
	 * @throws BDException
	 *             -
	 * @return List<TransferObject>
	 */

	public List<TransferObject> listarMenuPerfilAcessoBase(
			PerfilAcessoTO perfilAcesso, SistemaTO sistema) throws BDException {
		return this.persistencia.listarMenuPerfilAcessoBase(perfilAcesso,
				sistema);
	}
	
	public List<TransferObject> listar(MenuSistemaTO menuSistema) throws BDException {
		return this.persistencia.listar(menuSistema);
	}
	
	/**
	 * Lista os objetos com paginação.
	 * 
	 * @param menu - MenuTO
	 * @param info - ListaPaginadaInfo
	 * @return ListaPaginada
	 * @throws BDException
	 */
	public Paginacao listar(MenuSistemaTO menu, PaginacaoInfo info) throws BDException {
		return this.persistencia.listar(menu, info);
	}
	
	public List<TransferObject> listar(MenuSistemaTO menuSistema, String orderBy)
			throws BDException {
		return this.persistencia.listar(menuSistema, orderBy);
	}

	public void retirarObjetoSessao(MenuSistemaTO menuSistema) {
		this.persistencia.retirarObjetoSessao(menuSistema);
	}

}
