package core.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.utilitario.Texto;


/**
 * <b>Classe:</b> ConverterDataAD.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.conversor <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author marcelo
 * @version Revision: $ Date: $
 */
public class ConverterDataAD implements Converter {

	public Object getAsObject(FacesContext context, UIComponent componente, String str)	throws ConverterException {

		if(str != null && !str.trim().isEmpty()){
			return Texto.formataMascara("####/##/## ##:##:##", str);
		}
		return null;
	}
	
	/**
	 * Este m�todo recebe um Long e o converte para uma String com m�scara de Data
	 * 
	 * @param context - FacesContext
	 * @param componente - UIComponent
	 * @param obj - Object
	 * @return String - Data convertido
	 * @throws ConverterException
	 */
	public String getAsString(FacesContext context, UIComponent componente, Object obj)	throws ConverterException {
		String data = "";
		if(obj != null && obj instanceof String){
			data = Texto.formataMascara("####/##/## ##:##:##", (String)obj);
		}
		return data;
	}

}
