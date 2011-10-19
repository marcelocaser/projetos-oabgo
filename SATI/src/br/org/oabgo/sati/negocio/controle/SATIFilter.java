package br.org.oabgo.sati.negocio.controle;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import br.org.oabgo.sati.negocio.controle.entidade.UsuarioTO;
import core.excecoes.BaseException;
import core.utilitario.Util;

public class SATIFilter implements Filter, SATIConstantes {

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
			// Caso exista sessão e sessionId associado ao request seja válido,
			// recupera o atributo de sessão USUARIO_LOGADO.
			if (this.hSession != null
					&& this.hRequest.isRequestedSessionIdValid()) {
				usuarioLogado = (UsuarioTO) this.hSession
						.getAttribute(SATIConstantes.USUARIO_LOGADO);
			}

			// Se usuário logado, aplica o controle de acesso URL. Se não,
			// direciona para a página de LOGIN.
			if (usuarioLogado != null) {
				redirecionarAcessoUsuarioLogado(chain, request, response);
			} else {
				//chain.doFilter(request, response);
				request.getRequestDispatcher(URL_LOGIN).forward(request,
						response);
			}
		} catch (Throwable e) {
			BaseException ex = Util.getBaseException(e);
			ex.printStackTrace();
			request.setAttribute(MSG_ERRO_GERAL, Boolean.TRUE);
			request.setAttribute(MSG_ERRO_GERAL_TXT, e.getMessage());
			if (usuarioLogado != null) {
				request.getRequestDispatcher(URL_DASHBOARD).forward(request,
						response);
			} else {
				request.getRequestDispatcher(URL_LOGIN).forward(request,
						response);
			}
		}
	}

	private void redirecionarAcessoUsuarioLogado(FilterChain chain,
			ServletRequest request, ServletResponse response) throws Throwable {
		// rotinas de direcionamento para usuário logado
		if (this.path.equals(URL_LOGIN) || this.path.equals(URL_INIT)) {
			request.getRequestDispatcher(URL_DASHBOARD).forward(request,
					response);
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void destroy() {
	}

}
