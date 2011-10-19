package core.utilitario;

import java.util.Random;

/**
 * <b>Classe:</b> RandomIntGenerator.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> core <br>
 * <b>Pacote:</b> core.utilitario <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */

public class RandomIntGenerator {
	
	private int low;
	private int high;
	private static final int  BUFFER_SIZE = 101;
	private static double[] buffer = new double[BUFFER_SIZE];
	private static final Random r = new Random();
	
	/**
	 * Preenche o buffer
	 */
	static {
		int i;
		for(i=0; i < BUFFER_SIZE; i++){
			buffer[i] = Math.random();
		}
	}
	
	public RandomIntGenerator(int low, int high){
		this.low = low;
		this.high = high;
	}
	
	/**
	 * ACTION DE GERAÇÃO DE INTEIROS RANDÔMICOS
	 * @return int
	 */
	public int draw(){
		int r = low + (int) ((high - low + 1) * nextRandom());
		return r;
	}
	
	/**
	 * Implementação da lógica de randomicidade
	 * @return double
	 */
	private static double nextRandom(){
		int pos = (int)(Math.random() * BUFFER_SIZE);
		double r = buffer[pos];
		buffer[pos] = Math.random();
		return r;
	}
	
	public static int proximo(){
		return r.nextInt();
	}

}
