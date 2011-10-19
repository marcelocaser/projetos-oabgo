package br.org.oabgo.saeo.negocio.controle;

public interface SAEOConstantes {

	/**
	 * Nome padrão do sistema
	 */
	public static final String SAEO = "SAEO";

	/**
	 * Nome padrão do servidor/dominio
	 */
	public static final String ENDERECO_WINS_OAB = "192.168.10.1";
	public static final String DOMINIO_OAB = "OAB-GO";

	/**
	 * Variáveis para controle de acesso
	 */
	public static final String MSG_ERRO_GERAL_TXT = "txtMsgErroGlobal";
	public static final String MSG_ERRO_GERAL = "msgErroFilterSATI";
	public static final String USUARIO_LOGADO = "usuarioLogado";

	/**
	 * Chave das mensagens
	 */
	public static final String KEY_MSG_SUCESSO_INCLUIR = "msgSucessoIncluir";
	public static final String KEY_MSG_SUCESSO_ALTERAR = "msgSucessoAlterar";
	public static final String KEY_MSG_SUCESSO_EXCLUIR = "msgSucessoExcluir";

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
	 * URL'S SATI
	 */
	public static final String URL_INIT = "/saeo";
	public static final String URL_LOGIN = "/xhtml/principal/login.jsf";
	public static final String URL_DASHBOARD = "/xhtml/principal/paginaPrincipal.jsf";
}
