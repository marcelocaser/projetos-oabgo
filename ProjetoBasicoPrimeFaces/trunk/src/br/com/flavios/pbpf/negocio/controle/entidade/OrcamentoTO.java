package br.com.flavios.pbpf.negocio.controle.entidade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "ORCAMENT")
public class OrcamentoTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orcNro", unique = true, nullable = false, length = 7)
	private Long orcNro;

	@Column(name = "orcPerDes", length = 6, precision = 2)
	private Double orcPerDes;

	@Column(name = "orcCarNro", nullable = false, length = 7)
	private Integer orcCarNro;

	@ManyToOne
	@JoinColumn(name = "qdfCod", nullable = false)
	private UsuarioTO usuario;

	@Column(name = "orcStatus", nullable = false, length = 1)
	private char orcStatus;

	@Column(name = "orcInicio", nullable = false)
	private Date orcInicio;

	@Column(name = "orcDat1aPa")
	private Date orcDat1aPa;

	@Column(name = "orcVlrEnt", length = 10, precision = 2)
	private Double orcVlrEnt;

	@Column(name = "orcConRec", length = 2)
	private Integer orcConRec;

	@Column(name = "orcTipVda", length = 3)
	private Integer orcTipVda;

	public OrcamentoTO() {
	}

	public OrcamentoTO(Long orcNro) {
		this.orcNro = orcNro;
	}

	// *** Gets and Sets ***

	@Transient
	public Serializable getKey() {
		return getOrcNro();
	}

	public void setId(Serializable id) {
		this.orcNro = (Long) id;
	}

	public Long getOrcNro() {
		return orcNro;
	}

	public void setOrcNro(Long orcNro) {
		this.orcNro = orcNro;
	}

	public Double getOrcPerDes() {
		return orcPerDes;
	}

	public void setOrcPerDes(Double orcPerDes) {
		this.orcPerDes = orcPerDes;
	}

	public Integer getOrcCarNro() {
		return orcCarNro;
	}

	public void setOrcCarNro(Integer orcCarNro) {
		this.orcCarNro = orcCarNro;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}

	public char getOrcStatus() {
		return orcStatus;
	}

	public void setOrcStatus(char orcStatus) {
		this.orcStatus = orcStatus;
	}

	public Date getOrcInicio() {
		return orcInicio;
	}

	public void setOrcInicio(Date orcInicio) {
		this.orcInicio = orcInicio;
	}

	public Date getOrcDat1aPa() {
		return orcDat1aPa;
	}

	public void setOrcDat1aPa(Date orcDat1aPa) {
		this.orcDat1aPa = orcDat1aPa;
	}

	public Double getOrcVlrEnt() {
		return orcVlrEnt;
	}

	public void setOrcVlrEnt(Double orcVlrEnt) {
		this.orcVlrEnt = orcVlrEnt;
	}

	public Integer getOrcConRec() {
		return orcConRec;
	}

	public void setOrcConRec(Integer orcConRec) {
		this.orcConRec = orcConRec;
	}

	public Integer getOrcTipVda() {
		return orcTipVda;
	}

	public void setOrcTipVda(Integer orcTipVda) {
		this.orcTipVda = orcTipVda;
	}

}
