package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.AutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> AutenticacaoAdslPO.java <br>
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
@Service("autenticacaoAdslPO")
public class AutenticacaoAdslPO extends SATIPersistencia {

	public void alterar(AutenticacaoAdslTO autenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(autenticacaoAdsl, usuarioLogado);
	}
	
	public void excluir(AutenticacaoAdslTO autenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(autenticacaoAdsl, usuarioLogado);
	}
	
	public void incluir(AutenticacaoAdslTO autenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(autenticacaoAdsl, usuarioLogado);
	}
	
	public AutenticacaoAdslTO consultar(AutenticacaoAdslTO autenticacaoAdsl) throws BDException{
		return (AutenticacaoAdslTO) this.getDaoHibernate().consultar(AutenticacaoAdslTO.class, autenticacaoAdsl);
	}
	
	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl) throws BDException{
		return this.getDaoHibernate().listar(autenticacaoAdsl);
	}
	
	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(autenticacaoAdsl, orderBy);
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
