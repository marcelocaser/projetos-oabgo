package br.com.flavios.pbpf.negocio.controle;

public class PBPFLDAPUsuario {

	private String displayName;
	private String givenName;
	private String memberOf;
	private String scriptPath;
	private String userPrincipalName;
	private String whenCreated;
	private Boolean adminCount;

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getMemberOf() {
		return memberOf;
	}

	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}

	public String getScriptPath() {
		return scriptPath;
	}

	public void setScriptPath(String scriptPath) {
		this.scriptPath = scriptPath;
	}

	public String getUserPrincipalName() {
		return userPrincipalName;
	}

	public void setUserPrincipalName(String userPrincipalName) {
		this.userPrincipalName = userPrincipalName;
	}

	public String getWhenCreated() {
		return whenCreated;
	}

	public void setWhenCreated(String whenCreated) {
		this.whenCreated = whenCreated;
	}

	public Boolean getAdminCount() {
		return adminCount;
	}

	public void setAdminCount(Boolean adminCount) {
		this.adminCount = adminCount;
	}

}
