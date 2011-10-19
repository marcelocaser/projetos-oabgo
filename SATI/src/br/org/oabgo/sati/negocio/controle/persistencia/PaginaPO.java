package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.PaginaTO;
import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * 
 * <b>Classe:</b> PaginaPO.java <br>
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
@Service("paginaPO")
public class PaginaPO extends SATIPersistencia {

	/**
	 * Persiste as informa��es contidas no objeto passado como par�metro.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(PaginaTO pagina, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(pagina, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como referencia.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(PaginaTO pagina, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(pagina, usuarioLogado);
	}
	
	/**
	 * Inclui as informa��es contidas no objeto.
	 * 
	 * @param pagina - PaginaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(PaginaTO pagina, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(pagina, usuarioLogado);
	}
	
	/**
	 * Consulta um objeto.
	 * 
	 * @param pagina - PaginaTO
	 * @return PaginaTO
	 * @throws BDException
	 */
	public PaginaTO consultar(PaginaTO pagina) throws BDException{
		return (PaginaTO) this.getDaoHibernate().consultar(PaginaTO.class, pagina);
	}
	
	/**
	 * Verifica se o perfil de acesso passado como par�metro tem permiss�o para acessar a p�gina destino.
	 * 
	 * @param pagina - {@link PaginaTO}
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean possuiAcessoPagina(PaginaTO pagina, PerfilAcessoTO perfilAcesso)throws BDException{
		Criteria criteria = this.getDaoHibernate().createCriteria(PaginaTO.class);
		
		//insere as associa��es que ser�o utilizadas
		criteria.createCriteria("listaPerfilAcesso", "PERFIL");
		
		criteria.add(Restrictions.eq("id", pagina.getId()));
		criteria.add(Restrictions.eq("PERFIL.id", perfilAcesso.getId()));
		
		List<TransferObject> lista = criteria.list();
		return lista.isEmpty() ? false : true;
	}
	
	/**
	 * Retorna uma lista de objetos PaginaTO ordenados pela propriedade passada como par�metro.
	 * 
	 * @param pagina - PaginaTO
	 * @param orderBy - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(PaginaTO pagina, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(pagina, orderBy);
	}
	
	/**
	 * Busca o primeiro objeto encontrado que satisfa�a os par�metros de busca.
	 * 
	 * @param pagina - PaginaTO
	 * @return PaginaTO
	 * @throws BDException
	 */
	public PaginaTO buscarObjeto(PaginaTO pagina) throws BDException{
		return (PaginaTO) this.getDaoHibernate().buscarObjeto(pagina);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(PaginaTO pagina) throws BDException{
		this.getDaoHibernate().retirarObjetoSessao(pagina);
	}
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param pagina - PaginaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(PaginaTO pagina) throws BDException{
		this.getDaoHibernate().inicializar(pagina);
	}

	/**
	 * Busca o primeiro objeto encontrado que contenha o nome especificado.
	 * @param nome - String
	 * @return PaginaTO
	 * @throws BDException
	 */
	public PaginaTO consultarPaginaNome(String nome) throws BDException{
		Criteria criteria = getDaoHibernate().createCriteria(PaginaTO.class);
		//busca atraves do contexto
		criteria.add(Restrictions.eq("vchPagina", nome));
		return (PaginaTO) this.getDaoHibernate().buscarObjeto(criteria);
	}
	
	/**
	 * Retorna o nome da opera��o de altera��o.
	 * @return - {@link String} 
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Pagina";
	}

	/**
	 * Retorna o nome da opera��o de exclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Pagina";
	}

	/**
	 * Retorna o nome da opera��o de inclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Pagina";
	}

}
