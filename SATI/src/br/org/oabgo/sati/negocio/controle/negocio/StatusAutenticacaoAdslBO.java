package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.StatusAutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.StatusAutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.persistencia.StatusAutenticacaoAdslPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> StatusAutenticacaoAdslBO.java <br>
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
public class StatusAutenticacaoAdslBO implements StatusAutenticacaoAdsl {
	
	@Autowired
	private StatusAutenticacaoAdslPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(statusAutenticacaoAdsl);
		this.persistencia.alterar(statusAutenticacaoAdsl, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(statusAutenticacaoAdsl, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(StatusAutenticacaoAdslTO statusAutenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(statusAutenticacaoAdsl);
		this.persistencia.incluir(statusAutenticacaoAdsl, usuarioLogado);
	}

	public StatusAutenticacaoAdslTO consultar(
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl) throws BDException {
		return this.persistencia.consultar(statusAutenticacaoAdsl);
	}

	public List<TransferObject> listar(
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl) throws BDException {
		return this.persistencia.listar(statusAutenticacaoAdsl);
	}

	public List<TransferObject> listar(
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl, String orderBy)
			throws BDException {
		return this.persistencia.listar(statusAutenticacaoAdsl, orderBy);
	}

	public void retirarObjetoSessao(
			StatusAutenticacaoAdslTO statusAutenticacaoAdsl) {
		this.persistencia.retirarObjetoSessao(statusAutenticacaoAdsl);
	}

}
