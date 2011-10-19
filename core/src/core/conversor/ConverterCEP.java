package core.conversor;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import core.utilitario.Texto;

/**
 * <b>Classe:</b> ConverterCEP.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.conversor <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public class ConverterCEP implements Converter {

	public Object getAsObject(FacesContext context, UIComponent componente, String str)	throws ConverterException {

		if(str != null && !str.trim().isEmpty()){
			return Texto.formataMascara("##.###-###", str);
		}
		return null;
	}

	public String getAsString(FacesContext context, UIComponent componente, Object obj)	throws ConverterException {
		String data = "";
		if(obj != null && obj instanceof String){
			data = Texto.formataMascara("##.###-###", (String)obj);
		}
		return data;
	}

}
