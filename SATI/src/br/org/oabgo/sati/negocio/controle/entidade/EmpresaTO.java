package br.org.oabgo.sati.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> EmpresaTO.java <br>
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
@Table(name = "tbCdEmpresas", uniqueConstraints = @UniqueConstraint(columnNames={"vchCNPJ"}))
public class EmpresaTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchCNPJ", nullable = false)
	private String vchCNPJ;
	
	@Column(name="vchRazaoSocial", nullable = false)
	private String vchRazaoSocial;
	
	@Column(name="vchNomeFantasia", nullable = false)
	private String vchNomeFantasia;
	
	@Column(name="vchInscricaoMunicipal")
	private String vchInscricaoMunicipal;
	
	@Column(name="vchInscricaoEstadual")
	private String vchInscricaoEstadual;
	
	@Column(name="vchObservacoes")
	private String vchObservacoes;
	
	@Column(name="vchCEP", nullable = false)
	private String vchCEP;
	
	@Column(name="vchNomeLogradouro", nullable = false)
	private String vchNomeLogradouro;
	
	@Column(name="vchNumeroImovel", nullable = false)
	private String vchNumeroImovel;
	
	@Column(name="vchQuadra")
	private String vchQuadra;
	
	@Column(name="vchLote")
	private String vchLote;
	
	@Column(name="vchComplemento")
	private String vchComplemento;
	
	@Column(name="vchNomeBairro", nullable = false)
	private String vchNomeBairro;
	
	@Column(name="vchEnderecoEletronico")
	private String vchEnderecoEletronico;
	
	public EmpresaTO() {
		
	}
	
	public EmpresaTO(Long id) {
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

	public String getVchCNPJ() {
		return vchCNPJ;
	}

	public void setVchCNPJ(String vchCNPJ) {
		this.vchCNPJ = vchCNPJ;
	}

	public String getVchRazaoSocial() {
		return vchRazaoSocial;
	}

	public void setVchRazaoSocial(String vchRazaoSocial) {
		this.vchRazaoSocial = vchRazaoSocial;
	}

	public String getVchNomeFantasia() {
		return vchNomeFantasia;
	}

	public void setVchNomeFantasia(String vchNomeFantasia) {
		this.vchNomeFantasia = vchNomeFantasia;
	}

	public String getVchInscricaoMunicipal() {
		return vchInscricaoMunicipal;
	}

	public void setVchInscricaoMunicipal(String vchInscricaoMunicipal) {
		this.vchInscricaoMunicipal = vchInscricaoMunicipal;
	}

	public String getVchInscricaoEstadual() {
		return vchInscricaoEstadual;
	}

	public void setVchInscricaoEstadual(String vchInscricaoEstadual) {
		this.vchInscricaoEstadual = vchInscricaoEstadual;
	}

	public String getVchObservacoes() {
		return vchObservacoes;
	}

	public void setVchObservacoes(String vchObservacoes) {
		this.vchObservacoes = vchObservacoes;
	}

	public String getVchCEP() {
		return vchCEP;
	}

	public void setVchCEP(String vchCEP) {
		this.vchCEP = vchCEP;
	}

	public String getVchNomeLogradouro() {
		return vchNomeLogradouro;
	}

	public void setVchNomeLogradouro(String vchNomeLogradouro) {
		this.vchNomeLogradouro = vchNomeLogradouro;
	}

	public String getVchNumeroImovel() {
		return vchNumeroImovel;
	}

	public void setVchNumeroImovel(String vchNumeroImovel) {
		this.vchNumeroImovel = vchNumeroImovel;
	}

	public String getVchQuadra() {
		return vchQuadra;
	}

	public void setVchQuadra(String vchQuadra) {
		this.vchQuadra = vchQuadra;
	}

	public String getVchLote() {
		return vchLote;
	}

	public void setVchLote(String vchLote) {
		this.vchLote = vchLote;
	}

	public String getVchComplemento() {
		return vchComplemento;
	}

	public void setVchComplemento(String vchComplemento) {
		this.vchComplemento = vchComplemento;
	}

	public String getVchNomeBairro() {
		return vchNomeBairro;
	}

	public void setVchNomeBairro(String vchNomeBairro) {
		this.vchNomeBairro = vchNomeBairro;
	}

	public String getVchEnderecoEletronico() {
		return vchEnderecoEletronico;
	}

	public void setVchEnderecoEletronico(String vchEnderecoEletronico) {
		this.vchEnderecoEletronico = vchEnderecoEletronico;
	}
	
}
