package br.com.scode;

import java.util.ArrayList;
import java.util.List;

public class PedidoDeRetorno {

	private String pedidoRmId;
	private String ordemServicoId;
	private String dataOrdemCompra;
	private String dataLimiteRespCotacao;
	private String ordemCompraId;
	private String dataLimiteEntegraMercadoria;
	private String formaPagamento;
	private String previsaoEntegra;
	private List<Fornecedor> fornecedor = new ArrayList<Fornecedor>();
	
	public String getPedidoRmId() {
		return pedidoRmId;
	}

	public void setPedidoRmId(String pedidoRmId) {
		this.pedidoRmId = pedidoRmId;
	}

	public String getOrdemServicoId() {
		return ordemServicoId;
	}

	public void setOrdemServicoId(String ordemServicoId) {
		this.ordemServicoId = ordemServicoId;
	}

	public String getDataOrdemCompra() {
		return dataOrdemCompra;
	}

	public void setDataOrdemCompra(String dataOrdemCompra) {
		this.dataOrdemCompra = dataOrdemCompra;
	}

	public String getDataLimiteRespCotacao() {
		return dataLimiteRespCotacao;
	}

	public void setDataLimiteRespCotacao(String dataLimiteRespCotacao) {
		this.dataLimiteRespCotacao = dataLimiteRespCotacao;
	}

	public String getOrdemCompraId() {
		return ordemCompraId;
	}

	public void setOrdemCompraId(String ordemCompraId) {
		this.ordemCompraId = ordemCompraId;
	}

	public String getDataLimiteEntegraMercadoria() {
		return dataLimiteEntegraMercadoria;
	}

	public void setDataLimiteEntegraMercadoria(String dataLimiteEntegraMercadoria) {
		this.dataLimiteEntegraMercadoria = dataLimiteEntegraMercadoria;
	}

	public String getFormaPagamento() {
		return formaPagamento;
	}

	public void setFormaPagamento(String formaPagamento) {
		this.formaPagamento = formaPagamento;
	}

	public String getPrevisaoEntegra() {
		return previsaoEntegra;
	}

	public void setPrevisaoEntegra(String previsaoEntegra) {
		this.previsaoEntegra = previsaoEntegra;
	}

	public List<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(List<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public void addFornecedor(Fornecedor fornecedor) {
		this.fornecedor.add(fornecedor);
	}

	public void addItemEFornecedor(String dataPrevisaoEntrega, String diasParaEntrega, String formaPagamento, String frete, String dataValidadeProposta, String produtoSolicitadoRmId, String itemProdutoDescricao,
			String quantidadeSolicitada, String dataInformadoPrecoProduto, String precoUnitario,
			String totalFinal, String validadeProduto, String razaoSocial, String nomeFantasia,
			String cnpj, String inscricaoMunicipal, String inscricaoEstadual, String endereco, String bairro, String cep, String cidade,
			String estado, String contato, String telefone, String fax, String email,
			String descricaoProdutoServicoFornecido) {

		Fornecedor fornecedor = new Fornecedor();
		
		fornecedor.setDataPrevisaoEntrega(dataPrevisaoEntrega);
		fornecedor.setDiasParaEntrega(diasParaEntrega);
		fornecedor.setFormaPagamento(formaPagamento);
		fornecedor.setFrete(frete);
		fornecedor.setDataValidadeProposta(dataValidadeProposta);
		fornecedor.setRazaoSocial(razaoSocial);
		fornecedor.setNomeFantasia(nomeFantasia);
		fornecedor.setCnpj(cnpj);
		fornecedor.setInscricaoMunicipal(inscricaoMunicipal);
		fornecedor.setInscricaoEstadual(inscricaoEstadual);
		fornecedor.setEndereco(endereco);
		fornecedor.setBairro(bairro);
		fornecedor.setCep(cep);
		fornecedor.setCidade(cidade);
		fornecedor.setEstado(estado);
		fornecedor.setContato(contato);
		fornecedor.setTelefone(telefone);
		fornecedor.setFax(fax);
		fornecedor.setEmail(email);
		fornecedor.setDescricaoProdutoServicoFornecido(descricaoProdutoServicoFornecido);
		
		fornecedor.addItemAoFornecedor(produtoSolicitadoRmId,itemProdutoDescricao,
				quantidadeSolicitada, dataInformadoPrecoProduto, precoUnitario,
				totalFinal, validadeProduto);

		addFornecedor(fornecedor);
	}

	public static class Fornecedor {

		private String dataPrevisaoEntrega;
		private String diasParaEntrega;
		private String formaPagamento;
		private String frete;
		private String dataValidadeProposta;
		private String razaoSocial;
		private String nomeFantasia;
		private String cnpj;
		private String inscricaoMunicipal;
		private String inscricaoEstadual;
		private String endereco;
		private String bairro;
		private String cep;
		private String cidade;
		private String estado;
		private String contato;
		private String telefone;
		private String fax;
		private String email;
		private String descricaoProdutoServicoFornecido;
		private List<ItemPedidoRetorno> itensDoPedido = new ArrayList<ItemPedidoRetorno>();
		
		public String getDataPrevisaoEntrega() {
			return dataPrevisaoEntrega;
		}

		public void setDataPrevisaoEntrega(String dataPrevisaoEntrega) {
			this.dataPrevisaoEntrega = dataPrevisaoEntrega;
		}

		public String getDiasParaEntrega() {
			return diasParaEntrega;
		}

		public void setDiasParaEntrega(String diasParaEntrega) {
			this.diasParaEntrega = diasParaEntrega;
		}

		public String getFormaPagamento() {
			return formaPagamento;
		}

		public void setFormaPagamento(String formaPagamento) {
			this.formaPagamento = formaPagamento;
		}

		public String getFrete() {
			return frete;
		}

		public void setFrete(String frete) {
			this.frete = frete;
		}

		public String getDataValidadeProposta() {
			return dataValidadeProposta;
		}

		public void setDataValidadeProposta(String dataValidadeProposta) {
			this.dataValidadeProposta = dataValidadeProposta;
		}

		public String getRazaoSocial() {
			return razaoSocial;
		}

		public void setRazaoSocial(String razaoSocial) {
			this.razaoSocial = razaoSocial;
		}

		public String getNomeFantasia() {
			return nomeFantasia;
		}

		public void setNomeFantasia(String nomeFantasia) {
			this.nomeFantasia = nomeFantasia;
		}

		public String getCnpj() {
			return cnpj;
		}

		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}

		public String getInscricaoMunicipal() {
			return inscricaoMunicipal;
		}

		public void setInscricaoMunicipal(String inscricaoMunicipal) {
			this.inscricaoMunicipal = inscricaoMunicipal;
		}

		public String getInscricaoEstadual() {
			return inscricaoEstadual;
		}

		public void setInscricaoEstadual(String inscricaoEstadual) {
			this.inscricaoEstadual = inscricaoEstadual;
		}

		public String getEndereco() {
			return endereco;
		}

		public void setEndereco(String endereco) {
			this.endereco = endereco;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getBairro() {
			return bairro;
		}

		public String getCep() {
			return cep;
		}

		public void setCep(String cep) {
			this.cep = cep;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getEstado() {
			return estado;
		}

		public void setEstado(String estado) {
			this.estado = estado;
		}

		public String getContato() {
			return contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getFax() {
			return fax;
		}

		public void setFax(String fax) {
			this.fax = fax;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getDescricaoProdutoServicoFornecido() {
			return descricaoProdutoServicoFornecido;
		}

		public void setDescricaoProdutoServicoFornecido(String descricaoProdutoServicoFornecido) {
			this.descricaoProdutoServicoFornecido = descricaoProdutoServicoFornecido;
		}

		public List<ItemPedidoRetorno> getItensDoPedido() {
			return itensDoPedido;
		}

		public void setItensDoPedido(List<ItemPedidoRetorno> itensDoPedido) {
			this.itensDoPedido = itensDoPedido;
		}

		public void addItemAoFornecedor(String produtoSolicitadoRmId, String itemProdutoDescricao,
				String quantidadeSolicitada, String dataInformadoPrecoProduto, String precoUnitario,
				String totalFinal, String validadeProduto) {

			ItemPedidoRetorno item = new ItemPedidoRetorno();
			item.setProdutoSolicitadoRmId(produtoSolicitadoRmId);
			item.setItemProdutoDescricao(itemProdutoDescricao);
			item.setQuantidadeSolicitada(quantidadeSolicitada);
			item.setDataInformadoPrecoProduto(dataInformadoPrecoProduto);
			item.setPrecoUnitario(precoUnitario);
			item.setTotalFinal(totalFinal);
			item.setValidadeProduto(validadeProduto);

			addItemAoFornecedor(item);
		}

		public void addItemAoFornecedor(ItemPedidoRetorno item) {
			itensDoPedido.add(item);
		}
		
		public static class ItemPedidoRetorno {

			private String produtoSolicitadoRmId;
			private String itemProdutoDescricao;
			private String quantidadeSolicitada;
			private String dataInformadoPrecoProduto;
			private String precoUnitario;
			private String totalFinal;
			private String validadeProduto;
			public String getProdutoSolicitadoRmId() {
				return produtoSolicitadoRmId;
			}
			public void setProdutoSolicitadoRmId(String produtoSolicitadoRmId) {
				this.produtoSolicitadoRmId = produtoSolicitadoRmId;
			}
			public String getItemProdutoDescricao() {
				return itemProdutoDescricao;
			}
			public void setItemProdutoDescricao(String itemProdutoDescricao) {
				this.itemProdutoDescricao = itemProdutoDescricao;
			}
			public String getQuantidadeSolicitada() {
				return quantidadeSolicitada;
			}
			public void setQuantidadeSolicitada(String quantidadeSolicitada) {
				this.quantidadeSolicitada = quantidadeSolicitada;
			}
			public String getDataInformadoPrecoProduto() {
				return dataInformadoPrecoProduto;
			}
			public void setDataInformadoPrecoProduto(String dataInformadoPrecoProduto) {
				this.dataInformadoPrecoProduto = dataInformadoPrecoProduto;
			}
			public String getPrecoUnitario() {
				return precoUnitario;
			}
			public void setPrecoUnitario(String precoUnitario) {
				this.precoUnitario = precoUnitario;
			}
			public String getTotalFinal() {
				return totalFinal;
			}
			public void setTotalFinal(String totalFinal) {
				this.totalFinal = totalFinal;
			}
			public String getValidadeProduto() {
				return validadeProduto;
			}
			public void setValidadeProduto(String validadeProduto) {
				this.validadeProduto = validadeProduto;
			}			

		}

	}

}
