package br.com.flavios.pbpf.negocio.controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.com.flavios.pbpf.negocio.controle.entidade.UsuarioTO;
import core.excecoes.BaseException;
import core.utilitario.Util;

public class PBPFFilter implements Filter, PBPFConstantes {

	private HttpServletRequest hRequest;
	private HttpSession hSession;
	private String path;

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		this.hRequest = (HttpServletRequest) request;
		this.hSession = ((HttpServletRequest) request).getSession();
		this.path = this.hRequest.getServletPath();

		UsuarioTO usuarioLogado = null;

		try {
			// Caso exista sess�o e sessionId associado ao request seja v�lido,
			// recupera o atributo de sess�o USUARIO_LOGADO.
			if (this.hSession != null && this.hRequest.isRequestedSessionIdValid()) {
				usuarioLogado = (UsuarioTO) this.hSession.getAttribute(PBPFConstantes.USUARIO_LOGADO);
			}

			// Se usu�rio logado, aplica o controle de acesso URL. Se n�o,
			// direciona para a p�gina de LOGIN.
			if (usuarioLogado != null) {
				redirecionarAcessoUsuarioLogado(chain, request, response);
			} else {
				if (this.path.equals(URL_MOBILE)) {
					request.getRequestDispatcher(URL_MOBILE).forward(request, response);
				} else {
					request.getRequestDispatcher(URL_LOGIN).forward(request, response);
					//chain.doFilter(request, response);
				}
			}
		} catch (Throwable e) {
			BaseException ex = Util.getBaseException(e);
			ex.printStackTrace();
			request.setAttribute(MSG_ERRO_GERAL, Boolean.TRUE);
			request.setAttribute(MSG_ERRO_GERAL_TXT, e.getMessage());
			if (usuarioLogado != null) {
				request.getRequestDispatcher(URL_DASHBOARD).forward(request, response);
			} else {
				request.getRequestDispatcher(URL_LOGIN).forward(request, response);
			}
		}
	}

	private void redirecionarAcessoUsuarioLogado(FilterChain chain, ServletRequest request, ServletResponse response) throws Throwable {
		// rotinas de direcionamento para usu�rio logado
		if (this.path.equals(URL_LOGIN) || this.path.equals(URL_INIT)) {
			request.getRequestDispatcher(URL_DASHBOARD).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

}
