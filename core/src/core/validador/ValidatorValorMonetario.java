package core.validador;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import core.resource.ResourceServiceUtil;
import core.utilitario.Numero;


/**
 * <b>Classe:</b> ValidatorValorMonetario.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.validador <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
public class ValidatorValorMonetario implements Validator {

	/**
	 * M�todo chamado para realizar a valida��o de uma Data
	 * @param facesContext - FacesContext
	 * @param comp - UIComponent
	 * @param valor - Date
	 */
	public void validate(FacesContext facesContext, UIComponent comp, Object valor){
		if(valor != null && !Numero.isNumeric(valor.toString())){
			this.GoMsG();
		}
	}
	
	/**
	 * Retorna a mensagem de erro para uma data inv�lida
	 */
	private void GoMsG(){
		throw new ValidatorException(ResourceServiceUtil.getMessageResource("satiValidarMoeda", null));
	}
	
}
