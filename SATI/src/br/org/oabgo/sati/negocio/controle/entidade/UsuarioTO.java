package br.org.oabgo.sati.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> UsuarioTO.java <br>
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
@Table(name = "tbCdUsuarios", uniqueConstraints = @UniqueConstraint(columnNames = {
		"vchLogin", "vchEmail" }))
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

	@Column(name = "bitMembro")
	private Boolean bitMembro;

	@Column(name = "bitUsuarioSIGED")
	private Boolean bitUsuarioSIGED;

	@ManyToOne
	@JoinColumn(name = "IdPerfilAcesso")
	private PerfilAcessoTO perfilAcesso;

	// associação que armazena a lista de UnidadesAdministrativas ocupadas pelo
	// usuario
	@OneToMany(mappedBy = "usuarioTO", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private Set<UsuarioUnidadeAdministrativaTO> listaUsuarioUnidadeAdm;

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
		this.id = (Long) id;
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

	public PerfilAcessoTO getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcessoTO perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	public Set<UsuarioUnidadeAdministrativaTO> getListaUsuarioUnidadeAdm() {
		return listaUsuarioUnidadeAdm;
	}

	public void setListaUsuarioUnidadeAdm(
			Set<UsuarioUnidadeAdministrativaTO> listaUsuarioUnidadeAdm) {
		this.listaUsuarioUnidadeAdm = listaUsuarioUnidadeAdm;
	}

}