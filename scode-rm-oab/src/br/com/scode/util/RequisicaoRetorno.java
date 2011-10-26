package br.com.scode.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import br.com.scode.PedidoDeRetorno;

public class RequisicaoRetorno {

	private List<PedidoDeRetorno> pedidoRetornoList = new ArrayList<PedidoDeRetorno>();


	/**
	 * Adiciona o {@link PedidoDeRetorno} a requisição, nesse momento a
	 * {@link RequisicaoRetorno} se encarrega de construir os objetos conforme o
	 * envio dos dados pelo Scode.
	 * 
	 * @param pedidoRetorno
	 */
	public void addPedidoDeRetorno(PedidoDeRetorno pedidoRetorno) {
		pedidoRetornoList.add(pedidoRetorno);
	}

	
	/**
	 * Montar String de Retorno
	 *
	 * @param ajustar
	 * @return - 
	 * @return List<PedidoDeRetorno>
	 */
	public static List<PedidoDeRetorno> ajustarRetorno(String ajustar){
		
		int i = 0;
		String linha;
		String[] linhaSplit;
		PedidoDeRetorno pedidoRetorno;
		List<PedidoDeRetorno> pedidoRetornoList = new ArrayList<PedidoDeRetorno>();
		BufferedReader reader = new BufferedReader(new StringReader(ajustar));
		try {
			
			pedidoRetorno = new PedidoDeRetorno();
			while((linha = reader.readLine()) != null){
				linhaSplit = linha.split("\\;");
				
				//Verifica se o Pedido tem mais de um Item. 
				//Caso sim, inseri o Item ao Pedido.
				if (pedidoRetornoList.size() > 0 && pedidoRetornoList.get(pedidoRetornoList.size() - 1).getPedidoRmId().compareTo(linhaSplit[0]) == 0) {
					
					if (pedidoRetornoList.get(pedidoRetornoList.size() - 1).getFornecedor().get(i).getCnpj().compareTo(linhaSplit[21]) == 0) {
						pedidoRetornoList.get(pedidoRetornoList.size() - 1).getFornecedor().get(i).addItemAoFornecedor(linhaSplit[12], linhaSplit[13], linhaSplit[14], linhaSplit[15], linhaSplit[16], linhaSplit[17], linhaSplit[18]);
					} else {
						pedidoRetornoList.get(pedidoRetornoList.size() - 1).addItemEFornecedor(linhaSplit[7], linhaSplit[8], linhaSplit[9], linhaSplit[10], linhaSplit[11], linhaSplit[12], linhaSplit[13], linhaSplit[14], linhaSplit[15], linhaSplit[16], linhaSplit[17], linhaSplit[18], linhaSplit[19], linhaSplit[20], linhaSplit[21], linhaSplit[22], linhaSplit[23], linhaSplit[24], linhaSplit[25], linhaSplit[26], linhaSplit[27], linhaSplit[28], linhaSplit[29], linhaSplit[30], linhaSplit[31], linhaSplit[32], linhaSplit[33]);
						i++;
					}
				} else {
					pedidoRetorno = new PedidoDeRetorno();
					pedidoRetorno.setPedidoRmId(linhaSplit[0]);
					pedidoRetorno.setOrdemServicoId(linhaSplit[1]);
					pedidoRetorno.setDataOrdemCompra(linhaSplit[2]);
					pedidoRetorno.setDataLimiteRespCotacao(linhaSplit[3]);
					pedidoRetorno.setOrdemCompraId(linhaSplit[4]);
					pedidoRetorno.setDataLimiteEntegraMercadoria((linhaSplit[5]));
					pedidoRetorno.setFormaPagamento(linhaSplit[6]);
					pedidoRetorno.addItemEFornecedor(linhaSplit[7], linhaSplit[8], linhaSplit[9], linhaSplit[10], linhaSplit[11], linhaSplit[12], linhaSplit[13], linhaSplit[14], linhaSplit[15], linhaSplit[16], linhaSplit[17], linhaSplit[18], linhaSplit[19], linhaSplit[20], linhaSplit[21], linhaSplit[22], linhaSplit[23], linhaSplit[24], linhaSplit[25], linhaSplit[26], linhaSplit[27], linhaSplit[28], linhaSplit[29], linhaSplit[30], linhaSplit[31], linhaSplit[32], linhaSplit[33]);
					pedidoRetornoList.add(pedidoRetorno);
					i = 0;
				}
			}
		} catch (IOException e) {
			// caso esta classe não pertença à camada de controle,
			//  esta exceção deve ser propagada até a camada de Controller
		}
		
		return pedidoRetornoList;
	}
	
	
}
