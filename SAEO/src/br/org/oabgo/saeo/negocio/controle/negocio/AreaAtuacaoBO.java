package br.org.oabgo.saeo.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.saeo.negocio.controle.entidade.AreaAtuacaoTO;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.AreaAtuacao;
import br.org.oabgo.saeo.negocio.controle.persistencia.AreaAtuacaoPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;

@Service
public class AreaAtuacaoBO implements AreaAtuacao {

	@Autowired
	private AreaAtuacaoPO persistencia;

	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(areaAtuacao);
		this.persistencia.alterar(areaAtuacao, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(areaAtuacao, usuarioLogado);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(AreaAtuacaoTO areaAtuacao, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(areaAtuacao);
		this.persistencia.incluir(areaAtuacao, usuarioLogado);
	}

	public AreaAtuacaoTO consultar(AreaAtuacaoTO areaAtuacao)
			throws BDException {
		return this.persistencia.consultar(areaAtuacao);
	}

	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao)
			throws BDException {
		return this.persistencia.listar(areaAtuacao);
	}

	public List<TransferObject> listar(AreaAtuacaoTO areaAtuacao, String orderBy)
			throws BDException {
		return this.persistencia.listar(areaAtuacao, orderBy);
	}
	
	public List<TransferObject> listarPorStatus(Boolean status) throws BDException {
		return this.persistencia.listarPorStatus(status);
	}

	public void retirarObjetoSessao(AreaAtuacaoTO areaAtuacao) {
		this.persistencia.retirarObjetoSessao(areaAtuacao);
	}

}
