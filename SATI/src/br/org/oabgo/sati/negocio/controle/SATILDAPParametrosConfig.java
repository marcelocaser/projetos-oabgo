package br.org.oabgo.sati.negocio.controle;

public class SATILDAPParametrosConfig {
	
   
    public static final String INITIAL_CTX = "com.sun.jndi.ldap.LdapCtxFactory";

    public static final String SERVIDOR = "ldap://oabgo01.oabgo.org.br:389";
    
    public static final String AUTENTICACAO = "simple";

    public static final String SEARCHBASE = "dc=oabgo, dc=org, dc=br";
 
    public static final String BASE_DN = "ou=UsuariosOABGO, dc=oabgo, dc=org, dc=br";
        
    public static final String USER_DN = "gti@oabgo.org.br";   
    
    public static final String USER_PW = "tecnologia";
    
    public static final String displayName = "displayName";
    
    public static final String givenName = "givenName";
    
    public static final String scriptPath = "scriptPath";
    
    public static final String filter = "(&(objectCategory=Person)&(!(userAccountControl:1.2.840.113556.1.4.803:=2)))";
    
    public static final String cn = "cn";
    
    public static final String sn = "sn";
    
    public static final String userPrincipalName = "userPrincipalName";
    
    public static final String sAMAccountName = "sAMAccountName";
    
    public static final String name = "name";
    
    public static final String memberOf = "memberOf";
    
    public static final String whenCreated = "whenCreated";
    
    public static final String adminCount = "adminCount";
    
}