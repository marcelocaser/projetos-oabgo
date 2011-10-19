package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.AcaoMenuTO;
import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> AcaoMenuPO.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service("acaoMenuPO")
public class AcaoMenuPO extends SATIPersistencia {

	/**
	 * Persiste as informa��es contidas no objeto passado como par�metro.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(acaoMenu, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como referencia.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(acaoMenu, usuarioLogado);
	}
	
	/**
	 * Inclui as informa��es contidas no objeto.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(AcaoMenuTO acaoMenu, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(acaoMenu, usuarioLogado);
	}
	
	/**
	 * Consulta um objeto.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @return AcaoMenuTO
	 * @throws BDException
	 */
	public AcaoMenuTO consultar(AcaoMenuTO acaoMenu) throws BDException{
		return (AcaoMenuTO) this.getDaoHibernate().consultar(AcaoMenuTO.class, acaoMenu);
	}
	
	/**
	 * Retorna uma lista de objetos AcaoMenuTO ordenados pela propriedade passada como par�metro.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @param orderBy - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(AcaoMenuTO acaoMenu, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(acaoMenu, orderBy);
	}
	
	/**
	 * Verifica se um determinado perfil de acesso pode visualizar uma A��O DO MENU.
	 *
	 * @param perfil - {@link PerfilAcessoTO}
	 * @param menu - {@link AcaoMenuTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public boolean possuiAcessoAcao(PerfilAcessoTO perfil, AcaoMenuTO acaoMenu) throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(AcaoMenuTO.class);
		//insere a associa��o
		criteria.createAlias("listaPerfilAcesso", "PERFIL");
		//insere as cl�usulas de restri��o
		criteria.add(Restrictions.eq("PERFIL.id", perfil.getId()));
		criteria.add(Restrictions.eq("id", acaoMenu.getId()));
		return !criteria.list().isEmpty() ? true : false;
	}

	/**
	 * Retorna a ultima posi��o valida para um menu de a��o agrupador.
	 * @param menu - {@link AcaoTO}
	 * @throws BDException 
	 * @return boolean
	 */
	public Integer buscarUltimaPosicaoAcaoAgrupador(AcaoMenuTO acaoMenu) throws BDException{
		StringBuffer hql = new StringBuffer()
		.append(" select max(BEAN.intHierarquia) from ")
		.append(AcaoMenuTO.class.getName()).append(" BEAN where BEAN.acaoMenuPai.id = ").append(acaoMenu.getId());

		Integer ultimaPosicao = (Integer)this.getDaoHibernate().buscarObjeto(hql.toString());
		//retorna a ultima posi��o do menu
		if(ultimaPosicao == null || ultimaPosicao == 0){
			return 0;
		}
		//retorna a ultima posi��o +1
		return ++ultimaPosicao;
		
	}

	/**
	 * Lista todas as a��es de um determinado usu�rio.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param objetoTO - String
	 * @param target - String
	 * @param sistema - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, String sistema, String pagina)throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(AcaoMenuTO.class);
		
		//insere as associa��es que ser�o utilizadas
		criteria.createCriteria("listaPerfilAcesso", "PERFIL");
		criteria.createCriteria("pagina", "PAGINA");
		criteria.createCriteria("PAGINA.sistema", "SISTEMA");
		
		//insere as restri��es
		criteria.add(Restrictions.eq("vchTransferObject", objetoTO));
		criteria.add(Restrictions.eq("vchFaseObjeto", faseObjeto));
		criteria.add(Restrictions.eq("SISTEMA.vchNome", sistema));
		criteria.add(Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		criteria.add((Criterion)Restrictions.eq("bitAgrupador", Boolean.TRUE));
		criteria.add((Criterion)Restrictions.isNull("acaoMenuPai"));
		//filtra pela pagina
		if(pagina != null && !pagina.isEmpty())
			criteria.add((Criterion)Restrictions.eq("PAGINA.vchPagina", pagina));
		return criteria.list();
	}

	/**
	 * Lista todas as a��es de um determinado usu�rio.
	 * 
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @param objetoTO - String
	 * @param target - String
	 * @param sistema - SistemaTO
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public List<TransferObject> listarAcaoUsuarioBase(PerfilAcessoTO perfilAcesso,  String objetoTO,  String faseObjeto, SistemaTO sistema, String pagina)throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(AcaoMenuTO.class);
		
		//insere as associa��es que ser�o utilizadas
		criteria.createCriteria("listaPerfilAcesso", "PERFIL");
		criteria.createCriteria("pagina", "PAGINA");
		criteria.createCriteria("PAGINA.sistema", "SISTEMA");
		
		//insere as restri��es
		criteria.add(Restrictions.eq("vchTransferObject", objetoTO));
		criteria.add(Restrictions.eq("vchFaseObjeto", faseObjeto));
		criteria.add(Restrictions.eq("SISTEMA.id", sistema.getId()));
		criteria.add(Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		criteria.add((Criterion)Restrictions.eq("bitAgrupador", Boolean.TRUE));
		criteria.add((Criterion)Restrictions.isNull("acaoMenuPai"));
		//filtra pela pagina
		if(pagina != null && !pagina.isEmpty())
			criteria.add((Criterion)Restrictions.eq("PAGINA.pagina", pagina));
		return criteria.list();
	}

	/**
	 * Retorna a a��o base da p�gina.
	 * 
	 * @param faseObjeto - String
	 * @param pagina - PaginaTO
	 * @return AcaoTO
	 * @throws BDException
	 */
	public AcaoMenuTO buscarAcaoBase(String faseObjeto, PaginaTO pagina)throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(AcaoMenuTO.class);
		//insere as associa��es que ser�o utilizadas
		criteria.createCriteria("pagina", "PAGINA");
		//insere as restri��es
		criteria.add(Restrictions.eq("vchFaseObjeto", faseObjeto));
		criteria.add((Criterion)Restrictions.eq("bitAgrupador", Boolean.TRUE));
		criteria.add((Criterion)Restrictions.isNull("acaoMenuPai"));
		//filtra pela pagina
		criteria.add((Criterion)Restrictions.eq("PAGINA.id", pagina.getId()));
		//retorna a a��o PAI para a p�gina
		return (AcaoMenuTO)this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	/**
	 * Busca o primeiro objeto encontrado que satisfa�a os par�metros de busca.
	 * 
	 * @param acaoMenu - AcaoMenuTO
	 * @return AcaoMenuTO
	 * @throws BDException
	 */
	public AcaoMenuTO buscarObjeto(AcaoMenuTO acaoMenu) throws BDException{
		return (AcaoMenuTO) this.getDaoHibernate().buscarObjeto(acaoMenu);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcaoMenuTO acaoMenu) throws BDException{
		this.getDaoHibernate().retirarObjetoSessao(acaoMenu);
	}
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param acaoMenu - AcaoMenuTO
	 * @throws BDException
	 */
	public void inicializarObjeto(AcaoMenuTO acaoMenu) throws BDException{
		this.getDaoHibernate().inicializar(acaoMenu);
	}

	/**
	 * Busca o primeiro objeto encontrado que contenha o nome especificado.
	 * @param nome - String
	 * @return AcaoMenuTO
	 * @throws BDException
	 */
	public AcaoMenuTO consultarAcaoMenuNome(String nome) throws BDException{
		Criteria criteria = getDaoHibernate().createCriteria(AcaoMenuTO.class);
		//busca atraves do contexto
		criteria.add(Restrictions.eq("vchNome", nome));
		return (AcaoMenuTO) this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	/**
	 * Retorna o nome da opera��o de altera��o.
	 * @return - {@link String} 
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar AcaoMenu";
	}

	/**
	 * Retorna o nome da opera��o de exclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir AcaoMenu";
	}

	/**
	 * Retorna o nome da opera��o de inclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir AcaoMenu";
	}

}
