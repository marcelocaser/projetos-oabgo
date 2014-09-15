package br.com.cepgo.util;

import java.text.Normalizer;
import java.text.ParseException;
import javax.swing.text.MaskFormatter;

/**
 * <b>Classe:</b> TextUtil.java <br>
 * <b>Descrição:</b> Classe utilitária de texto. <br>
 *
 * <b>Projeto:</b> flavios-core <br>
 * <b>Pacote:</b> br.com.flavios.core.util <br>
 * <b>Empresa:</b> Flávios Calçados e Esportes <br>
 *
 * @author marcelocaser
 * @version Revision: $$ Date: 27/06/2013
 */
public class TextUtil {

    private TextUtil() {
    }

    /**
     * Metódo que irá limitar o tamanho do texto de acordo com um tamanho
     * definido por parâmetro mais seu complemento. Preenchimento a direita.
     *
     *
     * @param texto É o texto que será formatado.
     * @param tamanho É a quantidade de caracteres.
     * @param complemento É o caracter que será completado.
     * @return Texto formatado.
     */
    public static String padRight(String texto, int tamanho, String complemento) {
        String auxTexto = tratarNull(texto);
        //Caso 1: retorna string quando o "texto" já atende o "tamanho".
        if (auxTexto.length() == tamanho) {
            return auxTexto;
        }
        //Caso 2: retorna uma substring quando o "texto" é maior que o "tamanho".
        if (auxTexto.length() > tamanho) {
            return auxTexto.substring(0, tamanho);
        }
        //Caso 3: completa o "texto" com seu "complemento".
        for (int i = auxTexto.length(); i < tamanho; i++) {
            auxTexto = auxTexto + complemento;
        }
        return auxTexto;
    }

    /**
     * Metódo que irá limitar o tamanho do texto de acordo com um tamanho
     * definido por parâmetro mais seu complemento. Preenchimento a esquerda.
     *
     * @param texto É o texto que será formatado.
     * @param tamanho É a quantidade de caracteres.
     * @param complemento É o caracter que será completado.
     * @return Texto formatado.
     */
    public static String padLeft(String texto, int tamanho, String complemento) {
        String auxTexto = tratarNull(texto);
        //Caso 1: retorna string quando o "texto" já atende o "tamanho".
        if (auxTexto.length() == tamanho) {
            return auxTexto;
        }
        //Caso 2: retorna uma substring quando o "texto" é maior que o "tamanho".
        if (auxTexto.length() > tamanho) {
            return auxTexto.substring(0, tamanho);
        }
        //Caso 3: completa o "texto" com seu "complemento".
        for (int i = tratarNull(texto).length(); i < tamanho; i++) {
            auxTexto = complemento + auxTexto;
        }
        return auxTexto;
    }

    /**
     * Tratar atributo quanto o mesmo é nulo.
     *
     * @param atributo É o atributo que será tratado.
     * @return Valor vazio.
     */
    public static String tratarNull(String atributo) {
        if (atributo == null || atributo.equals("null")) {
            atributo = "";
        }
        return atributo;
    }

    /**
     * Método que formata o texto usando a mascara passada.
     *
     * @param texto O texto a ser formatado.
     * @param mascara A máscara a ser usada.
     * @return O texto formatado.
     * @throws ParseException caso ocorra erro.
     */
    public static String formataTexto(String texto, String mascara) throws ParseException {
        MaskFormatter mf = new MaskFormatter(mascara);
        mf.setValueContainsLiteralCharacters(false);
        return mf.valueToString(texto);
    }

    /**
     * Metodo que formata o texto.
     *
     * @param texto o texto a ser formatado.
     * @param caracter o caracter que sera repetido.
     * @param tamanho o tamanho total do texto de resposta.
     * @param direita a direcao onde colocar os caracteres.
     * @return o texto formatado.
     */
    public static String formataTexto(String texto, String caracter, int tamanho, boolean direita) {
        StringBuilder sb = new StringBuilder();
        int fim = tamanho - texto.length();
        for (int i = 0; i < fim; i++) {
            sb.append(caracter);
        }
        return direita ? texto + sb.toString() : sb.toString() + texto;
    }

    /**
     * Método que normaliza os caracteres removendo os acentos.
     *
     * @param texto É o texto acentuado.
     * @return Texto sem acento.
     */
    public static String removeAcento(String texto) {
        CharSequence cs = new StringBuilder(texto == null ? "" : texto);
        return Normalizer.normalize(cs, Normalizer.Form.NFKD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Método que retorna a representação hexadecimal da impressora zebra.
     *
     * @param texto Texto que será passado para Hexadecimal.
     * @return Representação em Hexadecimal.
     */
    public static String getHexadecimalSequenceForZebra(String texto) {
        if (texto == null || texto.length() == 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder("");
        for (Character c : texto.toCharArray()) {
            builder.append("_").append(Integer.toHexString((int) c));
        }
        String retorno = builder.toString();
        return retorno;
    }
}
