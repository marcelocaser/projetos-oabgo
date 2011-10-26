package br.com.scode.util;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class ParametrosUtil {

	public static final String SCODE_URL_IMPORTA = "scode.url_importa";
	public static final String SCODE_URL_EXPORTA = "scode.url_exporta";
	public static final String SCODE_EMPRESA_ID = "scode.empresa_id";
	public static final String SCODE_USUARIO = "scode.usuario";
	public static final String SCODE_SENHA = "scode.senha";
	public static final String SCODE_PROJETO_ID = "scode.projeto_id";
	public static final String SCODE_USUARIO_ID = "scode.usuario_id";
	
	public static final String SCODE_PROXY_IP = "proxy.ip";
	public static final String SCODE_PROXY_PORTA = "proxy.porta";
	public static final String SCODE_PROXY_USUARIO = "proxy.usuario";
	public static final String SCODE_PROXY_SENHA = "proxy.senha";
	
	private static ResourceBundle resource;

	static {
		resource = PropertyResourceBundle.getBundle("scoderm");

		if (resource == null) {
			throw new RuntimeException("Não foi encontrado o arquivo scoderm.properties dentro do classpath");
		}
	}
	
	
	public static String get(String key) {
		return resource.getString(key);
	}

}
