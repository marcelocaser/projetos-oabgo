package br.org.oabgo.saeo.web.controle.bean;

import java.net.UnknownHostException;

import jcifs.UniAddress;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbException;
import jcifs.smb.SmbSession;
import br.org.oabgo.saeo.negocio.controle.SAEOConstantes;
import br.org.oabgo.saeo.negocio.controle.entidade.UsuarioTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Usuario;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumSecurity;
import br.org.oabgo.saeo.web.controle.SAEOManagedBean;
import core.excecoes.RegraNegocioException;

public class LoginBean extends SAEOManagedBean {

	private static final long serialVersionUID = -8450962067450811894L;
	
	private UsuarioTO 	usuario;
	private String 		senha;
	private String 		login;
	private String 		idioma;
	private boolean 	mensagem;

	public String logon(){
		try{
			rotinaAutenticacaoSingleSignOn(getLogin(), getSenha());
			
			return dashBoard();
			
		} catch (Exception e) {
			return tratarExcecao(e);
		}
	}
	
	private void rotinaAutenticacaoSingleSignOn(String userName, String password)throws RegraNegocioException{
		Usuario usuarioService = getSAEOBusinessFactory().createUsuario();
//		Sistema negocioSistema = getSAEOBusinessFactory().createSistema();
		
//		SistemaTO sistema = negocioSistema.consultarSistemaContexto(getRequest().getContextPath());
		try {
			//Recupera o controlador de Dom�nio, baseado no IP do Servidor AD
			UniAddress myDomainController = UniAddress.getByName(SAEOConstantes.ENDERECO_WINS_OAB, Boolean.TRUE);
			// Instancia o objeto que encapsula as credenciais que ser�o verificadas junto ao AD.
			NtlmPasswordAuthentication myCreds = new NtlmPasswordAuthentication(SAEOConstantes.DOMINIO_OAB, userName, password);
			try {
				// Tenta logar no AD usando as credenciais passadas por par�metro.
				SmbSession.logon(myDomainController, myCreds);
				
				//consulta um usu�rio com base no login e na senha
				this.setUsuario(usuarioService.buscarUsuarioPorLogin(userName));
				if(this.getUsuario() != null){
					//associa o usu�rio a sess�o atual.
					setParamSession(SAEOConstantes.USUARIO_LOGADO, usuario);
					
				}else{
					removeParamSession(SAEOEnumSecurity.USUARIO_LOGADO);
					throw new RegraNegocioException("Usu�rio " + userName + " n�o encontrado.");
				}
				
			} catch (SmbException e) {
				switch (e.getNtStatus()) {
					case SmbException.NT_STATUS_ACCESS_DENIED: 			throw new RegraNegocioException("Falha de logon: Acesso negado.");
					case SmbException.NT_STATUS_WRONG_PASSWORD: 		throw new RegraNegocioException("Falha de logon: Senha incorreta. Aten��o: informar a senha errada diversas vezes pode causar o bloqueio do seu usu�rio de rede.");
					case SmbException.NT_STATUS_LOGON_FAILURE: 			throw new RegraNegocioException("Falha de logon: Usu�rio n�o encontrado ou senha inv�lida.");
					case SmbException.NT_STATUS_ACCOUNT_RESTRICTION: 	throw new RegraNegocioException("Falha de logon: Conta com restri��o.");
					case SmbException.NT_STATUS_INVALID_LOGON_HOURS: 	throw new RegraNegocioException("Falha de logon: Fora da faixa de hor�rio permitida.");
					case SmbException.NT_STATUS_INVALID_WORKSTATION: 	throw new RegraNegocioException("Falha de logon: Esta��o de trabalho inv�lida.");
					case SmbException.NT_STATUS_PASSWORD_EXPIRED:    	throw new RegraNegocioException("Falha de logon: Senha expirada.");
					case SmbException.NT_STATUS_ACCOUNT_DISABLED: 		throw new RegraNegocioException("Falha de logon: Conta desabilitada.");
					case SmbException.NT_STATUS_ACCOUNT_LOCKED_OUT: 	throw new RegraNegocioException("Falha de logon: Conta bloqueada.");
					default: throw new RegraNegocioException("Erro ao autenticar no Active Directory: "+e.getMessage());
				}
			}
		} catch (UnknownHostException e) {
			throw new RegraNegocioException("Host 192.168.10.1 n�o encontrado. Certifique-se que o IP informado no campo |IP Active Directory(AD)| nas configura��es do sistema est� correto.");
		}
	}
	
	public String dashBoard(){
		return JSF_DEFAULT;
	}
	
	public void logoff(){
		try{
			//realiza o logoff do usu�rio.
			removerAcesso();
		}catch (Exception e) {
			tratarExcecao(e);
		}
	}

	public UsuarioTO getUsuario() {
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
