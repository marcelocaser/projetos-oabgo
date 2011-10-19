package br.org.oabgo.sati.negocio.controle.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.org.oabgo.sati.negocio.enumerador.SATIEnumFaseAcao;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> SATIAcoesMenu.java <br>
 * <b>Descri��o:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.anotacoes <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Se��o de Goi�s <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface SATIAcoesMenu {

	/**
	 * Captura o objeto para o qual a a��o referencia.
	 * @return Class<? extends TransferObject>
	 */
	Class<? extends TransferObject> objeto();
	
	/**
	 * Captura a FASE que a a��o referencia.
	 * @return SATIEnumFaseAcao
	 */
	SATIEnumFaseAcao fase(); 

	/**
	 * Captura o Sistema ao qual o menu de a��es esta ligado.
	 * @return sistema
	 */
	String sistema(); 

	/**
	 * Captura a P�gina ao qual o menu de a��es esta ligado.
	 * @return sistema
	 */
	String pagina(); 
}
