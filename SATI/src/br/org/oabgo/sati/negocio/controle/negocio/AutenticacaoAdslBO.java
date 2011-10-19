package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import br.org.oabgo.sati.negocio.controle.entidade.AutenticacaoAdslTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.AutenticacaoAdsl;
import br.org.oabgo.sati.negocio.controle.persistencia.AutenticacaoAdslPO;

/**
 * <b>Classe:</b> AutenticacaoAdslBO.java <br>
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
public class AutenticacaoAdslBO implements AutenticacaoAdsl {
	
	@Autowired
	private AutenticacaoAdslPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(autenticacaoAdsl);
		this.persistencia.alterar(autenticacaoAdsl, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(autenticacaoAdsl, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(AutenticacaoAdslTO autenticacaoAdsl,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(autenticacaoAdsl);
		this.persistencia.incluir(autenticacaoAdsl, usuarioLogado);
	}

	public AutenticacaoAdslTO consultar(AutenticacaoAdslTO autenticacaoAdsl)
			throws BDException {
		return this.persistencia.consultar(autenticacaoAdsl);
	}

	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl)
			throws BDException {
		return this.persistencia.listar(autenticacaoAdsl);
	}

	public List<TransferObject> listar(AutenticacaoAdslTO autenticacaoAdsl,
			String orderBy) throws BDException {
		return this.persistencia.listar(autenticacaoAdsl, orderBy);
	}

	public void retirarObjetoSessao(AutenticacaoAdslTO autenticacaoAdsl) {
		this.persistencia.retirarObjetoSessao(autenticacaoAdsl);
	}

}
