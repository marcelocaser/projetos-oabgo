package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.MenuSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.paginacao.Paginacao;
import core.paginacao.PaginacaoInfo;

/**
 * <b>Classe:</b> menuSistemaPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service("menuSistemaPO")
public class MenuSistemaPO extends SATIPersistencia {
	

	public void alterar(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(menuSistema, usuarioLogado);
	}
	
	public void excluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(menuSistema, usuarioLogado);
	}
	
	public void incluir(MenuSistemaTO menuSistema, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(menuSistema, usuarioLogado);
	}
	
	public MenuSistemaTO consultar(MenuSistemaTO menuSistema) throws BDException{
		return (MenuSistemaTO) this.getDaoHibernate().consultar(MenuSistemaTO.class, menuSistema);
	}
	
	public void retirarObjectoSessao(MenuSistemaTO menuSistema)throws BDException{
		getDaoHibernate().retirarObjetoSessao(menuSistema);
	}
	
	/**
	 * Busca todos os menus associados a um perfil.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param sistema - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarMenuPerfilAcesso(PerfilAcessoTO perfilAcesso, String sistema) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(MenuSistemaTO.class);
		
		//insere a associação com a PERFIL
		criteria.createCriteria("listaPerfilAcesso", "PERFIL", Criteria.INNER_JOIN);
		criteria.createCriteria("tipoMenuSistema", "TIPO_MENU", Criteria.INNER_JOIN);
		criteria.createCriteria("TIPO_MENU.sistema", "SISTEMA", Criteria.INNER_JOIN);
		
		//busca todos os menus associados ao PERFIL pertencentes ao Sistema
		criteria.add((Criterion)Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		criteria.add((Criterion)Restrictions.eq("SISTEMA.vchNome", sistema));
		
		criteria.addOrder(Order.asc("intHierarquia"));
		return criteria.list();
	}
	
	/**
	 * Verifica se um determinado perfil de acesso pode visualizar um MENU.
	 *
	 * @param perfil - {@link PerfilAcessoTO}
	 * @param menu - {@link MenuTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public boolean possuiAcessoMenu(PerfilAcessoTO perfil, MenuSistemaTO menu) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(MenuSistemaTO.class);
		//insere a associação
		criteria.createAlias("listaPerfilAcesso", "PERFIL");
		//insere as cláusulas de restrição
		criteria.add(Restrictions.eq("PERFIL.id", perfil.getId()));
		criteria.add(Restrictions.eq("id", menu.getId()));
		return !criteria.list().isEmpty() ? true : false;
	}

	/**
	 * Retorna a lista de menu de primeiro nível associado ao perfil de acesso.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param sistema - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarMenuPerfilAcessoBase(PerfilAcessoTO perfilAcesso, String sistema) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(MenuSistemaTO.class);
		
		//insere a associação com a PERFIL
		criteria.createCriteria("listaPerfilAcesso", "PERFIL", Criteria.INNER_JOIN);
		criteria.createCriteria("tipoMenuSistema", "TIPO_MENU", Criteria.INNER_JOIN);
		criteria.createCriteria("TIPO_MENU.sistema", "SISTEMA", Criteria.INNER_JOIN);
		
		//busca todos os menus associados ao PERFIL pertencentes ao Sistema
		criteria.add((Criterion)Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		criteria.add((Criterion)Restrictions.eq("SISTEMA.vchNome", sistema));
		criteria.add((Criterion)Restrictions.ne("bitAtivo", Boolean.FALSE));
		//criteria.add((Criterion)Restrictions.eq("agrupador", Boolean.TRUE));
		criteria.add((Criterion)Restrictions.isNull("menuPai"));
		//insere a ordenação
		criteria.addOrder(Order.asc("intHierarquia"));
		return criteria.list();
	}

	/**
	 * Retorna a lista de menu de primeiro nível associado ao perfil de acesso.
	 * 
	 * @param perfilAcesso - PerfilAcessoTO
	 * @param sistema - SistemaTO
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarMenuPerfilAcessoBase(PerfilAcessoTO perfilAcesso, SistemaTO sistema) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(MenuSistemaTO.class);
		
		//insere a associação com a PERFIL
		criteria.createCriteria("listaPerfilAcesso", "PERFIL", Criteria.INNER_JOIN);
		criteria.createCriteria("tipoMenuSistema", "TIPO_MENU", Criteria.INNER_JOIN);
		criteria.createCriteria("TIPO_MENU.sistema", "SISTEMA", Criteria.INNER_JOIN);
		
		//busca todos os menus associados ao PERFIL pertencentes ao Sistema
		criteria.add((Criterion)Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		criteria.add((Criterion)Restrictions.eq("SISTEMA.id", sistema.getId()));
		criteria.add((Criterion)Restrictions.ne("bitAtivo", Boolean.FALSE));
		//criteria.add((Criterion)Restrictions.eq("agrupador", Boolean.TRUE));
		criteria.add((Criterion)Restrictions.isNull("menuPai"));
		//insere a ordenação
		criteria.addOrder(Order.asc("intHierarquia"));
		return criteria.list();
	}

	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como parâmetro.
	 * 
	 * @param transferObject - TransferObject 
	 * @param info - PaginacaoInfo
	 * @return Paginacao
	 * @throws RegraNegocioException
	 */
	public Paginacao listar(MenuSistemaTO menu, PaginacaoInfo info) throws BDException{
		return this.getDaoHibernate().listar(menu, info);
	}
	
	public List<TransferObject> listar(MenuSistemaTO menuSistema) throws BDException{
		return this.getDaoHibernate().listar(menuSistema);
	}
	
	public List<TransferObject> listar(MenuSistemaTO menuSistema, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(menuSistema, orderBy);
	}
	
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Sistema";
	}

	protected String getNomeOperacaoExclusao() {
		return "Excluir Sistema";
	}

	protected String getNomeOperacaoInclusao() {
		return "Incluir Sistema";
	}

}
