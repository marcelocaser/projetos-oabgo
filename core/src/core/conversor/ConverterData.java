package core.conversor;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.resource.ResourceServiceUtil;

/**
 * <b>Classe:</b> ConverterData.java <br>
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
public class ConverterData implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		
		Object objectValue;

		if (value != null && (value.length() == 0 || value.trim().length() == 0)) {
			objectValue = null;
		} else {
			try {
				objectValue = new SimpleDateFormat("dd/MM/yyyy").parse(value);
			} catch (Exception e) {
				System.out.print(ResourceServiceUtil.getMessageResource("satiConverterData", null));
				objectValue = null; 
			}
		}
		return objectValue;
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		String stringValue = "";
		
		if (value != null) {
			stringValue = new SimpleDateFormat("dd/MM/yyyy").format(value);
		}
		return stringValue;
	}
	
}
