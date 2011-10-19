package br.org.oabgo.sati.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> AtendimentoAutenticacaoAdslTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbHsAtendimentosAutenticacaoADSL", uniqueConstraints = @UniqueConstraint(columnNames = { "intProtocolo" }))
public class AtendimentoAutenticacaoAdslTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;

	@Column(name="intProtocolo", nullable = false)
	private Integer intProtocolo;

	@Column(name="vchAssunto", nullable = false)
	private String vchAssunto;

	@Column(name="datAtendimento", nullable = false)
	private Date datAtendimento;

	@Column(name="datRenegociacao")
	private Date datRenegociacao;

	@Column(name="vchAtendente", nullable = false)
	private String vchAtendente;

	@Column(name="txtObservacao")
	private String txtObservacao;

	@ManyToOne
	@JoinColumn(name = "IdAutenticacaoADSL", nullable = false)
	private AutenticacaoAdslTO autenticacaoAdsl;

	public AtendimentoAutenticacaoAdslTO() {

	}

	public AtendimentoAutenticacaoAdslTO(Long id) {
		this.id = id;
	}

	@Transient
	public Serializable getKey() {
		return getId();
	}

	public void setId(Serializable id) {
		this.id = (Long) id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIntProtocolo() {
		return intProtocolo;
	}

	public void setIntProtocolo(Integer intProtocolo) {
		this.intProtocolo = intProtocolo;
	}

	public String getVchAssunto() {
		return vchAssunto;
	}

	public void setVchAssunto(String vchAssunto) {
		this.vchAssunto = vchAssunto;
	}

	public Date getDatAtendimento() {
		return datAtendimento;
	}

	public void setDatAtendimento(Date datAtendimento) {
		this.datAtendimento = datAtendimento;
	}

	public Date getDatRenegociacao() {
		return datRenegociacao;
	}

	public void setDatRenegociacao(Date datRenegociacao) {
		this.datRenegociacao = datRenegociacao;
	}

	public String getVchAtendente() {
		return vchAtendente;
	}

	public void setVchAtendente(String vchAtendente) {
		this.vchAtendente = vchAtendente;
	}

	public String getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(String txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public AutenticacaoAdslTO getAutenticacaoAdsl() {
		return autenticacaoAdsl;
	}

	public void setAutenticacaoAdsl(AutenticacaoAdslTO autenticacaoAdsl) {
		this.autenticacaoAdsl = autenticacaoAdsl;
	}

}
