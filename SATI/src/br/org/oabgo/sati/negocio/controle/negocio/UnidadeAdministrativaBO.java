package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.UnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.persistencia.UnidadeAdministrativaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> UnidadeAdministrativaBO.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class UnidadeAdministrativaBO implements UnidadeAdministrativa {

	@Autowired
	private UnidadeAdministrativaPO persistencia;

	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {

		//UnidadeAdministrativaTO unidadeAdministrativa = (UnidadeAdministrativaTO) bean;
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
		
		//UnidadeAdministrativaTO unidadeAdministrativa = (UnidadeAdministrativaTO) bean;

	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		//this.beforeUpdate(unidadeAdministrativa);
		this.persistencia.alterar(unidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(unidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(UnidadeAdministrativaTO unidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		//this.beforeInsert(unidadeAdministrativa);
		this.persistencia.incluir(unidadeAdministrativa, usuarioLogado);
	}

	public UnidadeAdministrativaTO consultar(UnidadeAdministrativaTO unidadeAdministrativa)
			throws BDException {
		return this.persistencia.consultar(unidadeAdministrativa);
	}

	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa)
			throws BDException {
		return this.persistencia.listar(unidadeAdministrativa);
	}

	public List<TransferObject> listar(UnidadeAdministrativaTO unidadeAdministrativa,
			String orderBy) throws BDException {
		return this.persistencia.listar(unidadeAdministrativa, orderBy);
	}
	
	public void retirarObjetoSessao(UnidadeAdministrativaTO unidadeAdministrativa) {
		this.persistencia.retirarObjetoSessao(unidadeAdministrativa);
	}

}
