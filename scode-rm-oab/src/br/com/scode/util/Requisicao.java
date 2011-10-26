package br.com.scode.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.NameValuePair;

import br.com.scode.PedidoDeEnvio;
import br.com.scode.PedidoDeEnvio.ItemPedido;

public class Requisicao {

	private Map<String, String> params = new HashMap<String, String>();

	private List<PedidoDeEnvio> pedidos = new ArrayList<PedidoDeEnvio>();

	public Requisicao() {

		String usuario = ParametrosUtil.get(ParametrosUtil.SCODE_USUARIO);
		String empresa_id = ParametrosUtil.get(ParametrosUtil.SCODE_EMPRESA_ID);
		String projeto_id = ParametrosUtil.get(ParametrosUtil.SCODE_PROJETO_ID);
		String senha = ParametrosUtil.get(ParametrosUtil.SCODE_SENHA);

		params.put("usuario", usuario);
		params.put("senha", senha);
		params.put("empresa_id", empresa_id);
		params.put("projeto_id", projeto_id);
	}

	public void addParametro(String key, String valor) {
		params.put(key, valor);
	}

	public NameValuePair[] getParametros() {
		
		construirParametrosPedidos();
		
		List<NameValuePair> list = new ArrayList<NameValuePair>();

		for (String key : params.keySet()) {
			NameValuePair sp = new NameValuePair(key, params.get(key));
			list.add(sp);
		}

		return list.toArray(new NameValuePair[] {});
	}
	
	
	private void construirParametrosPedidos() {
		int indice = 1;
		
		for(PedidoDeEnvio pedido : pedidos) {
			addParametro("ped[" + indice + "][usuario_id]", pedido.getUsuarioId());
			addParametro("ped[" + indice + "][pedido_rm_id]", pedido.getPedidoRmId());
			addParametro("ped[" + indice + "][limite]", pedido.getDataLimite());
			addParametro("ped[" + indice + "][comentario]", pedido.getComentario() != null ? pedido.getComentario() : "");
			
			int i = 1;
			for (ItemPedido item : pedido.getItensDoPedido()) {
				addParametro("ped[" + indice + "][item_pedido][" + i + "][insumo_rm_id]", item.getItemRmId());
				addParametro("ped[" + indice + "][item_pedido][" + i + "][unidade]", item.getUnidade());
				addParametro("ped[" + indice + "][item_pedido][" + i + "][descricao]", item.getDescricao());
				addParametro("ped[" + indice + "][item_pedido][" + i + "][quantidade]", item.getQuantidade());
				addParametro("ped[" + indice + "][item_pedido][" + i + "][marca]", item.getMarca() != null ? item.getMarca().trim() : "");
				addParametro("ped[" + indice + "][item_pedido][" + i + "][comentario]", item.getComentario() != null ? item.getComentario().trim() : "");
				i++;
			}
			
			indice++;		
		}
		
	}
	

	/**
	 * Adiciona o {@link PedidoDeEnvio} a requisição, nesse momento a
	 * {@link Requisicao} se encarrega de construir os parametros conforme o
	 * scode recebe
	 * 
	 * @param pedido
	 */
	public void addPedidoDeEnvio(PedidoDeEnvio pedido) {
		pedidos.add(pedido);
	}

}
