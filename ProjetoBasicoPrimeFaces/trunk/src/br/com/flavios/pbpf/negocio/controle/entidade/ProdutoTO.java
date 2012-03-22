package br.com.flavios.pbpf.negocio.controle.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "PRODUTO")
public class ProdutoTO extends TransferObject {
	
	@Id
	@Column(name = "proCod", unique = true, nullable = false)
	private Long proCod;
	
	@Column(name = "proDesc", nullable = false)
	private String proDesc;
	
	@Column(name = "proRefer", nullable = false)
	private String proRefer;
	
	public ProdutoTO() {
	}
	
	public ProdutoTO(Long proCod) {
		this.proCod = proCod;
	}
	
	// *** Gets and Sets ***
	
	@Transient
	public Serializable getKey() {
		return getProCod();
	}

	public void setId(Serializable id) {
		this.proCod = (Long) id;
	}

	public Long getProCod() {
		return proCod;
	}

	public void setProCod(Long proCod) {
		this.proCod = proCod;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getProRefer() {
		return proRefer;
	}

	public void setProRefer(String proRefer) {
		this.proRefer = proRefer;
	}

}
