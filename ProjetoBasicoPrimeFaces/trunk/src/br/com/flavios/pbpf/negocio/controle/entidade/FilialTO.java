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
@Table(name = "FILIAL")
public class FilialTO extends TransferObject {
	
	@Id
	@Column(name = "filCod", unique = true, nullable = false)
	private Long filCod;
	
	@Column(name = "filDesc", nullable = false)
	private String filDesc;
	
	@Column(name = "filSigEti", nullable = false)
	private String filSigEti;
	
	public FilialTO() {
	}
	
	public FilialTO(Long filCod) {
		this.filCod = filCod;
	}

	// *** Gets and Sets ***
	
	@Transient
	public Serializable getKey() {
		return getFilCod();
	}
	
	public void setId(Serializable id) {
		this.filCod = (Long) id;
	}
	
	public Long getFilCod() {
		return filCod;
	}

	public void setFilCod(Long filCod) {
		this.filCod = filCod;
	}

	public String getFilDesc() {
		return filDesc;
	}

	public void setFilDesc(String filDesc) {
		this.filDesc = filDesc;
	}

	public String getFilSigEti() {
		return filSigEti;
	}

	public void setFilSigEti(String filSigEti) {
		this.filSigEti = filSigEti;
	}

}
