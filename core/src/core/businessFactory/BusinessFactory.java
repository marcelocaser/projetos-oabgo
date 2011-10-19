package core.businessFactory;

import java.lang.reflect.Proxy;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import core.security.SecurityProxy;



/**
 * <b>Title:</b> BusinesFactory.java <br>
 * <b>Description:</b> Objeto pai na hierarquia de criação de implementações de negócio. <br>
 * <b>Package:</b> br.org.oabgo.remoto.bus.ctr.negocio <br>
 * <b>Project:</b> Remoto <br>
 * <b>Company:</b> OAB/GO. <br>
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public abstract class BusinessFactory  {

	protected static ApplicationContext applicationContext;
		
	
	/**
	 * Retorna um objeto Proxy com uma instancia da classe de negócio.
	 * 
	 * @param classe - {@link Class}
	 * @param alias - {@link String}
	 * @return {@link Object}
	 */
	@SuppressWarnings("unchecked")
	protected Object createBusinessObject(Class classe, String alias) {
		return Proxy.newProxyInstance(classe.getClassLoader(), classe.getInterfaces(), new SecurityProxy(getApplicationContext().getBean(alias)));
	}
	

	/**
	 * Retorna o valor armazenado na variável applicationContext.
	 * @return {@link WebApplicationContext}
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	/**
	 * Insere um valor ao atributo applicationContext
	 * @param applicationContext - {@link WebApplicationContext}
	 */
	public static void setApplicationContext(ApplicationContext applicationContext) {
		BusinessFactory.applicationContext = applicationContext;
	}
}
