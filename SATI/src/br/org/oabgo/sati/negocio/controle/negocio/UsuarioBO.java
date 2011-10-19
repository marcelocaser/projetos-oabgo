package br.org.oabgo.sati.negocio.controle.negocio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.org.oabgo.sati.negocio.controle.entidade.PerfilAcessoTO;
import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.sati.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.sati.negocio.controle.persistencia.UsuarioPO;
import core.dao.TransferObject;
import core.excecoes.BDException;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

/**
 * <b>Classe:</b> UsuarioBO.java <br>
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
public class UsuarioBO implements Usuario {

	private static final long serialVersionUID = 6163656505600586094L;
	
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

		//objeto que encapsula as mensagens de regra de negocio
		MensagemLista msgs = new MensagemLista();
		
		usuarioTmp.setVchLogin(usuario.getVchLogin());
		
		//verifica se o existe usuario cadastrado com o mesmo login
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("satiMsgUsuarioJaCadastrado");
		}
		
		//valida e-mail já cadastro
		usuarioTmp.setVchLogin(new String());
		usuarioTmp.setVchEmail(usuario.getVchEmail());
		
		//verifica se o existe usuario cadastrado com o mesmo email
		if(this.persistencia.beforeInsert(usuarioTmp)){
			msgs.addMensagem("satiMsgEmailJaCadastrado");
		}
		
		//define como false para usuario do SATI
		usuario.setBitUsuarioSIGED(Boolean.FALSE);
		usuario.setBitMembro(Boolean.FALSE);
		
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
	 *             nome. </b>
	 */
	protected void beforeUpdate(TransferObject bean)
			throws RegraNegocioException {

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
		beforeUpdate(usuario);
		this.persistencia.alterar(usuario, usuarioLogado);
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
	 * Retorna o perfil de acesso do usuário.
	 *
	 * @param usuario - {@link UsuarioTO}
	 * @param perfilAcesso - {@link PerfilAcessoTO}
	 * @return PerfilAcessoTO
	 */
	public PerfilAcessoTO retornaPerfilAcesso(UsuarioTO usuario, PerfilAcessoTO perfilAcesso)throws BDException{
		return this.persistencia.retornaPerfilAcesso(usuario, perfilAcesso);
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
	public List<TransferObject> listar(UsuarioTO usuario, String orderBy) throws BDException{
		return this.persistencia.listar(usuario, orderBy);
	}

	/**
	 * Lista os militares cadastrados com base nas propriedades do objeto.
	 *
	 * @return List
	 * @throws BDException - 
	 */
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
