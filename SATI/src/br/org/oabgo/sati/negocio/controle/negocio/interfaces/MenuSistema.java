package br.org.oabgo.sati.negocio.controle.negocio.interfaces;

import java.util.List;

import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;

public interface MenuSistema {

	public void alterar(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public void excluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public void incluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException;

	public MenuSistemaTO consultar(MenuSistemaTO menuSistema)
			throws BDException;

	/**
	 * Lista todos os menus associados a um perfil e pertencentes ao Sistema
	 * passados como parâmetro.
	 * 
	 * @param perfilAcesso
	 *            - PerfilAcessoTO
	 * @param sistema
	 *            - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listarMenuPerfilAcesso(
			PerfilAcessoTO perfilAcesso, String sistema) throws BDException;

	/**
	 * Verifica se o perfil de acesso pode visualizar o menu Passado como
	 * parâmetro.
	 * 
	 * @param perfil
	 * @param menu
	 * @throws BDException
	 * @return boolean
	 */
	public boolean possuiAcessoMenu(PerfilAcessoTO perfil, MenuSistemaTO menu)
			throws BDException;

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
			PerfilAcessoTO perfilAcesso, String sistema) throws BDException;

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
			PerfilAcessoTO perfilAcesso, SistemaTO sistema) throws BDException;

	/**
	 * Lista todos os objetos MenuTO com paginação.
	 * 
	 * @param menu
	 *            - {@link MenuTO}
	 * @param info
	 *            - {@link ListaPaginadaInfo}
	 * @return {@link ListaPaginada}
	 * @throws BDException
	 */
	public Paginacao listar(MenuSistemaTO menu, PaginacaoInfo info)
			throws BDException;
	
	public List<TransferObject> listar(MenuSistemaTO menuSistema)
			throws BDException;

	public List<TransferObject> listar(MenuSistemaTO menuSistema, String orderBy)
			throws BDException;

	public void retirarObjetoSessao(MenuSistemaTO menuSistema);

}
