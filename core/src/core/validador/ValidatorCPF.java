package core.validador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import core.resource.ResourceServiceUtil;
import core.utilitario.Numero;


/**
 * <b>Classe:</b> ValidatorCPF.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.validador <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class ValidatorCPF implements Validator {

	/**
	 * Verifica se é um CPF válido
	 * @param context FacesContext
	 * @param component UIComponent
	 * @param value Valor a ser validado
	 */
    public void validate(FacesContext context, UIComponent component, Object value) {
    	if(!Numero.validarCPF(value.toString())){
    		throw new ValidatorException(ResourceServiceUtil.getMessageResource("satiValidarCPF", null));
    	}
    }
}
