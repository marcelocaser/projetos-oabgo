package br.com.flavios.pbpf.negocio.controle.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "ESTPRO")
public class EstoqueProdutoTO extends TransferObject {
	
	@EmbeddedId
	private EstoqueProdutoChaveComposta estoqueProdutoChaveComposta;
	
	@Column(name = "proBarCod", nullable = false, length = 10)
	private String proBarCod;

	@Column(name = "proBarNovo", nullable = false, length = 13)
	private String proBarNovo;
	
	@Column(name = "prcVlrPreV", nullable = false, length = 10, precision = 2)
	private Float prcVlrPreV;

	public EstoqueProdutoTO() {
	}

	public EstoqueProdutoTO(
			EstoqueProdutoChaveComposta estoqueProdutoChaveComposta) {
		this.estoqueProdutoChaveComposta = estoqueProdutoChaveComposta;
	}

	// *** Gets and Sets ***

	@Transient
	public Serializable getKey() {
		return getEstoqueProdutoChaveComposta();
	}

	public void setId(Serializable id) {
		this.estoqueProdutoChaveComposta = (EstoqueProdutoChaveComposta) id;
	}

	public EstoqueProdutoChaveComposta getEstoqueProdutoChaveComposta() {
		return this.estoqueProdutoChaveComposta;
	}

	public void setEstoqueProdutoChaveComposta(
			EstoqueProdutoChaveComposta estoqueProdutoChaveComposta) {
		this.estoqueProdutoChaveComposta = estoqueProdutoChaveComposta;
	}

	public String getProBarCod() {
		return proBarCod;
	}

	public void setProBarCod(String proBarCod) {
		this.proBarCod = proBarCod;
	}

	public String getProBarNovo() {
		return proBarNovo;
	}

	public void setProBarNovo(String proBarNovo) {
		this.proBarNovo = proBarNovo;
	}

	public Float getPrcVlrPreV() {
		return prcVlrPreV;
	}

	public void setPrcVlrPreV(Float prcVlrPreV) {
		this.prcVlrPreV = prcVlrPreV;
	}

}
