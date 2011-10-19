package br.org.oabgo.siged.negocio.controle.entidade;

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
	
	@Column(name = "vchSenha", unique = true)
	private String vchSenha;
	
	@Column(name = "vchEmail", nullable = false)
	private String vchEmail;
	
	@Column(name = "imgFoto")
	private byte[] imgFoto;
	
	@Column(name = "bitAtivo")
	private Boolean bitAtivo;
	
	@Column(name = "bitMembro")
	private Boolean bitMembro;
	
	@Column(name = "bitUsuarioSIGED")
	private Boolean bitUsuarioSIGED;
	
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
	
	public String getVchSenha() {
		return vchSenha;
	}

	public void setVchSenha(String vchSenha) {
		this.vchSenha = vchSenha;
	}

	public String getVchEmail() {
		return vchEmail;
	}

	public void setVchEmail(String vchEmail) {
		this.vchEmail = vchEmail;
	}

	public byte[] getImgFoto() {
		return imgFoto;
	}

	public void setImgFoto(byte[] imgFoto) {
		this.imgFoto = imgFoto;
	}

	public Boolean getBitAtivo() {
		return bitAtivo;
	}

	public void setBitAtivo(Boolean bitAtivo) {
		this.bitAtivo = bitAtivo;
	}

	public Boolean getBitMembro() {
		return bitMembro;
	}

	public void setBitMembro(Boolean bitMembro) {
		this.bitMembro = bitMembro;
	}
	
	public Boolean getBitUsuarioSIGED() {
		return bitUsuarioSIGED;
	}

	public void setBitUsuarioSIGED(Boolean bitUsuarioSIGED) {
		this.bitUsuarioSIGED = bitUsuarioSIGED;
	}

}