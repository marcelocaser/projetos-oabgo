package core.resource;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * <b>Classe:</b> ResourceServiceUtil.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.resource <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class ResourceServiceUtil {
	
	/**
	 * 
	 * @param defaultObject
	 * @return ClassLoader
	 */
	protected static ClassLoader getCurrentClassLoader(Object defaultObject) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = defaultObject.getClass().getClassLoader();
		}
		return loader;
	}

	/**
	 * 
	 * @param key
	 * @param params
	 * @return String
	 */
	public static String getMessageResourceString(String key, Object params[]) {
		String text = null;
		FacesContext context = FacesContext.getCurrentInstance();
		
		Locale locale = getLocale(context);
		
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");;
		try {
			text = bundle.getString(key);
		} catch (Exception e) {
			text = key;
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return text;
	}
	
	
	private static Locale getLocale(FacesContext context){
		if(context.getViewRoot() != null && context.getViewRoot().getLocale() != null){
			return context.getViewRoot().getLocale();
		}else{
			return new Locale("pt", "pt_br");
		}
	}
	/**
	 * 
	 * @param key
	 * @param params
	 * @return FacesMessage
	 */
	public static FacesMessage getMessageResource(String key, Object params[]) {
		String text = null;

		FacesContext context = FacesContext.getCurrentInstance();		
		String bundleName = context.getApplication().getMessageBundle();
		Locale locale = context.getViewRoot().getLocale();
		
		ResourceBundle bundle = ResourceBundle.getBundle(bundleName, locale, getCurrentClassLoader(params));
		try {
			text = bundle.getString(key);
		} catch (MissingResourceException e) {
			text = "?? key " + key + " não encontrada ??";
		}
		if (params != null) {
			MessageFormat mf = new MessageFormat(text, locale);
			text = mf.format(params, new StringBuffer(), null).toString();
		}
		return new FacesMessage(text, null);
	}	
}