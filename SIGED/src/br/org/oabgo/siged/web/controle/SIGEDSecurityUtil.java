package br.org.oabgo.siged.web.controle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.org.oabgo.siged.negocio.controle.SIGEDBusinessFactory;
import br.org.oabgo.siged.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.siged.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.siged.negocio.enumerador.SIGEDEnumSecurity;
import core.jsf.FacesUtil;

public class SIGEDSecurityUtil {

	/**
	 * Consulta o sistema com base no ServletPath.
	 */
	protected static SistemaTO buscarSistemaServletPath(FacesContext contexto){
		//busca pela aplicação requisitada com base no contexto
		Sistema negocioSistema = SIGEDBusinessFactory.getInstance().createSistema();
		//verifica se existe sistema com o contexto requisitado
		SistemaTO sistema 		= negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());
		//inicializa as propriedades
		negocioSistema.inicializarObjeto(sistema);
		//remove da sessão do Spring		
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}
	
	/**
	 * Consulta o sistema com base no ServletPath.
	 */
	protected static SistemaTO buscarSistemaServletPath(String servletPath){
		//busca pela aplicação requisitada com base no contexto
		Sistema negocioSistema = SIGEDBusinessFactory.getInstance().createSistema();
		//verifica se existe sistema com o contexto requisitado
		SistemaTO sistema 		= negocioSistema.consultarSistemaContexto(servletPath);
		//inicializa as propriedades
		negocioSistema.inicializarObjeto(sistema);
		//remove da sessão do Spring		
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}

	/**
	 * Consulta o sistema principal gerenciado pelo ADM
	 */
	protected static SistemaTO buscarSistemaPrincipal(){
		//busca o sistema principal gerenciado pelo ADM
		Sistema negocioSistema = SIGEDBusinessFactory.getInstance().createSistema();
		SistemaTO sistema = new SistemaTO();
		sistema.setBitSistemaPrincipal(Boolean.TRUE);
		sistema = negocioSistema.buscarObjeto(sistema);
		//inicializa as propriedades
		negocioSistema.inicializarObjeto(sistema);
		//remove da sessão do Spring		
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}
	
	/**
	 * Retorna o nome da página que estamos tentando acessar.
	 */
	protected static String getPaginaURL(String urlDestino){
		String pagina = urlDestino.substring(urlDestino.lastIndexOf('/')+1, urlDestino.length());
		pagina = pagina.substring(0, pagina.indexOf('.'));
		pagina = pagina+".xhtml";
		return pagina;
	}

	/**
	 * Retorna o nome da página que estamos tentando acessar.
	 */
	protected static String getPaginaURL(HttpServletRequest request){
		String urlDestino = getServletPath(request);
		String pagina = urlDestino.substring(urlDestino.lastIndexOf('/')+1, urlDestino.length());
		pagina = pagina.substring(0, pagina.indexOf('.'));
		pagina = pagina+".xhtml";
		return pagina;
	}
	
	/**
	 * Retorna os parametros armazenados na session pela engine do ADM.
	 */
	protected static Object getParamSession(FacesContext contexto, SIGEDEnumSecurity key){
		return FacesUtil.getParamSession(contexto, key.toString());
	}

	/**
	 * Retorna os parametros armazenados na session pela engine do ADM.
	 */
	protected static Object getParamRequest(FacesContext contexto, SIGEDEnumSecurity key){
		return FacesUtil.getParamRequest(contexto, key.toString());
	}
	
	/**
	 * Remove o parametro armazenado na chave passada como parametro da session.
	 */
	protected static void removeParamSession(FacesContext contexto, SIGEDEnumSecurity key){
		FacesUtil.removeParamSession(contexto, key.toString());
	}
	
	/**
	 * Retorna o nome da página ViewId.
	 */
	protected static String getPaginaViewId(FacesContext contexto){
		String url = contexto.getViewRoot().getViewId();
		return url.substring(url.lastIndexOf('/')+1, url.length());
	}

	/**
	 * Retorna o ViewId.
	 */
	protected static String getServletPath(FacesContext contexto){
		try{
			return getServletPath(FacesUtil.getRequest(contexto));
		}catch (NullPointerException e) {
			return "/URL_SIGED";
		}
	}

	/**
	 * Retorna o ViewId.
	 */
	protected static String getServletPath(HttpServletRequest request){
		try{
			return request.getServletPath();
		}catch (NullPointerException e) {
			return "/URL_SIGED";
		}
	}

	/**
	 * Insere uma parametro no SESSION.
	 */
	protected static void setParamSession(FacesContext contexto, SIGEDEnumSecurity key, Object parameter){
		FacesUtil.setParamSession(contexto, key.toString(), parameter);
	}

	/**
	 * Insere uma parametro no REQUEST.
	 */
	protected static void setParamRequest(FacesContext contexto, SIGEDEnumSecurity key, Object parameter){
		FacesUtil.setParamRequest(contexto, key.toString(), parameter);
	}
	
	/**
	 * Busca o sistema requisitado pelo usuário, busca por uma instancia de SISTEMA armazenada no SESSION
	 * se o objeto existir verifica se o sistema requisitado é o mesmo armazenado no session, se for um
	 * sistema diferente retorna NULL.
	 */
	
	public static SistemaTO buscarSistemaAutenticadoSession(FacesContext contexto){
		return (SistemaTO)SIGEDSecurityUtil.getParamSession(contexto, SIGEDEnumSecurity.SISTEMA_CONTROLADO);
	}
}
