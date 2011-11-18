package br.com.flavios.pbpf.web.controle.bean;

import java.net.UnknownHostException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbSession;
import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import br.com.flavios.pbpf.negocio.controle.negocio.interfaces.Usuario;
import br.com.flavios.pbpf.web.controle.PBPFManagedBean;
import core.excecoes.RegraNegocioException;

/**
 * <b>Classe:</b> LoginBean.java <br>
 * <b>Descrição:</b>		   <br>
 *
 * <b>Projeto:</b> ProjetoBasicoPrimeFaces <br>
 * <b>Pacote:</b> br.com.flavios.pbpf.web.controle.bean <br>
 * <b>Empresa:</b> Flávio´s Calçados e Esportes <br>
 * 
 *    Copyright (c) 2011 Flávio´s - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@ManagedBean
@RequestScoped
public class LoginBean extends PBPFManagedBean {

	private static final long serialVersionUID = -8450962067450811894L;
	
	private UsuarioTO 	usuario;
	private String 		senha;
	private String 		login;
	private String 		idioma;
	private boolean 	mensagem;

	public String logon(){
		try{
			
			if (this.getSenha() == null) {
				//this.recuperacaoDeSenha();
				return null;
			}
			
			this.rotinaAutenticacaoSingleSignOn(this.getLogin(), this.getSenha());
			
			return dashBoard();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String logonMobile(){
		try{
			
			this.rotinaAutenticacaoSingleSignOn(this.getLogin(), this.getSenha());
			
			return dashBoardMobile();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	private void rotinaAutenticacaoSingleSignOn(String userName, String password)throws RegraNegocioException{
		
		Usuario usuarioService = getPBPFBusinessFactory().createUsuario();
		
		try {
			//Recupera o controlador de Domínio, baseado no IP do Servidor AD
			UniAddress myDomainController = UniAddress.getByName(ENDERECO_WINS_FLAVIOS, Boolean.TRUE);
			// Instancia o objeto que encapsula as credenciais que serão verificadas junto ao AD.
			NtlmPasswordAuthentication myCreds = new NtlmPasswordAuthentication(DOMINIO_FLAVIOS, userName, password);
			try {
				// Tenta logar no AD usando as credenciais passadas por parâmetro.
				SmbSession.logon(myDomainController, myCreds);
				
				//consulta um usuário com base no login e na senha
				this.setUsuario(usuarioService.buscarUsuarioPorLogin(userName));
				if(this.getUsuario().getQdfCod() != null){
					//associa o usuário a sessão atual.
					setParamSession(USUARIO_LOGADO, usuario);
					
				}else{
					removeParamSession(USUARIO_LOGADO);
					throw new RegraNegocioException("Usuário " + userName + " não encontrado.");
				}
				
			} catch (SmbException e) {
				switch (e.getNtStatus()) {
					case SmbException.NT_STATUS_ACCESS_DENIED: 			throw new RegraNegocioException("Falha de logon: Acesso negado.");
					case SmbException.NT_STATUS_WRONG_PASSWORD: 		throw new RegraNegocioException("Falha de logon: Senha incorreta. Atenção: informar a senha errada diversas vezes pode causar o bloqueio do seu usuário de rede.");
					case SmbException.NT_STATUS_LOGON_FAILURE: 			throw new RegraNegocioException("Falha de logon: Usuário não encontrado ou senha inválida.");
					case SmbException.NT_STATUS_ACCOUNT_RESTRICTION: 	throw new RegraNegocioException("Falha de logon: Conta com restrição.");
					case SmbException.NT_STATUS_INVALID_LOGON_HOURS: 	throw new RegraNegocioException("Falha de logon: Fora da faixa de horário permitida.");
					case SmbException.NT_STATUS_INVALID_WORKSTATION: 	throw new RegraNegocioException("Falha de logon: Estação de trabalho inválida.");
					case SmbException.NT_STATUS_PASSWORD_EXPIRED:    	throw new RegraNegocioException("Falha de logon: Senha expirada.");
					case SmbException.NT_STATUS_ACCOUNT_DISABLED: 		throw new RegraNegocioException("Falha de logon: Conta desabilitada.");
					case SmbException.NT_STATUS_ACCOUNT_LOCKED_OUT: 	throw new RegraNegocioException("Falha de logon: Conta bloqueada.");
					default: throw new RegraNegocioException("Erro ao autenticar no Active Directory: "+e.getMessage());
				}
			}
		} catch (UnknownHostException e) {
			throw new RegraNegocioException("Host " + ENDERECO_WINS_FLAVIOS + " não encontrado. Certifique-se que o IP informado no campo |IP Active Directory(AD)| nas configurações do sistema está correto.");
		}
	}
	
	public String dashBoard(){
		return JSF_DEFAULT;
	}
	
	public String dashBoardMobile(){
		return JSF_DEFAULT_MOBILE;
	}
	
	public void logoff(){
		try{
			//realiza o logoff do usuário.
			removerAcesso();
		}catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	public void logoffMobile(){
		try{
			//realiza o logoff do usuário.
			removerAcessoMobile();
		}catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	/*public void recuperacaoDeSenha() throws RegraNegocioException {
				
		MensagemLista msgs = new MensagemLista();
		
		Usuario usuarioService = getPBPFBusinessFactory().createUsuario();
		
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		this.setUsuario(usuarioService.buscarUsuarioPorLogin(this.getLogin()));
		
		if (this.getUsuario().getVchLogin() == null) {
			throw new RegraNegocioException("sigedUsuarioNaoEncontrado");
		} else {
			this.getUsuario().setVchSenha(encrypt.decrypt(this.getUsuario().getVchSenha()));
		}
		msgs.addMensagem("sigedMsdEmailEnviado");
		msgs.addMensagem(this.getUsuario().getVchEmail());
		setMessage(msgs, PBPFEnumTipoMensagem.SUCESSO);
		
	}*/

	public UsuarioTO getUsuario() {
		if (usuario == null) {
			usuario = new UsuarioTO();
		}
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public boolean isMensagem() {
		return mensagem;
	}

	public void setMensagem(boolean mensagem) {
		this.mensagem = mensagem;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}
