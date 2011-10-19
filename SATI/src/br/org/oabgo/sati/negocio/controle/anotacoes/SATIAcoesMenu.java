package br.org.oabgo.sati.negocio.controle.anotacoes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.org.oabgo.sati.negocio.enumerador.SATIEnumFaseAcao;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> SATIAcoesMenu.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.anotacoes <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
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
	 * Captura o objeto para o qual a ação referencia.
	 * @return Class<? extends TransferObject>
	 */
	Class<? extends TransferObject> objeto();
	
	/**
	 * Captura a FASE que a ação referencia.
	 * @return SATIEnumFaseAcao
	 */
	SATIEnumFaseAcao fase(); 

	/**
	 * Captura o Sistema ao qual o menu de ações esta ligado.
	 * @return sistema
	 */
	String sistema(); 

	/**
	 * Captura a Página ao qual o menu de ações esta ligado.
	 * @return sistema
	 */
	String pagina(); 
}
