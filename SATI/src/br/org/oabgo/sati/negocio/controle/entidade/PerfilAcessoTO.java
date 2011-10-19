package br.org.oabgo.sati.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> PerfilAcessoTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Leonardo Peixoto
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdPerfilAcesso")
public class PerfilAcessoTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;

	@Column(name = "vchNome", nullable = false)
	private String vchNome;

	@Column(name = "vchDescricao")
	private String vchDescricao;

	@Column(name = "bitAdministrador", nullable = false)
	private Boolean bitAdministrador;

	@Column(name = "txtObservacao")
	private String txtObservacao;

	@ManyToMany(targetEntity=SistemaTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxPerfilAcessoSistema", 
		   joinColumns={@JoinColumn(name="IdPerfilAcesso")},
		   inverseJoinColumns={@JoinColumn(name="IdSistemas")})
	private Set<SistemaTO> listaSistema;
	
	public PerfilAcessoTO() {
	}

	public PerfilAcessoTO(Long id){
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

	public String getVchNome() {
		return vchNome;
	}

	public void setVchNome(String vchNome) {
		this.vchNome = vchNome;
	}

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

	public Boolean getBitAdministrador() {
		return bitAdministrador;
	}

	public void setBitAdministrador(Boolean bitAdministrador) {
		this.bitAdministrador = bitAdministrador;
	}

	public String getTxtObservacao() {
		return txtObservacao;
	}

	public void setTxtObservacao(String txtObservacao) {
		this.txtObservacao = txtObservacao;
	}

	public Set<SistemaTO> getListaSistema() {
		return listaSistema;
	}

	public void setListaSistema(Set<SistemaTO> listaSistema) {
		this.listaSistema = listaSistema;
	}

}
