package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> UnidadeAdministrativaPO.java <br>
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
@Service("unidadeAdministrativaPO")
public class UnidadeAdministrativaPO extends SATIPersistencia {
	
	public void alterar(UnidadeAdministrativaTO unidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(unidadeAdministrativa, usuarioLogado);
	}
	
	public void excluir(UnidadeAdministrativaTO unidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(unidadeAdministrativa, usuarioLogado);
	}
	
	public void incluir(UnidadeAdministrativaTO unidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(unidadeAdministrativa, usuarioLogado);
	}
	
	public UnidadeAdministrativaTO consultar(UnidadeAdministrativaTO unidadeAdministrativa) throws BDException{
		return (UnidadeAdministrativaTO) this.getDaoHibernate().consultar(UnidadeAdministrativaTO.class, unidadeAdministrativa);
	}
	
	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa) throws BDException{
		return this.getDaoHibernate().listar(unidadeAdministrativa);
	}
	
	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(unidadeAdministrativa, orderBy);
	}
	
	protected String getNomeOperacaoAlteracao() {
		return "Alterar Unidade Administrativa";
	}

	protected String getNomeOperacaoExclusao() {
		return "Excluir Unidade Administrativa";
	}

	protected String getNomeOperacaoInclusao() {
		return "Incluir Unidade Administrativa";
	}

}
