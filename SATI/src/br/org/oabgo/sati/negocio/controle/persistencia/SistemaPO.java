package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * 
 * <b>Classe:</b> SistemaPO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.persistencia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Service("sistemaPO")
public class SistemaPO extends SATIPersistencia {

	/**
	 * Persiste as informações contidas no objeto passado como parâmetro.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void alterar(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(sistema, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como referencia.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void excluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(sistema, usuarioLogado);
	}
	
	/**
	 * Inclui as informações contidas no objeto.
	 * 
	 * @param sistema - SistemaTO
	 * @param usuarioLogado - UsuarioTO
	 * @throws BDException
	 */
	public void incluir(SistemaTO sistema, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(sistema, usuarioLogado);
	}
	
	/**
	 * Consulta um objeto.
	 * 
	 * @param sistema - SistemaTO
	 * @return SistemaTO
	 * @throws BDException
	 */
	public SistemaTO consultar(SistemaTO sistema) throws BDException{
		return (SistemaTO) this.getDaoHibernate().consultar(SistemaTO.class, sistema);
	}
	
	/**
	 * Retorna uma lista de objetos SistemaTO ordenados pela propriedade passada como parâmetro.
	 * 
	 * @param sistema - SistemaTO
	 * @param orderBy - String
	 * @return List<TransferObject>
	 * @throws BDException
	 */
	public List<TransferObject> listar(SistemaTO sistema, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(sistema, orderBy);
	}
	
	/**
	 * Busca o primeiro objeto encontrado que satisfaça os parâmetros de busca.
	 * 
	 * @param sistema - SistemaTO
	 * @return SistemaTO
	 * @throws BDException
	 */
	public SistemaTO buscarObjeto(SistemaTO sistema) throws BDException{
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(sistema);
	}
	
	/**
	 * Remove o objeto atual da Session do Hibernate.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void retirarObjetoSessao(SistemaTO sistema) throws BDException{
		this.getDaoHibernate().retirarObjetoSessao(sistema);
	}
	
	/**
	 * Inicializa as propriedades de um obejto.
	 * @param sistema - SistemaTO
	 * @throws BDException
	 */
	public void inicializarObjeto(SistemaTO sistema) throws BDException{
		this.getDaoHibernate().inicializar(sistema);
	}

	/**
	 * Busca o primeiro objeto encontrado que contenha o nome especificado.
	 * @param nome - String
	 * @return SistemaTO
	 * @throws BDException
	 */
	public SistemaTO consultarSistemaNome(String nome) throws BDException{
		Criteria criteria = getDaoHibernate().createCriteria(SistemaTO.class);
		//busca atraves do contexto
		criteria.add(Restrictions.eq("vchNome", nome));
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(criteria);
	}

	/**
	 * Retorna o sistema com base no Contexto.
	 * @param contexto - String
	 * @return SistemaTO
	 * @throws BDException
	 */
	public SistemaTO consultarSistemaContexto(String contexto) throws BDException{
//		Criteria criteria = getDaoHibernate().createCriteria(SistemaTO.class);
//		//busca atraves do contexto
//		criteria.add(Restrictions.eq("vchContextoAplicacao", contexto));
		SistemaTO sistemaTO = new SistemaTO();
		sistemaTO.setVchContextoAplicacao(contexto);
		return (SistemaTO) this.getDaoHibernate().buscarObjeto(sistemaTO);//.buscarObjeto(criteria);
	}
	
	/**
	 * Retorna o nome da operação de alteração.
	 * @return - {@link String} 
	 */
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Sistema";
	}

	/**
	 * Retorna o nome da operação de exclusão.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoExclusao() {
		return "Excluir Sistema";
	}

	/**
	 * Retorna o nome da operação de inclusão.
	 * @return - {@link String}
	 */
	protected String getNomeOperacaoInclusao() {
		return "Incluir Sistema";
	}

}
