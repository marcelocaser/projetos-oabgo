package br.com.flavios.pbpf.negocio.controle.entidade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "FUNCIONA")
public class UsuarioTO extends TransferObject {

	@Id
	@Column(name = "qdfCod", unique = true, nullable = false)
	private Long qdfCod;
	
	@Column(name = "qdfNomFunc", nullable = false)
	private String qdfNomFunc;
	
	@ManyToOne
	@JoinColumn(name = "qdfFilCod", nullable = false)
	private FilialTO filial;
	
	@Column(name = "qdfTipVen", nullable = false)
	private Integer qdfTipVen;
	
	@Column(name = "qdfStaFunc", nullable = false)
	private String qdfStaFunc;
	
	@Column(name = "qdfExtEmai", nullable = false)
	private String qdfExtEmai;
	
	@Column(name = "qdfLoginAd", nullable = false)
	private String qdfLoginAd;
	
	public UsuarioTO() {
	}
	
	public UsuarioTO(Long qdfCod) {
		this.qdfCod = qdfCod;
	}
	
	// *** Gets and Sets ***
	
	@Transient
	public Serializable getKey() {
		return getQdfCod();
	}
	
	public void setId(Serializable id) {
		this.qdfCod = (Long) id;
	}

	public Long getQdfCod() {
		return qdfCod;
	}

	public void setQdfCod(Long qdfCod) {
		this.qdfCod = qdfCod;
	}

	public String getQdfNomFunc() {
		return qdfNomFunc;
	}

	public void setQdfNomFunc(String qdfNomFunc) {
		this.qdfNomFunc = qdfNomFunc;
	}

	public FilialTO getFilial() {
		return filial;
	}

	public void setFilial(FilialTO filial) {
		this.filial = filial;
	}

	public Integer getQdfTipVen() {
		return qdfTipVen;
	}

	public void setQdfTipVen(Integer qdfTipVen) {
		this.qdfTipVen = qdfTipVen;
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