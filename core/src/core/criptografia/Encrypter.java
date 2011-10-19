package core.criptografia;

/**
 * <b>Classe:</b> Encrypter.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.criptografia <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public interface Encrypter {

	public static final String			KEY_ENCRYPT						= "ChaveEncryptOAB_GO";
	
	/**
	 * Método responsável por encriptar a informação passada como parâmetro.
	 *
	 * @param str - String a ser encryptada
	 * @return String
	 */
	public String encrypt(String str);
	
	/**
	 * Método responsável por decryptar a String passada como parâmetro
	 * 
	 * @param str - String a ser decryptada
	 * @return String
	 */
	public String decrypt(String str);
	
}
