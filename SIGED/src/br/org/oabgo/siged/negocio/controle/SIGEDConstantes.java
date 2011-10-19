package br.org.oabgo.siged.negocio.controle;

public interface SIGEDConstantes {

	/**
	 * Nome padrão do sistema
	 */
	public static final String SIGED = "SIGED";
	
	/**
	 * Variáveis para controle de acesso
	 */
	public static final String MSG_CTR_ACESSO_RECURSO = "Usuário não possui privilégios para acessar este recurso, entre em contato com o Administrador do sistema.";
	public static final String MSG_ERRO_GERAL_TXT = "txtMsgErroGlobal";
	public static final String MSG_ERRO_GERAL = "msgErroFilterSIGED";
	public static final String USUARIO_LOGADO = "usuarioLogado";

	/**
	 * Chave das mensagens
	 */
	public static final String KEY_MSG_SUCESSO_INCLUIR = "msgSucessoIncluir";
	public static final String KEY_MSG_SUCESSO_ALTERAR = "msgSucessoAlterar";
	public static final String KEY_MSG_SUCESSO_EXCLUIR = "msgSucessoExcluir";
	
	/**
	 * URL'S SIGED
	 */
	public static final String URL_INIT = "/siged";
	public static final String URL_LOGIN = "/xhtml/principal/login.jsf";
	public static final String URL_DASHBOARD = "/xhtml/principal/paginaPrincipal.jsf";
	public static final String URL_CADASTRAR_SENHA = "/xhtml/principal/cadastro.jsf";
	
	/**
	 * DEFINIÇÕES SERVIDOR OABGO01
	 */
	public static final String PATH_ARQUIVO_ELETRONICO = "H://Orgaos//DocumentosDigitalizados//";
	public static final Integer TAMANHO_DISPONIVEL_MENOR_100_MB = 104857600;
	
	
	public static final String TIPO_DOCUMENTO_RELATORIO = "1"; //1 - Tipo relatório.
}
