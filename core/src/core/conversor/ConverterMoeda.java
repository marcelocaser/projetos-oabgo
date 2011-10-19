package core.conversor;

import java.text.NumberFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.resource.ResourceServiceUtil;
import core.utilitario.Numero;

/**
 * <b>Classe:</b> ConverterMoeda.java <br>
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
public class ConverterMoeda implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		Object objectValue;
		NumberFormat num = NumberFormat.getInstance();
		
		if (value != null && (value.length() == 0 || value.trim().length() == 0)) {
			objectValue = null;
		} else {
			try {
				Number valor = num.parse(value);
				objectValue = valor.doubleValue();
			} catch (Exception e) {
				System.out.print(ResourceServiceUtil.getMessageResource("satiConverterMoeda", null));
				objectValue = "GoToValidator"; 
			}
		}
		return objectValue;
	}
	
	public String getAsString(FacesContext context, UIComponent component, Object value)throws ConverterException {
		String stringValue = "";
		Numero num = new Numero();
		if (value != null) {
			if(value instanceof Double){
				stringValue = num.formatarDecimal((Double)value, 2);
			}else if(value instanceof Long){
				stringValue = num.formatarDecimal((Long)value, 2);
			}else if(value instanceof Integer){
				stringValue = num.formatarDecimal(Long.valueOf((Integer)value), 2);
			}
		}
		return stringValue;
	}

}
