package br.com.flavios.pbpf.negocio.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import core.businessFactory.BusinessFactory;
import core.utilitario.Util;

public class PBPFLoadListener implements ServletContextListener {

	
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
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_UTIL_LOGLEVEL,              PBPFConstantes.LOGLEVEL_VALUE);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_SMB_CLIENT_DOMAIN,          PBPFConstantes.DOMINIO_FLAVIOS);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_NETBIOS_WINS,               PBPFConstantes.ENDERECO_WINS_FLAVIOS);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_SMB_CLIENT_RESPONSETIMEOUT, PBPFConstantes.RESPONSETIMEOUT_VALUE);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_NETBIOS_RETRYTIMEOUT,       PBPFConstantes.RETRYTIMEOUT_VALUE);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_RESOLVEORDER,               PBPFConstantes.RESOLVEORDER_VALUE);
		jcifs.Config.setProperty(PBPFConstantes.JCIFS_SMB_CLIENT_SOTIMEOUT,       PBPFConstantes.SOTIMEOUT_VALUE);
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
