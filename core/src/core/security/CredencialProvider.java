package core.security;


public class CredencialProvider {
	
	private static ThreadLocal<Object> credencial	= new ThreadLocal<Object>();
	
	public static void setCredencial( Object credencial ) {
		CredencialProvider.credencial.set(credencial);
	}
	
	public static Object getCredencial() {
		return credencial.get();
	}
}
