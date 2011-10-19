package br.org.oabgo.siged.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.siged.negocio.controle.persistencia.UsuarioPO;
import core.criptografia.Encrypter;
import core.criptografia.EncrypterImpl;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> UsuarioBO.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SIGED <br>
 * <b>Pacote:</b> br.org.oabgo.siged.negocio.controle.negocio <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class UsuarioBO implements Usuario {

	private static final long serialVersionUID = 6163656505600586094L;
	
	@Autowired
	private UsuarioPO persistencia;

	/**
	 * Executa a valida��o da regra de neg�cio antes de inserir o objeto.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - N�o pode exister dois objetos UsuarioTO com o mesmo
	 *             nome. </b>
	 */
	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
		
		UsuarioTO usuario = (UsuarioTO) bean;
		
		UsuarioTO usuarioTmp = new UsuarioTO();
		
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		//objeto que encapsula as mensagens de regra de negocio
		MensagemLista msgs = new MensagemLista();
		
		//valida login j� cadastro
		usuarioTmp.setVchLogin(usuario.getVchLogin());
		
		//verifica se o existe usuario cadastrado com o mesmo login
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("sigedUsuarioJaCadastrado");
		}
		
		//valida e-mail j� cadastro
		usuarioTmp.setVchLogin(new String());
		usuarioTmp.setVchEmail(usuario.getVchEmail());
		
		//verifica se o existe usuario cadastrado com o mesmo email
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("sigedUsuarioEmailJaCadastrado");
		}
		
		//encriptar senha do usu�rio antes de incluir.
		usuario.setVchSenha(encrypt.encrypt(usuario.getVchSenha()));
		
		//define usuario como sendo apenas do SIGED
		usuario.setBitUsuarioSIGED(Boolean.TRUE);
		
		if(msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
		
	}

	/**
	 * Executa a valida��o da regra de neg�cio antes de inserir o objeto.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - N�o pode exister dois objetos UsuarioTO com o mesmo
	 *             nome </b>
	 */
	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
		
		UsuarioTO usuario = (UsuarioTO) bean;
		
		UsuarioTO usuarioTmp = new UsuarioTO();
		
		usuarioTmp.setVchEmail(usuario.getVchEmail());
		
		//verifica se o existe usuario cadastrado com o mesmo e-mail
		if(this.persistencia.beforeUpdate(usuario, usuarioTmp)){
			throw new RegraNegocioException("sigedUsuarioEmailJaCadastrado");
		}
		
	}
	
	protected void beforeDelete(TransferObject bean) throws RegraNegocioException {
		
		UsuarioTO usuario =(UsuarioTO) bean;
		
		//verifica se o usuario n�o � o �nico cadastrado.
		if (usuario.getVchLogin().equals("admin")) {
			throw new RegraNegocioException("sigedMsgUsuarioAdmin");
		}
		
	}

	/**
	 * Persiste as altera��es do objeto passado como par�metro.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - ColaboradorTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeUpdate(usuario);
		this.persistencia.alterar(usuario, usuarioLogado);
	}
	

	/**
	 * Persiste as altera��es no objeto UsuarioTO.
	 *
	 * @param usuario
	 * @param usuarioLogado
	 * @param confirmaSenha
	 * @param senhaAtual
	 * @throws RegraNegocioException
	 * @throws BDException - 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void alterar(UsuarioTO usuario, UsuarioTO usuarioLogado, String confirmaSenha, String senhaAtual)
			throws RegraNegocioException, BDException {
		
		UsuarioTO usuarioTmp = usuario;
		
		MensagemLista msgs = new MensagemLista();
		
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		if(usuarioTmp.getVchSenha() != null && !usuarioTmp.getVchSenha().isEmpty() && !usuarioTmp.getVchSenha().equals(encrypt.encrypt(senhaAtual))) {
			msgs.addMensagem("sigedMsgSenhaDiferenteDaAtual");
		}
		
		if (!usuario.getVchSenha().equals(confirmaSenha)) {
			msgs.addMensagem("sigedMsgSenhaDiferenteDaNova");
		}
		
		if(msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
		
		this.alterar(usuario, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como par�metro.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - ColaboradorTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void excluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeDelete(usuario);
		this.persistencia.excluir(usuario, usuarioLogado);
	}
	
	/**
	 * Inclui as informa��es contidas no objeto passado como par�metro.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @param usuarioLogado
	 *            - ColaboradorTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado)
			throws RegraNegocioException, BDException {
		this.beforeInsert(usuario);
		this.persistencia.incluir(usuario, usuarioLogado);
	}
	
	/**
	 * Inclui as informa��es contidas no objeto passado como par�metro.
	 *
	 * @param usuario
	 * @param senhaAtual
	 * @param confirmaSenha
	 * @throws RegraNegocioException
	 * @throws BDException - 
	 */
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void incluir(UsuarioTO usuario, UsuarioTO usuarioLogado,
			String confirmaSenha) throws RegraNegocioException, BDException {
		
		if(!usuario.getVchSenha().equals(confirmaSenha)) {
			throw new RegraNegocioException("sigedMsgSenhaDiferenteDaNova");
		}
		
		this.incluir(usuario, usuarioLogado);
	}

	/**
	 * Consulta um objeto com base nas informa��es contidas no objeto passado
	 * como par�metro.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @return UsuarioTO
	 * @throws RegraNegocioException
	 * @throws BDException
	 */
	public UsuarioTO consultar(UsuarioTO usuario) throws BDException {
		return this.persistencia.consultar(usuario);
	}
	
	/**
	 * Retorna a lista de objetos listados com base nas propriedades do
	 * TransferObject passado como par�metro.
	 * 
	 * @param usuario - {@link UsuarioTO} 
	 * @param orderBy - {@link String}
	 * @return {@link List}
	 * @throws RegraNegocioException
	 */
	public List<TransferObject> listar(UsuarioTO usuario) throws BDException{
		if (usuario.getVchLogin() != null || usuario.getVchNome() != null) {
			return this.persistencia.listar(usuario);
		}
		return this.persistencia.listar();		
	}
	
	/**
	 * Lista os militares cadastrados com base nas propriedades do objeto.
	 * 
	 * @param to - TransferObject
	 * @param orderBy - String, par�metro de ordena��o
	 * @return List
	 */
	public List<TransferObject> listar(UsuarioTO to, String orderBy) throws BDException{
		return this.persistencia.listar(to, orderBy);
	}
	
	public List<TransferObject> listar() throws BDException {
		return this.persistencia.listar();
	}
	
	/**
	 * Remove um objeto da sess�o do Hibernate.
	 */
	public void retirarObjetoSessao(UsuarioTO usuario) {
		this.persistencia.retirarObjetoSessao(usuario);
	}
	
	/**
	 * Retorna um usu�rio com base no Login do mesmo.
	 * 
	 * @param userName
	 *            String
	 * @return {@link UsuarioTO}
	 * @throws BDException
	 */
	public UsuarioTO buscarUsuarioPorLogin(String userName) throws BDException {
		return this.persistencia.buscarUsuarioPorLogin(userName);
	}
	
	/**
	 * Verifica se a senha informada � v�lida.
	 *
	 * @param userName - String
	 * @param senha - String
	 * @return boolean
	 */
	public boolean isSenhaValida(String userName, String senha)throws RegraNegocioException {
		
		UsuarioTO usuario = this.persistencia.buscarUsuarioPorLogin(userName);
		
		if (usuario == null) {
			throw new RegraNegocioException("sigedUsuarioNaoConfere");
		}
		
		//verifica se a senha informada e igual � senha do usu�rio
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		if(usuario.getVchSenha() == null || usuario.getVchSenha().isEmpty() || !usuario.getVchSenha().equals(encrypt.encrypt(senha))){
			return false;           
		}
		return true;
	}

	/**
	 * 
	 * Lan�a um RegraNegocioException com a Menssagem.
	 * 
	 * @param MsG
	 * @throws RegraNegocioException
	 */
	@SuppressWarnings("unused")
	private void goMsg(String msg) throws RegraNegocioException {
		MensagemLista msgs = new MensagemLista();
		msgs.addMensagem(msg);
		throw new RegraNegocioException(msgs);
	}

}
