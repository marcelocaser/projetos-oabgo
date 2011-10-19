package core.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.utilitario.Texto;


/**
 * 
 * <b>Classe:</b> ConverterCNPJ.java <br>
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
public class ConverterCNPJ implements Converter {

	public Object getAsObject(FacesContext context, UIComponent componente, String str)	throws ConverterException {

		Long result = null;
		if(str != null && !str.trim().isEmpty()){
			
		}
		return result;
	}
	
	/**
	 * Este método recebe um Long e o converte para uma String com máscara de CNPJ
	 * 
	 * @param context - FacesContext
	 * @param componente - UIComponent
	 * @param obj - Object
	 * @return String - CPF convertido
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent componente, Object obj)	throws ConverterException {
		String CNPJ = new String();
		if(obj != null && obj instanceof Long){
			Long cpfLongType = (Long)obj;
			CNPJ = cpfLongType.toString();
		}else if(obj != null && obj instanceof String){
			CNPJ = (String)obj;
		}
		if(CNPJ != null && CNPJ.length() < 14){
			int comprimento = CNPJ.length();
			int complemento = 14 - comprimento;
			String digitosZero = new String();
			for(int i = 0; i < complemento; i++){
				digitosZero = digitosZero + "0";
			}
			CNPJ = digitosZero + CNPJ;
		}
		CNPJ = Texto.formataMascara("##.###.###/####-##", CNPJ);
		return CNPJ;
	}

}
