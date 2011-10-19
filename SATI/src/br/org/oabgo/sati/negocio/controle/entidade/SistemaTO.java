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
 * <b>Classe:</b> SistemaTO.java <br>
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
@Table(name = "tbCdSistemas")
public class SistemaTO extends TransferObject {
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	 
	@Column(name = "vchNome", nullable = false)
	private String vchNome;
	
	@Column(name = "vchSigla", nullable = false)
	private String vchSigla;
	 
	@Column(name = "vchDescricao", nullable = false)
	private String vchDescricao;
	 
	@Column(name = "vchVersao")
	private String vchVersao;
	 
	@Column(name = "chrTipo")
	private String chrTipo;
	
	@Column(name="bitSistemaPrincipal", nullable=false)
	private Boolean bitSistemaPrincipal;
	
	@Column(name="vchContextoAplicacao", nullable=false, unique=true)
	private String vchContextoAplicacao;

	@Column(name="vchUrlSistema", nullable=false, unique=true)
	private String vchUrlSistema;

	@Column(name="vchUrlPaginaPrincipal", nullable=false, unique=true)
	private String vchUrlPaginaPrincipal;
	
	@Column(name="vchNomePaginaLogin", nullable=false)
	private String vchNomePaginaLogin;
	
	@ManyToMany(targetEntity=PerfilAcessoTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxPerfilAcessoSistema", 
		   joinColumns={@JoinColumn(name="IdSistemas")},
		   inverseJoinColumns={@JoinColumn(name="IdPerfilAcesso")})
	private Set<PerfilAcessoTO> listaPerfil;
	
	
	public SistemaTO(){
	}

	public SistemaTO(Long id){
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

	public String getVchSigla() {
		return vchSigla;
	}

	public void setVchSigla(String vchSigla) {
		this.vchSigla = vchSigla;
	}

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

	public String getVchVersao() {
		return vchVersao;
	}

	public void setVchVersao(String vchVersao) {
		this.vchVersao = vchVersao;
	}

	public String getChrTipo() {
		return chrTipo;
	}

	public void setChrTipo(String chrTipo) {
		this.chrTipo = chrTipo;
	}

	public void setListaPerfil(Set<PerfilAcessoTO> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public Set<PerfilAcessoTO> getListaPerfil() {
		return listaPerfil;
	}

	public Boolean getBitSistemaPrincipal() {
		return bitSistemaPrincipal;
	}

	public void setBitSistemaPrincipal(Boolean bitSistemaPrincipal) {
		this.bitSistemaPrincipal = bitSistemaPrincipal;
	}

	public String getVchContextoAplicacao() {
		return vchContextoAplicacao;
	}

	public void setVchContextoAplicacao(String vchContextoAplicacao) {
		this.vchContextoAplicacao = vchContextoAplicacao;
	}

	public String getVchUrlSistema() {
		return vchUrlSistema;
	}

	public void setVchUrlSistema(String vchUrlSistema) {
		this.vchUrlSistema = vchUrlSistema;
	}

	public String getVchUrlPaginaPrincipal() {
		return vchUrlPaginaPrincipal;
	}

	public void setVchUrlPaginaPrincipal(String vchUrlPaginaPrincipal) {
		this.vchUrlPaginaPrincipal = vchUrlPaginaPrincipal;
	}

	public String getVchNomePaginaLogin() {
		return vchNomePaginaLogin;
	}

	public void setVchNomePaginaLogin(String vchNomePaginaLogin) {
		this.vchNomePaginaLogin = vchNomePaginaLogin;
	}

}
 
