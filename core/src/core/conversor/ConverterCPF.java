package core.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.utilitario.Texto;


/**
 * <b>Classe:</b> ConverterCPF.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.conversor <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class ConverterCPF implements Converter {

	public Object getAsObject(FacesContext context, UIComponent componente, String str)	throws ConverterException {

		Long result = null;
		if(str != null && !str.trim().isEmpty()){
			
		}
		return result;
	}
	
	/**
	 * Este método recebe um Long e o converte para uma String com máscara de CPF
	 * 
	 * @param context - FacesContext
	 * @param componente - UIComponent
	 * @param obj - Object
	 * @return String - CPF convertido
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent componente, Object obj)	throws ConverterException {
		String CPF = "";
		if(obj != null && obj instanceof Long){
			Long cpfLongType = (Long)obj;
			CPF = cpfLongType.toString();
		}else if(obj != null && obj instanceof String){
			CPF = (String)obj;
		}
		if(CPF != null && CPF.length() < 11){
			// Completa com zeros a esquerda caso numeração total seja menor que 11
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
