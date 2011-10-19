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
 * <b>Classe:</b> AutenticacaoAdslTO.java <br>
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
@Table(name = "tbCdAutenticacoesADSL", uniqueConstraints = @UniqueConstraint(columnNames={"vchContaEmail"}))
public class AutenticacaoAdslTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchContaEmail", nullable = false)
	private String vchContaEmail;
	
	@Column(name="vchSenha", nullable = false)
	private String vchSenha;
	
	@Column(name="txtPlano")
	private String txtPlano;
	
	@Column(name="datCriacaoPlano")
	private Date datCriacaoPlano;
	
	@Column(name="datAlteracaoPlano")
	private Date datAlteracaoPlano;
	
	@Column(name="decValorServico", nullable = false)
	private Double decValorServico;
	
	@Column(name="tynDiaVencimento", nullable = false, columnDefinition = "tinyint default 20", insertable = false)
	private Integer tynDiaVencimento;
	
	@ManyToOne
	@JoinColumn(name = "IdEmpresa", nullable = false)
	private EmpresaTO empresa;
	
	@ManyToOne
	@JoinColumn(name = "IdUnidadeAdministrativa", nullable = false)
	private UnidadeAdministrativaTO unidadeAdministrativa;
	
	@ManyToOne
	@JoinColumn(name = "IdStatusAutenticacaoADSL", nullable = false)
	private StatusAutenticacaoAdslTO statusAutenticacaoADSL;
	
	public AutenticacaoAdslTO() {
		
	}
	
	public AutenticacaoAdslTO(Long id) {
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

	public String getVchContaEmail() {
		return vchContaEmail;
	}

	public void setVchContaEmail(String vchContaEmail) {
		this.vchContaEmail = vchContaEmail;
	}

	public String getVchSenha() {
		return vchSenha;
	}

	public void setVchSenha(String vchSenha) {
		this.vchSenha = vchSenha;
	}

	public String getTxtPlano() {
		return txtPlano;
	}

	public void setTxtPlano(String txtPlano) {
		this.txtPlano = txtPlano;
	}

	public Date getDatCriacaoPlano() {
		return datCriacaoPlano;
	}

	public void setDatCriacaoPlano(Date datCriacaoPlano) {
		this.datCriacaoPlano = datCriacaoPlano;
	}

	public Date getDatAlteracaoPlano() {
		return datAlteracaoPlano;
	}

	public void setDatAlteracaoPlano(Date datAlteracaoPlano) {
		this.datAlteracaoPlano = datAlteracaoPlano;
	}

	public Double getDecValorServico() {
		return decValorServico;
	}

	public void setDecValorServico(Double decValorServico) {
		this.decValorServico = decValorServico;
	}

	public Integer getTynDiaVencimento() {
		return tynDiaVencimento;
	}

	public void setTynDiaVencimento(Integer tynDiaVencimento) {
		this.tynDiaVencimento = tynDiaVencimento;
	}

	public EmpresaTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaTO empresa) {
		this.empresa = empresa;
	}

	public UnidadeAdministrativaTO getUnidadeAdministrativa() {
		return unidadeAdministrativa;
	}

	public void setUnidadeAdministrativa(
			UnidadeAdministrativaTO unidadeAdministrativa) {
		this.unidadeAdministrativa = unidadeAdministrativa;
	}

	public StatusAutenticacaoAdslTO getStatusAutenticacaoADSL() {
		return statusAutenticacaoADSL;
	}

	public void setStatusAutenticacaoADSL(
			StatusAutenticacaoAdslTO statusAutenticacaoADSL) {
		this.statusAutenticacaoADSL = statusAutenticacaoADSL;
	}
	
}
