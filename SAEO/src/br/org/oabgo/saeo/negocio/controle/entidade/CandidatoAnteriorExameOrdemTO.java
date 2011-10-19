package br.org.oabgo.saeo.negocio.controle.entidade;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> CandidatoAnteriorExameOrdemTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SAEO <br>
 * <b>Pacote:</b> br.org.oabgo.saeo.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdCandidatoAnteriorExameOrdem")
public class CandidatoAnteriorExameOrdemTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "intNumeroSequencia", nullable = false)
	private Integer intNumeroSequencia;
	
	@Column(name = "intPaginaLivro", nullable = false)
	private Integer intPaginaLivro;
	
	@Column(name = "vchNomeCandidato", nullable = false)
	private String vchNomeCandidato;
	
	@Column(name = "bitSituacao", nullable = false)
	private Boolean bitSituacao;
	
	@Column(name = "texObservacao")
	private String texObservacao;
	
	@ManyToOne
	@JoinColumn(name="IdAreaAtuacao")
	private AreaAtuacaoTO areaAtuacao;
	
	@ManyToOne
	@JoinColumn(name="IdCertame")
	private CertameTO certame;
	
	public CandidatoAnteriorExameOrdemTO() {
	}
	
	public CandidatoAnteriorExameOrdemTO(Long id) {
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
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIntNumeroSequencia() {
		return intNumeroSequencia;
	}

	public void setIntNumeroSequencia(Integer intNumeroSequencia) {
		this.intNumeroSequencia = intNumeroSequencia;
	}

	public Integer getIntPaginaLivro() {
		return intPaginaLivro;
	}

	public void setIntPaginaLivro(Integer intPaginaLivro) {
		this.intPaginaLivro = intPaginaLivro;
	}

	public String getVchNomeCandidato() {
		return vchNomeCandidato;
	}

	public void setVchNomeCandidato(String vchNomeCandidato) {
		this.vchNomeCandidato = vchNomeCandidato;
	}

	public Boolean getBitSituacao() {
		return bitSituacao;
	}

	public void setBitSituacao(Boolean bitSituacao) {
		this.bitSituacao = bitSituacao;
	}

	public String getTexObservacao() {
		return texObservacao;
	}

	public void setTexObservacao(String texObservacao) {
		this.texObservacao = texObservacao;
	}

	public AreaAtuacaoTO getAreaAtuacao() {
		return areaAtuacao;
	}

	public void setAreaAtuacao(AreaAtuacaoTO areaAtuacao) {
		this.areaAtuacao = areaAtuacao;
	}
	
	public CertameTO getCertame() {
		return certame;
	}

	public void setCertame(CertameTO certame) {
		this.certame = certame;
	}
}
