package br.com.flavios.pbpf.negocio.controle;

public interface PBPFConstantes {

	/**
	 * Nome padrão do sistema
	 */
	public static final String PBPF = "PBPF";
	
	/**
	 * Nome padrão do servidor/dominio
	 */
	public static final String ENDERECO_WINS_FLAVIOS = "10.62.3.20";
	public static final String DOMINIO_FLAVIOS = "FLAVIOS.BR";
	
	/**
	 * Variáveis para controle de acesso
	 */
	public static final String MSG_CTR_ACESSO_RECURSO = "Usuário não possui privilégios para acessar este recurso, entre em contato com o Administrador do sistema.";
	public static final String MSG_ERRO_GERAL_TXT = "txtMsgErroGlobal";
	public static final String MSG_ERRO_GERAL = "msgErroFilterPBPF";
	public static final String USUARIO_LOGADO = "usuarioLogado";

	/**
	 * Chave das mensagens
	 */
	public static final String KEY_MSG_SUCESSO_INCLUIR = "msgSucessoIncluir";
	public static final String KEY_MSG_SUCESSO_ALTERAR = "msgSucessoAlterar";
	public static final String KEY_MSG_SUCESSO_EXCLUIR = "msgSucessoExcluir";
	
	/**
	 * URL'S PBPF
	 */
	public static final String URL_INIT = "/ProjetoBasicoPrimeFaces";
	public static final String URL_LOGIN = "/xhtml/principal/login.jsf";
	public static final String URL_DASHBOARD = "/xhtml/principal/paginaPrincipal.jsf";
	public static final String URL_MOBILE = "/xhtml/mobile/paginaPrincipal.jsf";
	
	/**
	 * DEFINIÇÕES SERVIDOR
	 */
	public static final String PATH_ARQUIVO_ELETRONICO = "H://Orgaos//DocumentosDigitalizados//";
	public static final Integer TAMANHO_DISPONIVEL_MENOR_100_MB = 104857600;
	
	/**
	 * Constantes relacinadas ao mecanismo de integração com Active Directory
	 */
	public static final String JCIFS_UTIL_LOGLEVEL = "jcifs.util.loglevel";
	public static final String LOGLEVEL_VALUE = "3";

	public static final String JCIFS_SMB_CLIENT_RESPONSETIMEOUT = "jcifs.smb.client.responseTimeout";
	public static final String RESPONSETIMEOUT_VALUE = "50000";

	public static final String JCIFS_NETBIOS_RETRYTIMEOUT = "jcifs.netbios.retryTimeout";
	public static final String RETRYTIMEOUT_VALUE = "10000";

	public static final String JCIFS_RESOLVEORDER = "jcifs.resolveOrder";
	public static final String RESOLVEORDER_VALUE = "DNS";

	public static final String JCIFS_SMB_CLIENT_SOTIMEOUT = "jcifs.smb.client.soTimeout";
	public static final String SOTIMEOUT_VALUE = "50000";

	public static final String JCIFS_SMB_CLIENT_DOMAIN = "jcifs.smb.client.domain";
	public static final String JCIFS_NETBIOS_WINS = "jcifs.netbios.wins";
	
	
	/**
	 * DEFINIÇÕES DO JSF 2.0 PARA REDIRECT DAS PÁGINAS 
	 */
	public static final String URL_USUARIO_LISTAR = "/xhtml/usuario/usuarioListar";
}
