package br.com.flavios.pbpf.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.flavios.pbpf.negocio.controle.entidade.EstoqueProdutoTO;
import br.com.flavios.pbpf.negocio.controle.entidade.FilialTO;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.EstoqueProduto;
import br.com.flavios.pbpf.negocio.controle.persistencia.EstoqueProdutoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> EstoqueProdutoBO.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.negocio.controle.negocio <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 * Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class EstoqueProdutoBO implements EstoqueProduto {

	@Autowired
	private EstoqueProdutoPO persistencia;

	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
	}

	protected void beforeDelete(TransferObject bean)
			throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(estoqueProduto);
		this.persistencia.alterar(estoqueProduto, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeDelete(estoqueProduto);
		this.persistencia.excluir(estoqueProduto, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(EstoqueProdutoTO estoqueProduto, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(estoqueProduto);
		this.persistencia.incluir(estoqueProduto, usuarioLogado);
	}
	
	public List<TransferObject> incluirDataTable(EstoqueProdutoTO estoqueProduto, List<TransferObject> list) {
		list.add(estoqueProduto);
		return list;
	}
	
	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto, FilialTO filial) throws BDException {
		return this.persistencia.consultar(estoqueProduto, filial);
	}

	public EstoqueProdutoTO consultar(EstoqueProdutoTO estoqueProduto)
			throws BDException {
		return this.persistencia.consultar(estoqueProduto);
	}

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto)
			throws BDException {
		return this.persistencia.listar(estoqueProduto);
	}

	public List<TransferObject> listar(EstoqueProdutoTO estoqueProduto,
			String orderBy) throws BDException {
		return this.persistencia.listar(estoqueProduto, orderBy);
	}

	public List<TransferObject> listar() throws BDException {
		return this.persistencia.listar();
	}

	public void retirarObjetoSessao(EstoqueProdutoTO estoqueProduto) {
		this.persistencia.retirarObjetoSessao(estoqueProduto);
	}

	private void goMsg(String msg) throws RegraNegocioException {
		MensagemLista msgs = new MensagemLista();
		msgs.addMensagem(msg);
		throw new RegraNegocioException(msgs);
	}

}
