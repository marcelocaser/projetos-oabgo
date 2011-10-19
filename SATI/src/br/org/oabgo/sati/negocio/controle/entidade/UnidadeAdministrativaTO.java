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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> UnidadeAdministrativaTO.java <br>
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
@Table(name = "tbCdUnidadesAdministrativas", uniqueConstraints = @UniqueConstraint(columnNames={"vchSigla"}))
public class UnidadeAdministrativaTO extends TransferObject {
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	 
	@Column(name = "vchSigla", nullable = false)
	private String vchSigla;
	 
	@Column(name = "vchNome", nullable = false)
	private String vchNome;
	 
	@ManyToOne
	@JoinColumn(name = "IdTipoUnidadeAdministrativa", nullable = false)
	private TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa;
	
	@ManyToOne
	@JoinColumn(name = "IdUnidadeAdmSuperior")
	private UnidadeAdministrativaTO unidadeAdministrativaSuperior;
	 
	@OneToMany(mappedBy = "unidadeAdministrativa", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private Set<UsuarioUnidadeAdministrativaTO> 	listaUsuarioUnidadeAdm;
	
	
	public UnidadeAdministrativaTO() {
	}

	public UnidadeAdministrativaTO(Long id){
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

	public String getVchSigla() {
		return vchSigla;
	}

	public void setVchSigla(String vchSigla) {
		this.vchSigla = vchSigla;
	}

	public String getVchNome() {
		return vchNome;
	}

	public void setVchNome(String vchNome) {
		this.vchNome = vchNome;
	}

	public TipoUnidadeAdministrativaTO getTipoUnidadeAdministrativa() {
		return tipoUnidadeAdministrativa;
	}

	public void setTipoUnidadeAdministrativa(
			TipoUnidadeAdministrativaTO tipoUnidadeAdministrativa) {
		this.tipoUnidadeAdministrativa = tipoUnidadeAdministrativa;
	}

	public UnidadeAdministrativaTO getUnidadeAdministrativaSuperior() {
		return unidadeAdministrativaSuperior;
	}

	public void setUnidadeAdministrativaSuperior(
			UnidadeAdministrativaTO unidadeAdministrativaSuperior) {
		this.unidadeAdministrativaSuperior = unidadeAdministrativaSuperior;
	}

	public Set<UsuarioUnidadeAdministrativaTO> getListaUsuarioUnidadeAdm() {
		return listaUsuarioUnidadeAdm;
	}

	public void setListaUsuarioUnidadeAdm(
			Set<UsuarioUnidadeAdministrativaTO> listaUsuarioUnidadeAdm) {
		this.listaUsuarioUnidadeAdm = listaUsuarioUnidadeAdm;
	}

}
 
