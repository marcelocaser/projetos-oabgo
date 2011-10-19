package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioUnidadeAdministrativaTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.UsuarioUnidadeAdministrativa;
import br.org.oabgo.sati.negocio.controle.persistencia.UsuarioUnidadeAdministrativaPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> UsuarioUnidadeAdministrativaBO.java <br>
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
public class UsuarioUnidadeAdministrativaBO implements UsuarioUnidadeAdministrativa {
	
	@Autowired 
	private UsuarioUnidadeAdministrativaPO persistencia;

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.alterar(usuarioUnidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(usuarioUnidadeAdministrativa, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.incluir(usuarioUnidadeAdministrativa, usuarioLogado);
	}
	
	public UsuarioUnidadeAdministrativaTO consultar(
			UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa) throws BDException {
		return this.persistencia.consultar(usuarioUnidadeAdministrativa);
	}

	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa)
			throws BDException {
		return this.persistencia.listar(usuarioUnidadeAdministrativa);
	}

	public List<TransferObject> listar(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa,
			String orderBy) throws BDException {
		return this.persistencia.listar(usuarioUnidadeAdministrativa, orderBy);
	}

	public void retirarObjetoSessao(UsuarioUnidadeAdministrativaTO usuarioUnidadeAdministrativa) {
		this.persistencia.retirarObjetoSessao(usuarioUnidadeAdministrativa);
	}

}
