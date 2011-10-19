package core.security;

public class SecurityProviderRegistry {
	
	private static SecurityProvider provider;
	
	public static void registerSecurityProvider( SecurityProvider provider ) {
		SecurityProviderRegistry.provider	= provider;
	}
	
	public static SecurityProvider getSecurityProvider() {
		return provider;
	}

}
