package br.com.scode.util;

import java.io.IOException;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.PostMethod;

public class ComunicadorComScode {

	public void enviarConteudo(NameValuePair[] parts) throws HttpException, IOException {
		String url = ParametrosUtil.get(ParametrosUtil.SCODE_URL_IMPORTA);
		System.out.println(conectarComScode(url, parts, "UTF-8"));
	}

	public void retornarConteudo(NameValuePair[] parts) throws HttpException, IOException, Throwable {
		String url = ParametrosUtil.get(ParametrosUtil.SCODE_URL_EXPORTA);
		System.out.println(conectarComScode(url, parts, "ISO-8859-1"));
		new Integracao().gravaDadosDaCotacao(RequisicaoRetorno.ajustarRetorno(conectarComScode(url, parts, "ISO-8859-1")));
		//RequisicaoRetorno.ajustarRetorno(conectarComScode(url, parts));
	}

	/**
	 * Centraliza a comunicação com o SCODE, com isso a configuração do proxy e a forma
	 * de comunicar servirá tanto para enviar conteúdo, quanto para retornar o conteúdo
	 * 
	 * @param url Endereço da requisição
	 * @param parts
	 * @return
	 * @throws HttpException
	 * @throws IOException
	 */
	private String conectarComScode(String url, NameValuePair[] parts, String encoding) throws HttpException, IOException {
		HttpClient httpclient = new HttpClient();
		httpclient.getParams().setParameter("http.protocol.content-charset", encoding);
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);

		PostMethod post = new PostMethod(url);
		post.setRequestBody(parts);

		String proxyIp = ParametrosUtil.get(ParametrosUtil.SCODE_PROXY_IP);
		String proxyPorta = ParametrosUtil.get(ParametrosUtil.SCODE_PROXY_PORTA);
		String proxyUsuario = ParametrosUtil.get(ParametrosUtil.SCODE_PROXY_USUARIO);
		String proxySenha = ParametrosUtil.get(ParametrosUtil.SCODE_PROXY_SENHA);

		if (proxyIp != null && !proxyIp.trim().isEmpty()) {

			// mas não tem autenticação
			if (proxyUsuario.equals("")) {
				httpclient.getHostConfiguration().setProxy(proxyIp, new Integer(proxyPorta));
			} else {
				Credentials credentials = new UsernamePasswordCredentials(proxyUsuario, proxySenha);
				AuthScope authScope = new AuthScope(proxyIp, Integer.parseInt(proxyPorta));
				httpclient.getState().setProxyCredentials(authScope, credentials);
			}

		}

		int status = httpclient.executeMethod(post);
		if (status == HttpStatus.SC_OK) {
			return post.getResponseBodyAsString();
		} else {
			throw new RuntimeException("Houve uma falha na comunicação com o endereço " + url + " > " + status);
		}
	}

}
