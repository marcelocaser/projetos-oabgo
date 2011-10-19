package core.utilitario;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * <b>Title:</b> Numero.java <br>
 * <b>Description:</b>     <br>
 * <b>Project:</b> core <br>
 * 
 * @author Leoanrdo Peixoto
 * @version $Revision: 1.5 $, $Date: 06/09/2007
 *
 */
public class Numero {
    
    /**
     * Formata n�meros quaisquer com uma m�scara qualquer.
     * 
     * @param numero double - N�mero a ser formatado
     * @param mascara String - M�scara que ser� aplicada ao n�mero
     * @return String
     * @author Leoanrdo Peixoto
     */
    public final String formatar(double numero, String mascara) {
        DecimalFormat formatador = null;

        try {
            formatador = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
        } catch(ClassCastException e) {
            return("");
        }

        formatador.applyPattern(mascara);

        return(formatador.format(numero));
    }
    
    /**
     * Formata n�meros quaisquer com uma m�scara qualquer.
     * 
     * @param numero int - Numero a ser formatado
     * @param mascara String - Mascara que ser� aplicada ao n�mero
     * @return String
     * @author Leoanrdo Peixoto
     */
    public final String formatar(int numero, String mascara) {
        return this.formatar((double) numero, mascara);
    }

    /**
     * Verifica se a string informada no par�metro "valor" �
     * um valor num�rico ou n�o.
     *
     * @param valor String - Valor a ser testado
     * @return boolean - True para se � num�rico e False para se n�o � num�rico
     * 
     */
    public static boolean isNumeric(String valor) {
        try {
            new Double(valor);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
    
	/**
     * Formata valores decimais.
     * @param valor double - valor decimal
     * @param decimais int - quantidade de casas decimais
     * @return String
     * @author Leonardo.Peixoto
     */
    public final String formatarDecimal(double valor, int decimais) {
        DecimalFormat Formatador = null;
        String Padrao = "";

        for(int i = 1; i <= decimais; i++)
            Padrao = Padrao + "0";
        if(decimais > 0)
            Padrao = "." + Padrao;
        try {
            Formatador = (DecimalFormat) NumberFormat.getInstance(Locale.GERMAN);
        } catch(ClassCastException e) {
            return("");
        }
        
        Formatador.applyPattern("##,###,###,###,###,##0" + Padrao);

        return(Formatador.format(valor));
    }
    
    /**
     * valida valores do CPF
     * @param xCPF String
     * @return boolean - <i> true </i> se CPF � v�lido e <i> false </i> se CPF n�o � valido
     * @author Leoanrdo Peixoto
     */
    public static boolean validarCPF(String xCPF) {
        try {
            //Testa se o CPF � v�lido ou n�o
            int d1, d4, xx, nCount, resto, digito1, digito2;
            String Check;
            String Separadores = "/-.";

            d1 = 0;
            d4 = 0;
            xx = 1;

            for(nCount = 0; nCount < xCPF.length() - 2; nCount++) {
                String s_aux = xCPF.substring(nCount, nCount + 1);

                if(Separadores.indexOf(s_aux) == -1) {
                    d1 = d1 + (11 - xx) * Integer.valueOf(s_aux).intValue();
                    d4 = d4 + (12 - xx) * Integer.valueOf(s_aux).intValue();
                    xx++;
                }
            }

            resto = (d1 % 11);

            if(resto < 2) {
                digito1 = 0;
            } else {
                digito1 = 11 - resto;
            }

            d4 = d4 + 2 * digito1;
            resto = (d4 % 11);

            if(resto < 2) {
                digito2 = 0;
            } else {
                digito2 = 11 - resto;
            }

            Check = String.valueOf(digito1) + String.valueOf(digito2);
            String s_aux2 = xCPF.substring(xCPF.length() - 2, xCPF.length());

            if(s_aux2.compareTo(Check) != 0) {
                return false;
            }

            return true;
        } catch(Exception e) {
            return false;
        }
    }
    
    /**
     * Converter n�mero em MegaByte.
     *
     * @param numero
     * @return - 
     * @return Long
     */
    public static Long converteToMegabyte(Long numero) {
    	return numero/1048576;
    }
	
}