package br.org.oabgo.saeo.negocio.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import core.businessFactory.BusinessFactory;
import core.utilitario.Util;

public class SAEOLoadListener implements ServletContextListener {

	
	/**
	 * Metodo executado no STARTUP da aplicacao
	 * @param contextEvent - ServletContextEvent
	 */
	public void contextInitialized(ServletContextEvent contextEvent) {
		try{
			//carrega as interfaces de negocios do SPRING
			if(BusinessFactory.getApplicationContext() == null){
				BusinessFactory.setApplicationContext(WebApplicationContextUtils.getRequiredWebApplicationContext(contextEvent.getServletContext()));
			}
			
			setupSingleSignOn();
		}catch (Exception e) {
			e.printStackTrace();
			Util.getBaseException(e);
		}
	}
	
	/**
	 * Realiza o setup para o ambiente de Single Sign On
	 * Todas as variáveis setadas serão utilizadas pela API jcifs ao realizar o login de um usuário.
	 */
	private void setupSingleSignOn() {
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_UTIL_LOGLEVEL,              SAEOConstantes.LOGLEVEL_VALUE);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_SMB_CLIENT_DOMAIN,          SAEOConstantes.DOMINIO_OAB);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_NETBIOS_WINS,               SAEOConstantes.ENDERECO_WINS_OAB);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_SMB_CLIENT_RESPONSETIMEOUT, SAEOConstantes.RESPONSETIMEOUT_VALUE);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_NETBIOS_RETRYTIMEOUT,       SAEOConstantes.RETRYTIMEOUT_VALUE);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_RESOLVEORDER,               SAEOConstantes.RESOLVEORDER_VALUE);
		jcifs.Config.setProperty(SAEOConstantes.JCIFS_SMB_CLIENT_SOTIMEOUT,       SAEOConstantes.SOTIMEOUT_VALUE);
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
