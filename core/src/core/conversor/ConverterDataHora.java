package core.conversor;

import java.text.SimpleDateFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.resource.ResourceServiceUtil;

/**
 * 
 * <b>Título:</b> ConverterDataHora.java <br>
 * <b>Descrição:</b>     <br>
 * <b>Pacote:</b> br.com.leonardopeixoto.core.converter <br>
 * <b>Projeto:</b> br.com.leonardopeixoto.core <br>
 * 
 * <b>Copyright (c) 2009 Leonardo Borges Peixoto.</b>
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class ConverterDataHora implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		
		Object objectValue;
		//Se o valor for nulo ou vazio.
		if (value != null && (value.length() == 0 || value.trim().length() == 0)) {
			objectValue = null;
		} else {
			try {
				objectValue = new SimpleDateFormat("HH:mm").parse(value);
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
			stringValue = new SimpleDateFormat("HH:mm").format(value);
		}
		return stringValue;
	}
	
}
