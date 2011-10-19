package br.org.oabgo.siged.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.siged.negocio.controle.entidade.RelatorTO;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Relator;
import br.org.oabgo.siged.negocio.controle.persistencia.RelatorPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> RelatorBO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SIGED <br>
 * <b>Pacote:</b> br.org.oabgo.siged.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2011 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class RelatorBO implements Relator {
	
	@Autowired
	private RelatorPO persistencia;

	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(relator);
		this.persistencia.alterar(relator, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(relator, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(RelatorTO relator, UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(relator);
		this.persistencia.incluir(relator, usuarioLogado);
	}

	public RelatorTO consultar(RelatorTO relator) throws BDException {
		return this.persistencia.consultar(relator);
	}

	public List<TransferObject> listar(RelatorTO relator) throws BDException {
		return this.persistencia.listar(relator);
	}

	public List<TransferObject> listar(RelatorTO relator, String orderBy) throws BDException {
		return this.persistencia.listar(relator, orderBy);
	}

	public void retirarObjetoSessao(RelatorTO relator) {
		this.persistencia.retirarObjetoSessao(relator);
	}

}
