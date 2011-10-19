package br.org.oabgo.saeo.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> CertameTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SAEO <br>
 * <b>Pacote:</b> br.org.oabgo.saeo.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author marcelo
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbDsCertameExameOrdem")
public class CertameTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "intNumeroLivro")
	private Integer intNumeroLivro;
	
	@Column(name = "vchDescricao", nullable = false)
	private String vchDescricao;
	
	@Column(name = "datDataProvaEscrita")
	private Date datDataProvaEscrita;
	
	@Column(name = "datDataProvaOral")
	private Date datDataProvaOral;
	
	@Column(name = "datDataProvaObjetiva")
	private Date datDataProvaObjetiva;
	
	public CertameTO() {
	}
	
	public CertameTO(Long id) {
		this.id = id;
	}
	
	@Transient
	public Serializable getKey() {
		return getId();
	}

	public void setId(Serializable id) {
		this.id = (Long)id;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIntNumeroLivro() {
		return intNumeroLivro;
	}

	public void setIntNumeroLivro(Integer intNumeroLivro) {
		this.intNumeroLivro = intNumeroLivro;
	}

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

	public Date getDatDataProvaEscrita() {
		return datDataProvaEscrita;
	}

	public void setDatDataProvaEscrita(Date datDataProvaEscrita) {
		this.datDataProvaEscrita = datDataProvaEscrita;
	}

	public Date getDatDataProvaOral() {
		return datDataProvaOral;
	}

	public void setDatDataProvaOral(Date datDataProvaOral) {
		this.datDataProvaOral = datDataProvaOral;
	}

	public Date getDatDataProvaObjetiva() {
		return datDataProvaObjetiva;
	}

	public void setDatDataProvaObjetiva(Date datDataProvaObjetiva) {
		this.datDataProvaObjetiva = datDataProvaObjetiva;
	}

}
