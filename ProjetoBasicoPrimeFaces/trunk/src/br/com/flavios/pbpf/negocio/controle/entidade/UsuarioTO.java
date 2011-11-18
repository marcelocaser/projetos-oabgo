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
@Table(name = "FUNCIONA")
public class UsuarioTO extends TransferObject {

	@Id
	@Column(name = "qdfCod", unique = true, nullable = false)
	private Integer qdfCod;
	
	@Column(name = "qdfNomFunc", nullable = false)
	private String qdfNomFunc;
	
	@Column(name = "qdfStaFunc", nullable = false)
	private String qdfStaFunc;
	
	@Column(name = "qdfExtEmai", nullable = false)
	private String qdfExtEmai;
	
	@Column(name = "qdfLoginAd", nullable = false)
	private String qdfLoginAd;
	
	
	public UsuarioTO() {
	}
	
	public UsuarioTO(Integer qdfCod) {
		this.qdfCod = qdfCod;
	}
	
	// *** Gets and Sets ***
	
	@Transient
	public Serializable getKey() {
		return getQdfCod();
	}
	
	public void setId(Serializable id) {
		this.qdfCod = (Integer)qdfCod;
	}

	public Integer getQdfCod() {
		return qdfCod;
	}

	public void setQdfCod(Integer qdfCod) {
		this.qdfCod = qdfCod;
	}

	public String getQdfNomFunc() {
		return qdfNomFunc;
	}

	public void setQdfNomFunc(String qdfNomFunc) {
		this.qdfNomFunc = qdfNomFunc;
	}

	public String getQdfStaFunc() {
		return qdfStaFunc;
	}

	public void setQdfStaFunc(String qdfStaFunc) {
		this.qdfStaFunc = qdfStaFunc;
	}

	public String getQdfExtEmai() {
		return qdfExtEmai;
	}

	public void setQdfExtEmai(String qdfExtEmai) {
		this.qdfExtEmai = qdfExtEmai;
	}

	public String getQdfLoginAd() {
		return qdfLoginAd;
	}

	public void setQdfLoginAd(String qdfLoginAd) {
		this.qdfLoginAd = qdfLoginAd;
	}
	
}