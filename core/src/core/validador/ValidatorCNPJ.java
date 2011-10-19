package core.validador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.brazilutils.br.cpfcnpj.Cnpj;

import core.resource.ResourceServiceUtil;

/**
 * <b>Classe:</b> ValidatorCNPJ.java <br>
 * <b>Descri��o:</b> <br>
 * 
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.validador <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public class ValidatorCNPJ implements Validator {

	/**
	 * Verifica se � um CNPJ v�lido.
	 * 
	 * @param context
	 * @param component
	 * @param value
	 * @throws ValidatorException
	 *             -
	 */
	public void validate(FacesContext context, UIComponent component, Object value) {
		if (!Cnpj.isValid(value.toString())) {
			throw new ValidatorException(ResourceServiceUtil
					.getMessageResource("satiValidarCNPJ", null));
		}
	}

}
