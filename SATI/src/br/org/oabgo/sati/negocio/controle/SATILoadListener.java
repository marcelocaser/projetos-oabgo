package br.org.oabgo.sati.negocio.controle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.support.WebApplicationContextUtils;

import core.businessFactory.BusinessFactory;
import core.utilitario.Util;

/**
 * <b>Classe:</b> SATILoadListener.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public class SATILoadListener implements ServletContextListener {

	
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
		jcifs.Config.setProperty(SATIConstantes.JCIFS_UTIL_LOGLEVEL,              SATIConstantes.LOGLEVEL_VALUE);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_SMB_CLIENT_DOMAIN,          SATIConstantes.DOMINIO_OAB);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_NETBIOS_WINS,               SATIConstantes.ENDERECO_WINS_OAB);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_SMB_CLIENT_RESPONSETIMEOUT, SATIConstantes.RESPONSETIMEOUT_VALUE);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_NETBIOS_RETRYTIMEOUT,       SATIConstantes.RETRYTIMEOUT_VALUE);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_RESOLVEORDER,               SATIConstantes.RESOLVEORDER_VALUE);
		jcifs.Config.setProperty(SATIConstantes.JCIFS_SMB_CLIENT_SOTIMEOUT,       SATIConstantes.SOTIMEOUT_VALUE);
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
