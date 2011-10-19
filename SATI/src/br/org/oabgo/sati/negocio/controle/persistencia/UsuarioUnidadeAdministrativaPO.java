package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioUnidadeAdministrativaTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> UsuarioUnidadeAdministrativaPO.java <br>
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
@Service("usuarioUnidadeAdministrativaPO")
public class UsuarioUnidadeAdministrativaPO extends SATIPersistencia {
	
	public void alterar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(usuarioUnidadeAdministrativa, usuarioLogado);
	}
	
	public void excluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(usuarioUnidadeAdministrativa, usuarioLogado);
	}
	
	public void incluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(usuarioUnidadeAdministrativa, usuarioLogado);
	}
	
	public UsuarioUnidadeAdministrativaTO consultar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa) throws BDException{
		return (UsuarioUnidadeAdministrativaTO) super.consultar(usuarioUnidadeAdministrativa);
	}
	
	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa)throws BDException{
		return this.getDaoHibernate().listar(usuarioUnidadeAdministrativa);
	}
	
	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(usuarioUnidadeAdministrativa, orderBy);
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
