package br.org.oabgo.saeo.web.controle;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.org.oabgo.saeo.negocio.controle.SAEOBusinessFactory;
import br.org.oabgo.saeo.negocio.controle.entidade.SistemaTO;
import br.org.oabgo.saeo.negocio.controle.negocio.interfaces.Sistema;
import br.org.oabgo.saeo.negocio.enumerador.SAEOEnumSecurity;
import core.jsf.FacesUtil;

public class SAEOSecurityUtil {

	protected static SistemaTO buscarSistemaServletPath(FacesContext contexto){
		Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
		SistemaTO sistema 		= negocioSistema.consultarSistemaContexto(FacesUtil.getRequest(contexto).getContextPath());
		negocioSistema.inicializarObjeto(sistema);
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}
	
	protected static SistemaTO buscarSistemaServletPath(String servletPath){
		Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
		SistemaTO sistema 		= negocioSistema.consultarSistemaContexto(servletPath);
		negocioSistema.inicializarObjeto(sistema);
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}

	protected static SistemaTO buscarSistemaPrincipal(){
		Sistema negocioSistema = SAEOBusinessFactory.getInstance().createSistema();
		SistemaTO sistema = new SistemaTO();
		sistema.setBitSistemaPrincipal(Boolean.TRUE);
		sistema = negocioSistema.buscarObjeto(sistema);
		negocioSistema.inicializarObjeto(sistema);
		negocioSistema.retirarObjetoSessao(sistema);
		return sistema;
	}
	
	protected static String getPaginaURL(String urlDestino){
		String pagina = urlDestino.substring(urlDestino.lastIndexOf('/')+1, urlDestino.length());
		pagina = pagina.substring(0, pagina.indexOf('.'));
		pagina = pagina+".xhtml";
		return pagina;
	}

	protected static String getPaginaURL(HttpServletRequest request){
		String urlDestino = getServletPath(request);
		String pagina = urlDestino.substring(urlDestino.lastIndexOf('/')+1, urlDestino.length());
		pagina = pagina.substring(0, pagina.indexOf('.'));
		pagina = pagina+".xhtml";
		return pagina;
	}
	
	protected static Object getParamSession(FacesContext contexto, SAEOEnumSecurity key){
		return FacesUtil.getParamSession(contexto, key.toString());
	}

	protected static Object getParamRequest(FacesContext contexto, SAEOEnumSecurity key){
		return FacesUtil.getParamRequest(contexto, key.toString());
	}
	
	protected static void removeParamSession(FacesContext contexto, SAEOEnumSecurity key){
		FacesUtil.removeParamSession(contexto, key.toString());
	}
	
	protected static String getPaginaViewId(FacesContext contexto){
		String url = contexto.getViewRoot().getViewId();
		return url.substring(url.lastIndexOf('/')+1, url.length());
	}

	protected static String getServletPath(FacesContext contexto){
		try{
			return getServletPath(FacesUtil.getRequest(contexto));
		}catch (NullPointerException e) {
			return "/URL_SAEO";
		}
	}

	protected static String getServletPath(HttpServletRequest request){
		try{
			return request.getServletPath();
		}catch (NullPointerException e) {
			return "/URL_SAEO";
		}
	}

	protected static void setParamSession(FacesContext contexto, SAEOEnumSecurity key, Object parameter){
		FacesUtil.setParamSession(contexto, key.toString(), parameter);
	}

	protected static void setParamRequest(FacesContext contexto, SAEOEnumSecurity key, Object parameter){
		FacesUtil.setParamRequest(contexto, key.toString(), parameter);
	}
	
	public static SistemaTO buscarSistemaAutenticadoSession(FacesContext contexto){
		return (SistemaTO)SAEOSecurityUtil.getParamSession(contexto, SAEOEnumSecurity.SISTEMA_CONTROLADO);
	}
}
