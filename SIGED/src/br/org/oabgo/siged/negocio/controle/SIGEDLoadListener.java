package br.org.oabgo.siged.negocio.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import core.businessFactory.BusinessFactory;
import core.utilitario.Util;

public class SIGEDLoadListener implements ServletContextListener {

	
	/**
	 * Metodo executado no STARTUP da aplicacao.
	 * @param contextEvent - ServletContextEvent
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		try{
			//carrega as interfaces de negocios do SPRING
			if(BusinessFactory.getApplicationContext() == null){
				BusinessFactory.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(contextEvent.getServletContext()));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			Util.getBaseException(e);
		}
	}
	

	/**
	 * Metodo executado no DESTROY da aplicacao.
	 * @param contextEvent - ServletContextEvent
	 */
	public void contextDestroyed(ServletContextEvent contextEvent) {
		try{

		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
