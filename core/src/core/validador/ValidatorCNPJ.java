package core.validador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.brazilutils.br.cpfcnpj.Cnpj;

import core.resource.ResourceServiceUtil;

/**
 * <b>Classe:</b> ValidatorCNPJ.java <br>
 * <b>Descrição:</b> <br>
 * 
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.validador <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 * Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
public class ValidatorCNPJ implements Validator {

	/**
	 * Verifica se é um CNPJ válido.
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
