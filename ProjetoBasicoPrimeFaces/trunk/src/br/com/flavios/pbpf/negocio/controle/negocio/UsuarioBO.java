package br.com.flavios.pbpf.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.negocio.controle.persistencia.UsuarioPO;
import core.criptografia.Encrypter;
import core.criptografia.EncrypterImpl;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> UsuarioBO.java <br>
 * <b>Descrição:</b>		   <br>
 *
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.negocio.controle.negocio <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 *    Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class UsuarioBO implements Usuario {
	
	@Autowired
	private UsuarioPO persistencia;

	/**
	 * Executa a validação da regra de negócio antes de inserir o objeto.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - Não pode exister dois objetos UsuarioTO com o mesmo
	 *             nome. </b>
	 */
	protected void beforeInsert(TransferObject bean)
			throws RegraNegocioException {
		
		UsuarioTO usuario = (UsuarioTO) bean;
		
		UsuarioTO usuarioTmp = new UsuarioTO();
		
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		//objeto que encapsula as mensagens de regra de negocio
		MensagemLista msgs = new MensagemLista();
		
		//valida login já cadastro
		usuarioTmp.setVchLogin(usuario.getVchLogin());
		
		//verifica se o existe usuario cadastrado com o mesmo login
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("pbpfUsuarioJaCadastrado");
		}
		
		//valida e-mail já cadastro
		usuarioTmp.setVchLogin(new String());
		usuarioTmp.setVchEmail(usuario.getVchEmail());
		
		//verifica se o existe usuario cadastrado com o mesmo email
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("pbpfUsuarioEmailJaCadastrado");
		}
		
		//encriptar senha do usuário antes de incluir.
		usuario.setVchSenha(encrypt.encrypt(usuario.getVchSenha()));
		
		if(msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
		
	}

	/**
	 * Executa a validação da regra de negócio antes de inserir o objeto.
	 * 
	 * @param usuario
	 *            - UsuarioTO
	 * @throws RegraNegocioException
	 * 
	 *             <b># - Não pode exister dois objetos UsuarioTO com o mesmo
	 *             nome </b>
	 */
	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {
		
		UsuarioTO usuario = (UsuarioTO) bean;
		
		UsuarioTO usuarioTmp = new UsuarioTO();
		
		usuarioTmp.setVchEmail(usuario.getVchEmail());
		
		//verifica se o existe usuario cadastrado com o mesmo e-mail
		if(this.persistencia.beforeUpdate(usuario, usuarioTmp)){
			throw new RegraNegocioException("pbpfUsuarioEmailJaCadastrado");
		}
		
	}
	
	protected void beforeDelete(TransferObject bean) throws RegraNegocioException {
		
		UsuarioTO usuario =(UsuarioTO) bean;
		
		//verifica se o usuario não é o único cadastrado.
		if (usuario.getVchLogin().equals("admin")) {
			throw new RegraNegocioException("pbpfMsgUsuarioAdmin");
		}
		
	}

	/**
	 * Persiste as alterações do objeto passado como parâmetro.
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
	 * Persiste as alterações no objeto UsuarioTO.
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
			msgs.addMensagem("pbpfMsgSenhaDiferenteDaAtual");
		}
		
		if (!usuario.getVchSenha().equals(confirmaSenha)) {
			msgs.addMensagem("pbpfMsgSenhaDiferenteDaNova");
		}
		
		if(msgs.getNumeroRegistros() > 0) {
			throw new RegraNegocioException(msgs);
		}
		
		this.alterar(usuario, usuarioLogado);
	}
	
	/**
	 * Exclui o objeto passado como parâmetro.
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
	 * Inclui as informações contidas no objeto passado como parâmetro.
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
	 * Inclui as informações contidas no objeto passado como parâmetro.
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
			throw new RegraNegocioException("pbpfMsgSenhaDiferenteDaNova");
		}
		
		this.incluir(usuario, usuarioLogado);
	}

	/**
	 * Consulta um objeto com base nas informações contidas no objeto passado
	 * como parâmetro.
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
	 * TransferObject passado como parâmetro.
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
	 * @param orderBy - String, parâmetro de ordenação
	 * @return List
	 */
	public List<TransferObject> listar(UsuarioTO to, String orderBy) throws BDException{
		return this.persistencia.listar(to, orderBy);
	}
	
	public List<TransferObject> listar() throws BDException {
		return this.persistencia.listar();
	}
	
	/**
	 * Remove um objeto da sessão do Hibernate.
	 */
	public void retirarObjetoSessao(UsuarioTO usuario) {
		this.persistencia.retirarObjetoSessao(usuario);
	}
	
	/**
	 * Retorna um usuário com base no Login do mesmo.
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
	 * 
	 * Lança um RegraNegocioException com a Menssagem.
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
