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

import core.dao.TransferObject;

/**
 * <b>Classe:</b> AcessoSistemaTO.java <br>
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
@Table(name = "tbCdAcessosSistemas")
public class AcessoSistemaTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchCookie", nullable=false)
	private String vchCookie;
	
	@Column(name="vchIp", nullable=false)
	private String vchIp;
	
	@Column(name="datDataAcesso", nullable=false)
	private Date datDataAcesso;
	
	@Column(name="bitAcessoAtivo", nullable=false)
	private Boolean bitAcessoAtivo;
	
	@ManyToOne
	@JoinColumn(name="IdUsuario", nullable=false)
	private UsuarioTO usuario;

	@ManyToOne
	@JoinColumn(name="IdSistema", nullable=false)
	private SistemaTO sistema;
	
	
	public AcessoSistemaTO() {
	}

	public AcessoSistemaTO(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public Serializable getKey() {
		return getId();
	}
	
	public void setId(Serializable id) {
		this.id = (Long)id;
	}

	public String getVchCookie() {
		return vchCookie;
	}

	public void setVchCookie(String vchCookie) {
		this.vchCookie = vchCookie;
	}

	public String getVchIp() {
		return vchIp;
	}

	public void setVchIp(String vchIp) {
		this.vchIp = vchIp;
	}

	public Date getDatDataAcesso() {
		return datDataAcesso;
	}

	public void setDatDataAcesso(Date datDataAcesso) {
		this.datDataAcesso = datDataAcesso;
	}

	public Boolean getBitAcessoAtivo() {
		return bitAcessoAtivo;
	}

	public void setBitAcessoAtivo(Boolean bitAcessoAtivo) {
		this.bitAcessoAtivo = bitAcessoAtivo;
	}

	public UsuarioTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioTO usuario) {
		this.usuario = usuario;
	}


	public SistemaTO getSistema() {
		return sistema;
	}

	public void setSistema(SistemaTO sistema) {
		this.sistema = sistema;
	}

}
