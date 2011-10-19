package core.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.utilitario.Texto;

public class ConverterProcesso implements Converter {

	public Object getAsObject(FacesContext context, UIComponent componente, String str) throws ConverterException {
		if(str != null && !str.trim().isEmpty()){
			return Texto.formataMascara("####/#####", str);
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent componente, Object obj) throws ConverterException {
		String data = "";
		if(obj != null && obj instanceof String){
			data = Texto.formataMascara("####/#####", (String)obj);
		}
		return data;
	}

}
