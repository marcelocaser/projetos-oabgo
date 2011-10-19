package br.org.oabgo.saeo.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.saeo.negocio.controle.entidade.CandidatoAnteriorExameOrdemTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.CandidatoAnteriorExameOrdem;
import br.org.oabgo.saeo.negocio.controle.persistencia.CandidatoAnteriorExameOrdemPO;
import core.dao.TransferObject;
import core.enumeration.OrderType;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service
public class CandidatoAnteriorExameOrdemBO implements CandidatoAnteriorExameOrdem {
	
	@Autowired
	private CandidatoAnteriorExameOrdemPO persistencia;
	
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeUpdate(candidatoAnteriorExameOrdem);
		this.persistencia.alterar(candidatoAnteriorExameOrdem, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.persistencia.excluir(candidatoAnteriorExameOrdem, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			UsuarioTO usuarioLogado) throws RegraNegocioException, BDException {
		this.beforeInsert(candidatoAnteriorExameOrdem);
		this.persistencia.incluir(candidatoAnteriorExameOrdem, usuarioLogado);
	}

	public CandidatoAnteriorExameOrdemTO consultar(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem)
			throws BDException {
		return this.persistencia.consultar(candidatoAnteriorExameOrdem);
	}

	public List<TransferObject> listar(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem)
			throws BDException {
		return this.persistencia.listar(candidatoAnteriorExameOrdem);
	}

	public List<TransferObject> listar(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			String orderBy) throws BDException {
		return this.persistencia.listar(candidatoAnteriorExameOrdem, orderBy);
	}
	
	public List<TransferObject> listar(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem,
			String orderBy, OrderType orderType) throws BDException {
		return this.persistencia.listar(candidatoAnteriorExameOrdem, orderBy, orderType);
	}
	
	public List<TransferObject> listarDezUltimos() throws BDException {
		return this.persistencia.listarDezUltimos();
	}

	public void retirarObjetoSessao(
			CandidatoAnteriorExameOrdemTO candidatoAnteriorExameOrdem) {
		this.persistencia.retirarObjetoSessao(candidatoAnteriorExameOrdem);
	}
	
}
