package br.org.oabgo.sati.negocio.controle.persistencia;

import java.util.List;

import org.springframework.stereotype.Service;

import br.org.oabgo.sati.negocio.controle.SATIPersistencia;
import br.org.oabgo.sati.negocio.controle.entidade.AtendimentoAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.dao.TransferObject;
import core.excecoes.BDException;

/**
 * <b>Classe:</b> AtendimentoAutenticacaoAdslPO.java <br>
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
@Service("atendimentoAutenticacaoAdslPO")
public class AtendimentoAutenticacaoAdslPO extends SATIPersistencia {

	public void alterar(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.alterar(atendimentoAutenticacaoAdsl, usuarioLogado);
	}
	
	public void excluir(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.excluir(atendimentoAutenticacaoAdsl, usuarioLogado);
	}
	
	public void incluir(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl, UsuarioTO usuarioLogado) throws BDException{
		super.incluir(atendimentoAutenticacaoAdsl, usuarioLogado);
	}
	
	public AtendimentoAutenticacaoAdslTO consultar(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl) throws BDException{
		return (AtendimentoAutenticacaoAdslTO) this.getDaoHibernate().consultar(AtendimentoAutenticacaoAdslTO.class, atendimentoAutenticacaoAdsl);
	}
	
	public List<TransferObject> listar(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl) throws BDException{
		return this.getDaoHibernate().listar(atendimentoAutenticacaoAdsl);
	}
	
	public List<TransferObject> listar(AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl, String orderBy)throws BDException{
		return this.getDaoHibernate().listar(atendimentoAutenticacaoAdsl, orderBy);
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
