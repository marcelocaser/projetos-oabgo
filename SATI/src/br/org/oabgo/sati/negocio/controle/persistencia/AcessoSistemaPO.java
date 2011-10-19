package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.AcessoSistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> AcessoSistemaPO.java <br>
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
@Service("acessoSistemaPO")
public class AcessoSistemaPO extends SATIPersistencia {
	
	/**
	 * Persiste as informa��es contidas no objeto passado como par�metro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(acessoSistema, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como referencia.
	 * 
	 * @param dataLimite - Date
	 * @throws BDException
	 */
	public void excluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(acessoSistema, usuarioLogado);
	}

	/**
	 * Inclui as informa��es contidas no objeto.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(AcessoSistemaTO acessoSistema, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(acessoSistema, usuarioLogado);
	}
	
	/**
	 * Consulta um objeto.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @return AcessoSistemaTO
	 * @throws BDException
	 */
	public AcessoSistemaTO consultar(AcessoSistemaTO acessoSistema) throws BDException{
		return (AcessoSistemaTO) super.consultar(acessoSistema);
	}
	
	/**
	 * Retorna uma lista de objetos AcessoSistemaTO ordenados pela propriedade passada como par�metro.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @param orderBy - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(AcessoSistemaTO acessoSistema, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(acessoSistema, orderBy);
	}
	
	/**
	 * Busca o primeiro objeto encontrado que satisfa�a os par�metros de busca.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @return AcessoSistemaTO
	 * @throws BDException
	 */
	public AcessoSistemaTO buscarObjeto(AcessoSistemaTO acessoSistema) throws BDException{
		return (AcessoSistemaTO) this.getDaoHibernate().buscarObjeto(acessoSistema);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - AcessoSistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(AcessoSistemaTO acessoSistema) throws BDException{
		this.getDaoHibernate().retirarObjetoSessao(acessoSistema);
	}
	
	/**
	 * Exclui todos os acessoSistemas realizados em um per�odo menor ou igual ao
	 * da data informada.
	 * 
	 * @param dataLimite - Date
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@SuppressWarnings("unchecked")
	public void excluirAcessosAntigos(Date dataLimite) throws BDException{
		Criteria criteria = getDaoHibernate().createCriteria(AcessoSistemaTO.class);
		//busca todos os acessoSistemas realizados at� a data passada como par�metro
		criteria.add(Restrictions.le("data", dataLimite));
		
		//apaga todos os registros encontrados
		List<TransferObject> lista = criteria.list();
		for(TransferObject acessoSistema : lista){
			super.excluir(acessoSistema, null);
		}
	}

	/**
	 * Exclui a lista de objetos passado como referencia.
	 * 
	 * @param lista - List
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(List<TransferObject> lista, UsuarioTO usuarioLogado) throws BDException{
		super.excluirLista(lista, usuarioLogado);
	}
	
	/**
	 * Retorna a quantidade de registros para encontrada para o objeto.
	 * 
	 * @param acessoSistema - AcessoSistemaTO
	 * @return Integer
	 * @throws BDException
	 */
	public Integer count(AcessoSistemaTO acessoSistema) throws BDException{
		return  getDaoHibernate().count(acessoSistema);
	}

	/**
	 * Retorna o nome da opera��o de altera��o.
	 * @return - {@link String} 
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Acesso";
	}

	/**
	 * Retorna o nome da opera��o de exclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Acesso";
	}

	/**
	 * Retorna o nome da opera��o de inclus�o.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Acesso";
	}
}
