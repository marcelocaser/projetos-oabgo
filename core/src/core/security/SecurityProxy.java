package core.security;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import core.excecoes.AcessoException;
import core.excecoes.RegraNegocioException;



/**
 * <b>Title:</b> SecurityProxy.java <br>
 * <b>Description:</b> Objeto pai na hierarquia de criação de implementações de negócio. <br>
 * <b>Package:</b> br.org.oabgo.remoto.bus.ctr.negocio <br>
 * <b>Project:</b> Remoto <br>
 * <b>Company:</b> OAB/GO. <br>
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 *
 */
public class SecurityProxy implements InvocationHandler {
	
	private Object business;
	
	public SecurityProxy(Object business) {
		this.business = business;
	}
	
	/**
	 * Proxy que executa o método chamada disparando a exceção de controle de acesso caso o
	 * usuário não tenha privilégio para acessar o recurso.
	 * 
	 * @param proxy 
	 * @param method - Método Chamado
	 * @param args - Argumentos do método
	 * 
	 * @throws CtrAcessoException
	 * @throws RegraNegocioException
	 */
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable, AcessoException, RegraNegocioException {
		SecurityProvider provider	= SecurityProviderRegistry.getSecurityProvider();
		Object credencial			= CredencialProvider.getCredencial();
		try{
			provider.checkPermission(credencial, business, method, args);
		}catch (NullPointerException e) {
			System.out.println("WARN: Proxy de segurança esta nulo, nova instancia criada. ");
			SecurityProviderRegistry.registerSecurityProvider(new CoreSecurityProxy());
		}
		
		//executa o método de negócia via REFLECTION após a validação do controle de acesso
		return method.invoke(business, args);
	}

}
