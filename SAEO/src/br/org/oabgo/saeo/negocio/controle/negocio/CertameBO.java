package br.org.oabgo.saeo.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import br.org.oabgo.saeo.negocio.controle.entidade.CertameTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Certame;
import br.org.oabgo.saeo.negocio.controle.persistencia.CertamePO;

@Service
public class CertameBO implements Certame {

	@Autowired
	private CertamePO persistencia;
	
	protected void beforeInsert(TransferObject bean) throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean) throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(CertameTO certame, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(certame);
		this.persistencia.alterar(certame, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(CertameTO certame, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(certame, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(CertameTO certame, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(certame);
		this.persistencia.incluir(certame, usuarioLogado);
	}

	public CertameTO consultar(CertameTO certame) throws BDException {
		return this.persistencia.consultar(certame);
	}

	public List<TransferObject> listar(CertameTO certame) throws BDException {
		return this.persistencia.listar(certame);
	}

	public List<TransferObject> listar(CertameTO certame, String orderBy)
			throws BDException {
		return this.persistencia.listar(certame, orderBy);
	}

	public void retirarObjetoSessao(CertameTO certame) {
		this.persistencia.retirarObjetoSessao(certame);
	}

}
