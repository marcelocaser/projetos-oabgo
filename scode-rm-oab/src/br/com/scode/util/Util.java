package br.com.scode.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <b>Classe:</b> Util.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> scode-rm-oab <br>
 * <b>Pacote:</b> br.com.scode.util <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author marcelo
 * @version Revision: $ Date: $
 */
public class Util {

	/**
	 * Formata String conforme parâmetro passado pelo pMask. apenas o caractere
	 * "#" serão substituídos pelo texto original
	 * 
	 * Ex.: #####-### => 74000-000
	 * 
	 * @param pMask
	 *            - String
	 * @param pValue
	 *            - String
	 * @return String
	 */
	public static String formataStringMascara(String pMask, String pValue) {
		String retorno = new String();
		// Formata valor com base no parametro pMask
		for (int i = 0; i < pValue.length(); i++) {
			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
		}
		retorno = pMask.replaceAll("#", "");

		// Caso o valor seja menor que a m�scara, retorna o conteudo original
		if (retorno.length() < pMask.length())
			retorno = pValue;

		return retorno;
	}

	/**
	 * Validação de CNPJ.
	 *
	 * @param cnpj
	 * @return - 
	 * @return boolean
	 */
	public static boolean CNPJ(String cnpj) {
		int soma = 0, dig;
		
		if (cnpj.length() != 14) {
			return false;
		}
		
		String calc = cnpj.substring(0, 12);

		char[] chr_cnpj = cnpj.toCharArray();

		/* Primeira parte */
		for (int i = 0; i < 4; i++) {
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
				soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
			}
		}
		for (int i = 0; i < 8; i++) {
			if (chr_cnpj[i + 4] - 48 >= 0 && chr_cnpj[i + 4] - 48 <= 9) {
				soma += (chr_cnpj[i + 4] - 48) * (10 - (i + 1));
			}
		}
		dig = 11 - (soma % 11);

		calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		/* Segunda parte */
		soma = 0;
		for (int i = 0; i < 5; i++) {
			if (chr_cnpj[i] - 48 >= 0 && chr_cnpj[i] - 48 <= 9) {
				soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
			}
		}

		for (int i = 0; i < 8; i++) {
			if (chr_cnpj[i + 5] - 48 >= 0 && chr_cnpj[i + 5] - 48 <= 9) {
				soma += (chr_cnpj[i + 5] - 48) * (10 - (i + 1));
			}
		}

		dig = 11 - (soma % 11);
		calc += (dig == 10 || dig == 11) ? "0" : Integer.toString(dig);

		return cnpj.equals(calc);
	}
	
	/**
	 * Retorna null se a String for null ou vazia.
	 * Caso não, retorna seu próprio valor. 
	 *
	 * @param valor
	 * @return - 
	 * @return String
	 */
	public static String stringVazia(String valor) {

		if (valor.equals("null") || valor.trim().isEmpty()) {
			return valor = null;
		}
		return valor.trim();
	}
	
    /**
     * Formata números quaisquer com uma máscara qualquer.
     * 
     * @param numero double - Número a ser formatado
     * @param mascara String - Máscara que será aplicada ao número
     * @return String
     * @author Leoanrdo Peixoto
     */
    public static final String formatar(double numero, String mascara) {
        DecimalFormat formatador = null;

        try {
            formatador = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
        } catch(ClassCastException e) {
            return("");
        }

        formatador.applyPattern(mascara);

        return(formatador.format(numero));
    }

}
