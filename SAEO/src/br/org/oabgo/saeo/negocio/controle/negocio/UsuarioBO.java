package br.org.oabgo.saeo.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.saeo.negocio.controle.persistencia.UsuarioPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

@Service
public class UsuarioBO implements Usuario {

	private static final long serialVersionUID = 6163656505600586094L;
	
	@Autowired
	private UsuarioPO persistencia;

	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
	}

	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
		
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		beforeUpdate(usuario);
		this.persistencia.alterar(usuario, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.persistencia.excluir(usuario, usuarioLogado);
	}
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		beforeInsert(usuario);
		this.persistencia.incluir(usuario, usuarioLogado);
	}
	
	public UsuarioTO consultar(UsuarioTO usuario) throws BDException {
		return this.persistencia.consultar(usuario);
	}
	
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException{
		return this.persistencia.listar(usuario);
	}
	
	public List<TransferObject> listar(UsuarioTO to, String orderBy) throws BDException{
		return this.persistencia.listar(to, orderBy);
	}
	
	public void retirarObjetoSessao(UsuarioTO usuario) {
		this.persistencia.retirarObjetoSessao(usuario);
	}
	
	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException {
		return this.persistencia.buscarUsuarioPorLogin(userName);
	}

	@SuppressWarnings("unused")
	private void goMsg(String msg) throws RegraNegocioException {
		MensagemLista msgs = new MensagemLista();
		msgs.addMensagem(msg);
		throw new RegraNegocioException(msgs);
	}

}
