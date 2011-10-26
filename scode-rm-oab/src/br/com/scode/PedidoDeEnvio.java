package br.com.scode;

import java.util.ArrayList;
import java.util.List;

public class PedidoDeEnvio {

	private String usuarioId;
	private String pedidoRmId;
	private String dataLimite;
	private String comentario;
	private List<ItemPedido> itensDoPedido = new ArrayList<ItemPedido>();

	public String getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(String usuarioId) {
		this.usuarioId = usuarioId;
	}

	public String getPedidoRmId() {
		return pedidoRmId;
	}

	public void setPedidoRmId(String pedidoRmId) {
		this.pedidoRmId = pedidoRmId;
	}

	public String getDataLimite() {
		return dataLimite;
	}

	public void setDataLimite(String dataLimite) {
		this.dataLimite = dataLimite;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public List<ItemPedido> getItensDoPedido() {
		return itensDoPedido;
	}

	public void setItensDoPedido(List<ItemPedido> itensDoPedido) {
		this.itensDoPedido = itensDoPedido;
	}

	public void addItemAoPedido(ItemPedido item) {
		itensDoPedido.add(item);
	}

	public void addItemAoPedido(String itemRmId, String unidade, String descricao, String quantidade) {
		ItemPedido item = new ItemPedido();
		item.setItemRmId(itemRmId);
		item.setUnidade(unidade);
		item.setDescricao(descricao);
		item.setQuantidade(quantidade);
		addItemAoPedido(item);
	}

	public static class ItemPedido {
		private String itemRmId;
		private String unidade;
		private String descricao;
		private String quantidade;
		private String marca;
		private String comentario;

		public String getMarca() {
			return marca;
		}

		public void setMarca(String marca) {
			this.marca = marca;
		}

		public String getComentario() {
			return comentario;
		}

		public void setComentario(String comentario) {
			this.comentario = comentario;
		}

		public String getItemRmId() {
			return itemRmId;
		}

		public void setItemRmId(String itemRmId) {
			this.itemRmId = itemRmId;
		}

		public String getUnidade() {
			return unidade;
		}

		public void setUnidade(String unidade) {
			this.unidade = unidade;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getQuantidade() {
			return quantidade;
		}

		public void setQuantidade(String quantidade) {
			this.quantidade = quantidade;
		}

	}

}
