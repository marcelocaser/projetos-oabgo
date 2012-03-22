package br.com.flavios.pbpf.negocio.controle.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class EstoqueProdutoChaveComposta implements Serializable {

	@ManyToOne
	@JoinColumn(name = "preFilCod", nullable = false)
	private FilialTO filial;

	@ManyToOne
	@JoinColumn(name = "proCod", nullable = false)
	private ProdutoTO produto;

	@Column(name = "corCod", nullable = false)
	private String corCod;

	@Column(name = "estNum", nullable = false)
	private String estNum;

	// *** Gets and Sets ***
	
	public FilialTO getFilial() {
		return filial;
	}

	public void setFilial(FilialTO filial) {
		this.filial = filial;
	}

	public ProdutoTO getProduto() {
		return produto;
	}

	public void setProduto(ProdutoTO produto) {
		this.produto = produto;
	}

	public String getCorCod() {
		return corCod;
	}

	public void setCorCod(String corCod) {
		this.corCod = corCod;
	}

	public String getEstNum() {
		return estNum;
	}

	public void setEstNum(String estNum) {
		this.estNum = estNum;
	}

}
