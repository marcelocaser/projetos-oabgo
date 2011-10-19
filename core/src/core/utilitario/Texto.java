package core.utilitario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 * <b>Classe:</b> Texto.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.utilitario <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class Texto extends Util {
    public static final String CRLF = "\r\n";

    /**
     * 
     * @param Valor
     * @param Procurar
     * @param Novo
     * @return String
     * 
     */
    private final String Substituir(String Valor, String Procurar, String Novo) {
        String resp = "";
        int pos = 1;
        resp = Valor;
        pos = this.PosicaoString(resp, Procurar, pos, true);
        while (pos > 0) {
            resp = resp.substring(0, pos - 1) +
                Novo +
                resp.substring(pos + Procurar.length() - 1);
            pos = this.PosicaoString(resp, Procurar, pos+Novo.length(), true);
        }
        return resp;
    }
    
    /**
     * 
     * @param Valor
     * @param Procurar
     * @param Novo
     * @return String
     * 
     */
    public final String substituir(String Valor, String Procurar, String Novo) {
    	return this.Substituir(Valor, Procurar, Novo);
    }

    /**
     * 
     * @param Origem
     * @param Procurar
     * @param Inicio
     * @param SensivelCaso
     * @return int
     * 
     */
    private final int PosicaoString(String Origem, String Procurar, int Inicio,
                                   boolean SensivelCaso) {
        int i = 0, pos = Procurar.length(), tam = Origem.length();
        String Aux1 = "";
        String AuxOrigem = "";
        String AuxProcurar = "";
        if (SensivelCaso) {
            AuxOrigem = Origem;
            AuxProcurar = Procurar;
        }
        else {
            AuxOrigem = Origem.toUpperCase();
            AuxProcurar = Procurar.toUpperCase();
        }
        pos += Inicio - 2;
        for (i = Inicio - 1; i < tam && pos < tam && !Aux1.equals(AuxProcurar);
             i++) {
            pos += 1;
            Aux1 = AuxOrigem.substring(i, pos);
        }
        if (Aux1.equals(AuxProcurar))
            return i;
        else
            return 0;
    }

    /**
     * Implementa a facilidade Localizar.
     * @return int
     * 
     */
    public final int posicaoString(String Origem, String Procurar, int Inicio) {
        return this.PosicaoString(Origem, Procurar, Inicio, false);
    }

    /**
     * Implementa a facilidade Localizar.
     * @return int
     * 
     */
    public final int PosicaoString(String Origem, String Procurar) {
        return this.PosicaoString(Origem, Procurar, 1, false);
    }

    /**
     * Define o Alinhamento ao Centro
     * @return String
     * 
     */
    public final String alinhaC(String Valor, int Tamanho, String Caracter) {
        String AuxValor = Valor;
        for (int i = Valor.length(); i < Tamanho; i++) {
            if ( (AuxValor.length() % 2) == 0)
                AuxValor = Caracter + AuxValor;
            else
                AuxValor = AuxValor + Caracter;
        }
        return AuxValor;
    }

    /**
     * Define o Alinhamento ao Centro
     * @return String
     * 
     */
    public final String alinhaC(String Valor, int Tamanho) {
        return this.alinhaC(Valor, Tamanho, " ");
    }

    /**
     * Define o Alinhamento ao Centro
     * @return String
     * 
     */
    public final String alinhaD(String Valor, int Tamanho, String Caracter) {
        String AuxValor = Valor;
        for (int i = Valor.length(); i < Tamanho; i++)
            AuxValor = Caracter + AuxValor;
        return AuxValor;
    }

    /**
     * Define o Alinhamento à Direita
     * @return String
     * 
     */
    public final String alinhaD(String Valor, int Tamanho) {
        return this.alinhaD(Valor, Tamanho, " ");
    }

    /**
     * Define o Alinhamento à Esquerda
     */
    public final String AlinhaE(String Valor, int Tamanho, String Caracter) {
        String AuxValor = Valor;
        for (int i = Valor.length(); i < Tamanho; i++)
            AuxValor = AuxValor + Caracter;
        return AuxValor;
    }

    /**
     * Define o Alinhamento à Esquerda
     */
    public final String AlinhaE(String Valor, int Tamanho) {
        return this.AlinhaE(Valor, Tamanho, " ");
    }

    /**
     * Obtem quantas vezes ocorre um String
     */
    public final int Ocorrencias(String Valor, String Procurar) {
        int resp = 0;
        int pos = 1;
        pos = this.PosicaoString(Valor, Procurar, pos, true);
        while (pos > 0) {
            resp++;
            pos = this.PosicaoString(Valor, Procurar, pos + 1, true);
        }
        return resp;
    }

    /**
     * Tratamento de Nulo
     * 
     * @return String
     * 
     */
    public final String trataNull(String strOriginal, String strSubs) {
        if (strOriginal == null) {
            return strSubs;
        }
        else {
            return strOriginal;
        }
    }

    /**
     * Tratamento de Nulo
     * 
     * @return String
     * 
     */
    public final String trataNull(String strOriginal, String strSubs,
                                  boolean bRetTrim) {
        if (strOriginal == null) {
            return strSubs;
        }
        else {
            if (bRetTrim) {
                return strOriginal.trim();
            }
            else {
                return strOriginal;
            }
        }
    }

    /**
     * Remove a acentuação de um texto informado.
     * 
     * @param str - String texto a ser avaliado
     * @return String - Texto sem acentuação
     * 
     */
    public String removeAcentuacao(String str) {
        //Substitui os caracteres acentuados por caracteres nao acentuados

        char aux[] = new char[256];
        //matriz de caracteres onde o texto eh manipulado

        int i; //contador

        aux = str.toCharArray();
        //matriz recebe o texto

        for (i = 0; i < str.length(); i++) {
            //percorre o texto, caracter a caracter
            switch (aux[i]) {
                //Maiúsculo
                case 'É':
                    aux[i] = 'E'; //É -> E
                    break;
                case 'Ê':
                    aux[i] = 'E'; //Ê -> E
                    break;
                case 'Ë':
                    aux[i] = 'E'; //Ë -> E
                    break;
                case 'Á':
                    aux[i] = 'A'; //Á -> A
                    break;
                case 'À':
                    aux[i] = 'A'; //À -> A
                    break;
                case 'Â':
                    aux[i] = 'A'; //Â -> A
                    break;
                case 'Ã':
                    aux[i] = 'A'; //Ã -> A
                    break;
                case 'Ä':
                    aux[i] = 'A'; //Ä -> A
                    break;
                case 'Ç':
                    aux[i] = 'C'; //Ç -> C
                    break;
                case 'Í':
                    aux[i] = 'I'; //Í -> I
                    break;
                case 'Ó':
                    aux[i] = 'O'; //Ó -> O
                    break;
                case 'Õ':
                    aux[i] = 'O'; //Õ -> O
                    break;
                case 'Ô':
                    aux[i] = 'O'; //Ô -> O
                    break;
                case 'Ö':
                    aux[i] = 'O'; //Ö -> O
                    break;
                case 'Ú':
                    aux[i] = 'U'; //Ú -> U
                    break;
                case 'Ü':
                    aux[i] = 'U'; //Ü -> U
                    break;
                case 'Ñ':
                    aux[i] = 'N'; //Ñ -> N
                //Minúsculo
                case 'é':
                    aux[i] = 'e'; //É -> E
                    break;
                case 'ê':
                    aux[i] = 'e'; //Ê -> E
                    break;
                case 'ë':
                    aux[i] = 'e'; //Ë -> E
                    break;
                case 'á':
                    aux[i] = 'a'; //Á -> A
                    break;
                case 'à':
                    aux[i] = 'a'; //À -> A
                    break;
                case 'â':
                    aux[i] = 'a'; //Â -> A
                    break;
                case 'ã':
                    aux[i] = 'a'; //Ã -> A
                    break;
                case 'ä':
                    aux[i] = 'a'; //Ä -> A
                    break;
                case 'ç':
                    aux[i] = 'c'; //Ç -> C
                    break;
                case 'í':
                    aux[i] = 'i'; //Í -> I
                    break;
                case 'ó':
                    aux[i] = 'o'; //Ó -> O
                    break;
                case 'õ':
                    aux[i] = 'o'; //Õ -> O
                    break;
                case 'o':
                    aux[i] = 'o'; //Ô -> O
                    break;
                case 'ö':
                    aux[i] = 'o'; //Ö -> O
                    break;
                case 'ú':
                    aux[i] = 'u'; //Ú -> U
                    break;
                case 'ü':
                    aux[i] = 'u'; //Ü -> U
                    break;
                case 'ñ':
                    aux[i] = 'n'; //Ñ -> N
                    break;
            }
        }
        str = String.copyValueOf(aux).trim();
        //o string recebe o texto sem acentuacao

        return str;
    } //removeAcentuacao

    /**
     * Remove caracteres que não sejam alfanumericos ou espaços
     * @param str - String Texto a ser avaliado
     * @return String - Texto sem caracteres que não sejam alfanumericos ou espaços
     */
    public String removeEstranhos(String str) {
        //Elimina os caracteres que NAO sejam alfanumericos ou espacos

        char[] foncmp = new char[256];
        //matriz de caracteres que armazena o texto original

        char[] fonaux = new char[256];
        //matriz de caracteres que armazena o texto modificado

        int i, j, //contadores
            first; //indica se exitem espacos em branco antes do primeiro
        //caracter: se 1 -> existem, se 0 -> nao existem

        j = 0;
        first = 1;
        fonaux = str.toCharArray();
        //matriz de caracteres recebe o texto

        for (i = 0; i < 256; i++)
            foncmp[i] = ' ';
            //branqueia a matriz de caracteres

        for (i = 0; i < str.length(); i++) {
            //percorre o texto, caracter a caracter

            //elimina os caracteres que nao forem alfanumericos ou espacos
            if ( ( (fonaux[i] >= 'A') &&
                  (fonaux[i] <= 'Z')) ||
                ( (fonaux[i] >= 'a') &&
                 (fonaux[i] <= 'z')) ||
                ( (fonaux[i] >= '0') &&
                 (fonaux[i] <= '9')) ||
                (fonaux[i] == '&') ||
                (fonaux[i] == '_') ||
                ( (fonaux[i] == ' ') && first == 0)) {
                foncmp[j] = fonaux[i];
                j++;
                first = 0;
            } //if
        } //for
        str = String.valueOf(foncmp);
        //string recebe o texto da matriz de caracteres

        return str.trim();
    } //removeEstranhos

    /**
     * Remove todos os conectores entre as palavras, inclusive letras.
     * @param str - String Texto com conectores
     * @return String - Texto sem conectores
     */
    public String removeConectores(String str) {
        String conectores[] = {
            "DA", "DE", "DI", "DO", "DU", "SA", "S/A", "SA.",
            "DAS", "DOS", "S.A", "S.A.", "S/A.", "LTDA"};
        boolean conector = false;
        String strAux = "";
        String palavra = "";
        Vector<String> vetStr = strToVector(str);
        for (int i = 0; i < vetStr.size(); i++) {
            palavra = vetStr.elementAt(i).toString();
            if (palavra.length() > 1) {
                /*Se palavra for maior que 01 caracter*/
                conector = false;
                for (int j = 0; j < conectores.length; j++) {
                    /*Compara conectores*/
                    if (palavra.equals(conectores[j])) {
                        conector = true;
                        break;
                    }
                }
                if (!conector) { /*Se palavra não é um conector*/
                    strAux = strAux + " " + palavra;
                }
            }
        }
        return strAux.trim();
    }

    /**
     * Converte String para Vetor
     * @param str - String Texto a ser convertido
     * @return Vector - Vetor contendo as palavras do texto
     */
    public Vector<String> strToVector(String str) {
        //armazena o texto de um string em um vetor onde
        //cada palavra do texto ocupa uma posicao do vetor

        str = str.trim();

        char[] fonaux = new char[256];
        //matriz de caracteres que armazena o texto completo

        char[] foncmp = new char[256];
        //matriz de caracteres que armazena cada palavra

        Vector<String> component = new Vector<String>();
        //vetor que armazena o texto

        int i, j, //contadores
            pos, //posicao da matriz
            rep, //indica se eh espaco em branco repetido
            first; //indica se eh o primeiro caracter

        first = 1;
        pos = 0;
        rep = 0;

        fonaux = str.toCharArray();
        //matriz de caracteres recebe o texto

        for (j = 0; j < 256; j++)
            foncmp[j] = ' ';
            //branqueia matriz de caracteres

        for (i = 0; i < str.length(); i++) {
            //percorre o texto, caracter a caracter

            //se encontrar um espaco e nao for o primeiro caracter,
            //armazena a palavra no vetor
            if ( (fonaux[i] == ' ') && (first != 1)) {
                if (rep == 0) {
                    component.addElement(String.copyValueOf(foncmp).trim());
                    pos = 0;
                    rep = 1;
                    for (j = 0; j < 256; j++)
                        foncmp[j] = ' ';
                } //if
            } //if

            //forma a palavra, letra a letra, antes de envia-la a uma
            //posicao do vetor
            else {
                foncmp[pos] = fonaux[i];
                first = 0;
                pos++;
                rep = 0;
            } //else
        } //for

        if (foncmp[0] != ' ')
            component.addElement(String.copyValueOf(foncmp).trim());

        return component;
    } //strToVector

    /**
     * Retira os acentos de um nome e o ceonverte para maiúsculo
     * @param nome - String nome a ser convertido
     * @return String - Nome sem acentos e em Maiúsculo
     */
    public String retiraAcentos(String nome) {
        String Acentos =
            "áàãââÁÀÃÂéêÉÊíÍóõôÓÔÕúüÚÜçÇabcdefghijklmnopqrstuvxwyz";
        String Traducao =
            "AAAAAAAAAEEEEIIOOOOOOUUUUCCABCDEFGHIJKLMNOPQRSTUVXWYZ";
        int Posic = 0, Carac = 0;
        String TempLog = "";

        for (int i = 0; i < nome.length(); i++) {
            Carac = nome.charAt(i);
            Posic = Acentos.indexOf(Carac);
            if (Posic > -1)
                TempLog += Traducao.charAt(Posic);
            else
                TempLog += nome.charAt(i);
        }
        return (TempLog.trim());
    }

    /**
     * Completa com ou remove espaços em branco.
     * @param texto - String string a ser formatada.
     * @param tamanho - int tamanho que a string deve ter.
     * @return String - String com o tamanho pedido.
     */
    public final String formatarTamanho(String texto, int tamanho) {
        if (texto.length() >= tamanho) {
            return texto.substring(0, tamanho);
        }
        else {
            return this.AlinhaE(texto, tamanho, " ");
        }
    }

    /**
     * Formata texto com caracteres especiais para o formato Xml.
     * @param s - String a ser formatado.
     * @return String - string gerado.
     */

    public static synchronized String encodeXml(String s) {
        if (s == null)
            return "";
        String encoded_str = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '&')
                encoded_str += "&amp;";
            else if (s.charAt(i) == '\"')
                encoded_str += "&quot;";
            else if (s.charAt(i) == '>')
                encoded_str += "&gt;";
            else if (s.charAt(i) == '<')
                encoded_str += "&lt;";
            else
                encoded_str += s.charAt(i);
        }
        return encoded_str;
    }

    /**
     * Quebra um texto em String em várias linhas, de acordo com o tamano de coluna,
     * permitindo limitar o número de linhas, e também especificar uma linha de preenchimento
     * caso a quantidade não seja atingida.
     * 
     * @param str - String a ser formatado.
     * @param lin - int quantidade de linhas. Zero para qualquer quantidade.
     * @param col - int quantidade máxima de caracteres por linha.
     * @param linhaVazia - String conteúdo das linhas que não foram preenchidas.
     * @return ArrayList Linhas geradas.
     */
    @SuppressWarnings("unchecked")
    public static ArrayList<String> quebrarLinha(String str, int lin, int col,
                                         String linhaVazia) {

        ArrayList objArray = new ArrayList();
        HashMap linha;

        int ini = 0;
        int fim = 0;
        int l = 0;
        int pos = 0;
        for (; pos >= 0; ) {
            pos = str.indexOf(CRLF, ini);
            if (pos < 0)
                fim = str.length() - 1;
            else
                fim = pos - 1;

            int io = ini;
            int fo = fim - 1;
            for (; fo != fim; ) {
                fo = io + col - 1;
                if (fo > fim) { // ultima vez
                    fo = fim;
                }
                if (fo >= io) {
                    l += 1;

                    if ( (l > lin) && (lin > 0)) { // limite de linhas, sair
                        fo = fim;
                        pos = -1;
                    }
                    else {
                        linha = new HashMap();
                        linha.put("linha",
                                  Texto.encodeXml(str.substring(io, fo + 1)));
                        objArray.add(linha);
                    }
                }
                io = fo + 1;
            }
            ini = pos + 2;
        }

        for (; l < lin; l++) { // completar linhas restantes
            linha = new HashMap();
            linha.put("linha", linhaVazia);
            objArray.add(linha);
        }

        return objArray;

    }

    /**
     * Remove a formatação com caracteres que não sejam alfanuméricos.
     * @param valor String
     * @return String
     */
    public static String removerFormatacao(String valor) {
        return Texto.tratarCampoNulo(valor, "").replaceAll("[^\\d]", "");
    }

    /**
     * Trata o valor nulo.
     * @param valor String
     * @param valorPadrao String
     * @return String
     */
    public static String tratarCampoNulo(String valor, String valorPadrao) {
        return valor == null || valor.equals("null") || valor.isEmpty() ? valorPadrao : valor.trim();
    }
    
    /**
     * Verifica se todos os caracteres passados no parâmetro "str"
     * são letras de "A" a "Z" ou "a" a "z".
     * @param valor String
     * @return boolean
     * @author Diego Miranda
     */
    public static boolean eLetra(String valor) {
        for (int i = 0; i <= valor.length() - 1; i++) {
            //Transforma sempre em maiúscula primeiro
            int c = valor.toUpperCase().charAt(i);
            //Se não estiver entre "A" e "Z"
            if (c < 65 || c > 90) {
                return false;
            }
        }
        return true;
    }
    
	/**
	 * Formata String conforme parâmetro passado pelo pMask.
	 * apenas o caractere # será substituído pelo texto original
	 * 
	 * Ex.: #####-### => 74000-000
	 *
	 * @param pMask - String
	 * @param pValue - String
	 * @return String
	 */
	public static String formataMascara(String pMask, String pValue){
		String retorno = new String();
		// Formata valor com base no marametro pMask 
 		for(int i = 0; i < pValue.length(); i++){
 			pMask = pMask.replaceFirst("#", pValue.substring(i, i + 1));
 		}
 		retorno = pMask.replaceAll("#", "");
 		
 		//Caso o valor seja menor que a máscara, retorna o conteudo original
 		if (retorno.length() < pMask.length())
 			retorno = pValue;
 			
 		return retorno;
 	}
	
	/**
	 * Este método recebe um Long e o converte para uma String com máscara de CPF
	 * 
	 * @param obj - Object
	 * @return String - CPF convertido
	 */
	public static String formatarCPF(Object obj) {
		String CPF = "";
		if(obj != null && obj instanceof Long){
			Long cpfLongType = (Long)obj;
			CPF = cpfLongType.toString();
		}else if(obj != null && obj instanceof String){
			CPF = (String)obj;
		}
		if(CPF != null && CPF.length() < 11){
			// Ex.: Este CPF 00176881166 no banco é assim: 176881166
			int comprimento = CPF.length();
			int complemento = 11 - comprimento;
			String digitosZero = "";
			for(int i = 0; i < complemento; i++){
				digitosZero = digitosZero+"0";
			}
			CPF = digitosZero + CPF;
		}
		CPF = Texto.formataMascara("###.###.###-##", CPF);
		return CPF;
	}
}
