package br.org.oabgo.siged.web.controle.bean;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import br.org.oabgo.siged.negocio.controle.SIGEDConstantes;
import br.org.oabgo.siged.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumSecurity;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumTipoMensagem;
import br.org.oabgo.siged.web.controle.SIGEDMail;
import br.org.oabgo.siged.web.controle.SIGEDManagedBean;
import core.criptografia.Encrypter;
import core.criptografia.EncrypterImpl;
import core.excecoes.RegraNegocioException;
import core.mensagem.MensagemLista;

public class LoginBean extends SIGEDManagedBean {

	private static final long serialVersionUID = -8450962067450811894L;
	
	private UsuarioTO 	usuario;
	private String 		senha;
	private String 		login;
	private String 		idioma;
	private boolean 	mensagem;

	public String logon(){
		try{
			
			if (this.getSenha() == null) {
				this.recuperacaoDeSenha();
				return null;
			}
			
			this.rotinaAutenticacaoSingleSignOn(this.getLogin(), this.getSenha());
			
			return dashBoard();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	public String logonAnonimo() {
		try {
			
			this.setLogin("anonimo");
			this.setSenha("anonimo");
			
			this.rotinaAutenticacaoSingleSignOn(this.getLogin(), this.getSenha());
			
			return dashBoard();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	private void rotinaAutenticacaoSingleSignOn(String userName, String password)throws RegraNegocioException{
		
		Usuario usuarioService = getSIGEDBusinessFactory().createUsuario();
		
		if (!usuarioService.isSenhaValida(userName, password)) {
			throw new RegraNegocioException("sigedUsuarioSenhaNaoConfere");
		} else {
			
			//consulta um usuário com base no login
			this.setUsuario(usuarioService.buscarUsuarioPorLogin(userName));
			
			if(this.getUsuario() != null){
			//associa o usuário a sessão atual.
			setParamSession(SIGEDConstantes.USUARIO_LOGADO, usuario);
			}else{
				removeParamSession(SIGEDEnumSecurity.USUARIO_LOGADO);
				throw new RegraNegocioException("sigedUsuarioNaoEncontrado");
			}
		}
	}
	
	public String dashBoard(){
		return JSF_DEFAULT;
	}
	
	public void logoff(){
		try{
			//realiza o logoff do usuário.
			removerAcesso();
		}catch (Exception e) {
			tratarExcecao(e);
		}
	}
	
	public void recuperacaoDeSenha() throws RegraNegocioException {
				
		MensagemLista msgs = new MensagemLista();
		
		Usuario usuarioService = getSIGEDBusinessFactory().createUsuario();
		
		EncrypterImpl encrypt = new EncrypterImpl(Encrypter.KEY_ENCRYPT);
		
		this.setUsuario(usuarioService.buscarUsuarioPorLogin(this.getLogin()));
		
		if (this.getUsuario().getVchLogin() == null) {
			throw new RegraNegocioException("sigedUsuarioNaoEncontrado");
		} else {
			this.getUsuario().setVchSenha(encrypt.decrypt(this.getUsuario().getVchSenha()));
			SIGEDMail mail = new SIGEDMail();
			try {
				mail.sendMail(this.getUsuario());
			} catch (AddressException e) {
				throw new RegraNegocioException(e.getMessage());
			} catch (MessagingException e) {
				throw new RegraNegocioException(e.getMessage());
			} catch (UnsupportedEncodingException e) {
				throw new RegraNegocioException(e.getMessage());
			}
		}
		msgs.addMensagem("sigedMsdEmailEnviado");
		msgs.addMensagem(this.getUsuario().getVchEmail());
		setMessage(msgs, SIGEDEnumTipoMensagem.SUCESSO);
		
	}

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
