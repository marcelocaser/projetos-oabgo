package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.TipoUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.TipoUnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.persistencia.TipoUnidadeAdministrativaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> TipoUnidadeAdministrativaBO.java <br>
 * <b>Descrição:</b>     <br>
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
public class TipoUnidadeAdministrativaBO implements TipoUnidadeAdministrativa {
	
	@Autowired
	private TipoUnidadeAdministrativaPO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(tipoUnidadeAdministrativa);
		this.persistencia.alterar(tipoUnidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(tipoUnidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(tipoUnidadeAdministrativa);
		this.persistencia.incluir(tipoUnidadeAdministrativa, usuarioLogado);
	}
	
	public TipoUnidadeAdministrativaTO consultar(
			TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) throws BDException {
		return this.persistencia.consultar(tipoUnidadeAdministrativa);
	}

	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa)
			throws BDException {
		return this.persistencia.listar(tipoUnidadeAdministrativa);
	}

	public List<TransferObject> listar(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa,
			String orderBy) throws BDException {
		return this.persistencia.listar(tipoUnidadeAdministrativa, orderBy);
	}
	
	public void retirarObjetoSessao(TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) {
		this.persistencia.retirarObjetoSessao(tipoUnidadeAdministrativa);
	}
}
