package br.com.flavios.pbpf.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.negocio.controle.persistencia.UsuarioPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> UsuarioBO.java <br>
 * <b>Descri��o:</b>		   <br>
 *
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.negocio.controle.negocio <br>
 * <b>Empresa:</b> Fl�vio�s Cal�ados e Esportes <br>
 * 
 *    Copyright (c) 2011 Fl�vio�s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@Service
public class UsuarioBO implements Usuario {
	
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
		
	}
	
	protected void beforeDelete(TransferObject bean) throws RegraNegocioException {
		
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
		if (usuario.getQdfCod() != null || usuario.getQdfNomFunc() != null) {
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
