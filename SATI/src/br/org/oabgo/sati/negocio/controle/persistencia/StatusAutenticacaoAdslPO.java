package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.StatusAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> StatusAutenticacaoAdslPO.java <br>
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
@Service("statusAutenticacaoAdslPO")
public class StatusAutenticacaoAdslPO extends SATIPersistencia {

	public void alterar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(statusAutenticacaoAdsl, usuarioLogado);
	}
	
	public void excluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(statusAutenticacaoAdsl, usuarioLogado);
	}
	
	public void incluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(statusAutenticacaoAdsl, usuarioLogado);
	}
	
	public StatusAutenticacaoAdslTO consultar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl) throws BDException{
		return (StatusAutenticacaoAdslTO) this.getDaoHibernate().consultar(StatusAutenticacaoAdslTO.class, statusAutenticacaoAdsl);
	}
	
	public List<TransferObject> listar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl) throws BDException{
		return this.getDaoHibernate().listar(statusAutenticacaoAdsl);
	}
	
	public List<TransferObject> listar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(statusAutenticacaoAdsl, orderBy);
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
