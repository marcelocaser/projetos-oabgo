package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import br.org.oabgo.sati.negocio.controle.entidade.AtendimentoAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AtendimentoAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.persistencia.AtendimentoAutenticacaoAdslPO;

/**
 * <b>Classe:</b> AtendimentoAutenticacaoAdslBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class AtendimentoAutenticacaoAdslBO implements
		AtendimentoAutenticacaoAdsl {
	
	@Autowired
	private AtendimentoAutenticacaoAdslPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(atendimentoAutenticacaoAdsl);
		this.persistencia.alterar(atendimentoAutenticacaoAdsl, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(atendimentoAutenticacaoAdsl, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(atendimentoAutenticacaoAdsl);
		this.persistencia.incluir(atendimentoAutenticacaoAdsl, usuarioLogado);
	}

	public AtendimentoAutenticacaoAdslTO consultar(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl)
			throws BDException {
		return this.persistencia.consultar(atendimentoAutenticacaoAdsl);
	}

	public List<TransferObject> listar(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl)
			throws BDException {
		return this.persistencia.listar(atendimentoAutenticacaoAdsl);
	}

	public List<TransferObject> listar(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl,
			String orderBy) throws BDException {
		return this.persistencia.listar(atendimentoAutenticacaoAdsl, orderBy);
	}

	public void retirarObjetoSessao(
			AtendimentoAutenticacaoAdslTO atendimentoAutenticacaoAdsl) {
		this.persistencia.retirarObjetoSessao(atendimentoAutenticacaoAdsl);
	}

}
