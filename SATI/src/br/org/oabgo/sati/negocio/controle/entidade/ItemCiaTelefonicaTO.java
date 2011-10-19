package br.org.oabgo.sati.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

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
 * <b>Classe:</b> ItemCiaTelefonicaTO.java <br>
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
@Table(name = "tbCdItensCiasTelefonicas", uniqueConstraints = @UniqueConstraint(columnNames = { "vchNumrContratoAgrupador" }))
public class ItemCiaTelefonicaTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;

	@Column(name = "vchNumrContratoAgrupador", nullable = false)
	private String vchNumrContratoAgrupador;

	@Column(name = "vchNumrTelefone")
	private String vchNumrTelefone;

	@Column(name = "vchDescricaoServico", nullable = false)
	private String vchDescricaoServico;

	@Column(name = "decValorServico")
	private Double decValorServico;

	@Column(name = "vchDiaVencimento", nullable = false, columnDefinition = "varchar(2) default 24", insertable = false)
	private String vchDiaVencimento;

	@Column(name = "txtObservacao")
	private String txtObservacao;

	@ManyToOne
	@JoinColumn(name = "IdEmpresa", nullable = false)
	private EmpresaTO empresa;

	@ManyToOne
	@JoinColumn(name = "IdUnidadeAdministrativa", nullable = false)
	private UnidadeAdministrativaTO unidadeAdministrativa;

	public ItemCiaTelefonicaTO() {

	}

	public ItemCiaTelefonicaTO(Long id) {
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

	public String getVchNumrContratoAgrupador() {
		return vchNumrContratoAgrupador;
	}

	public void setVchNumrContratoAgrupador(String vchNumrContratoAgrupador) {
		this.vchNumrContratoAgrupador = vchNumrContratoAgrupador;
	}

	public String getVchNumrTelefone() {
		return vchNumrTelefone;
	}

	public void setVchNumrTelefone(String vchNumrTelefone) {
		this.vchNumrTelefone = vchNumrTelefone;
	}

	public String getVchDescricaoServico() {
		return vchDescricaoServico;
	}

	public void setVchDescricaoServico(String vchDescricaoServico) {
		this.vchDescricaoServico = vchDescricaoServico;
	}

	public Double getDecValorServico() {
		return decValorServico;
	}

	public void setDecValorServico(Double decValorServico) {
		this.decValorServico = decValorServico;
	}

	public String getVchDiaVencimento() {
		return vchDiaVencimento;
	}

	public void setVchDiaVencimento(String vchDiaVencimento) {
		this.vchDiaVencimento = vchDiaVencimento;
	}

	public String getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(String txtObservacao) {
		this.txtObservacao = txtObservacao;
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

}
