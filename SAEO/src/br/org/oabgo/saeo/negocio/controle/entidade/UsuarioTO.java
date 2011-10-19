package br.org.oabgo.saeo.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdUsuarios", uniqueConstraints = @UniqueConstraint(columnNames={"vchLogin","vchEmail"}))
public class UsuarioTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "vchNome", nullable = false)
	private String vchNome;
	
	@Column(name = "vchLogin", nullable = false)
	private String vchLogin;
	
	@Column(name = "vchEmail", nullable = false)
	private String vchEmail;
	
	@Column(name = "imgFoto")
	private Blob imgFoto;
	
	@Column(name = "bitAtivo")
	private Boolean bitAtivo;
	
	public UsuarioTO() {
	}
	
	public UsuarioTO(Long id) {
		this.id = id;
	}
	
	// *** Gets and Sets ***
	
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

	public String getVchNome() {
		return vchNome;
	}

	public void setVchNome(String vchNome) {
		this.vchNome = vchNome;
	}

	public String getVchLogin() {
		return vchLogin;
	}

	public void setVchLogin(String vchLogin) {
		this.vchLogin = vchLogin;
	}

	public String getVchEmail() {
		return vchEmail;
	}

	public void setVchEmail(String vchEmail) {
		this.vchEmail = vchEmail;
	}

	public Blob getImgFoto() {
		return imgFoto;
	}

	public void setImgFoto(Blob imgFoto) {
		this.imgFoto = imgFoto;
	}

	public Boolean getBitAtivo() {
		return bitAtivo;
	}

	public void setBitAtivo(Boolean bitAtivo) {
		this.bitAtivo = bitAtivo;
	}
	
}