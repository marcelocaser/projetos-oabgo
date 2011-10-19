package core.criptografia;

/**
 * <b>Classe:</b> Encrypter.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.criptografia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public interface Encrypter {

	public static final String			KEY_ENCRYPT						= "ChaveEncryptOAB_GO";
	
	/**
	 * M�todo respons�vel por encriptar a informa��o passada como par�metro.
	 *
	 * @param str - String a ser encryptada
	 * @return String
	 */
	public String encrypt(String str);
	
	/**
	 * M�todo respons�vel por decryptar a String passada como par�metro
	 * 
	 * @param str - String a ser decryptada
	 * @return String
	 */
	public String decrypt(String str);
	
}
