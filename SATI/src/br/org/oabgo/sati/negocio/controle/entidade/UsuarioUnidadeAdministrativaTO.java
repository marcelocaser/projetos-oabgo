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

import core.dao.TransferObject;

/**
 * <b>Classe:</b> UsuarioUnidadeAdministrativaTO.java <br>
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
@Table(name = "tbAxUsuarioUnidadesAdministrativas")
public class UsuarioUnidadeAdministrativaTO extends TransferObject {
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "idUsuario", nullable = false)
	private UsuarioTO usuarioTO;

	@ManyToOne
	@JoinColumn(name = "idUnidadeAdministrativa")
	private UnidadeAdministrativaTO unidadeAdministrativa;
	 
	
	public UsuarioUnidadeAdministrativaTO(){
	}

	public UsuarioUnidadeAdministrativaTO(Long id){
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

	public UsuarioTO getUsuarioTO() {
		return usuarioTO;
	}

	public void setUsuarioTO(UsuarioTO usuarioTO) {
		this.usuarioTO = usuarioTO;
	}

	public void setUnidadeAdministrativa(UnidadeAdministrativaTO unidadeAdministrativa) {
		this.unidadeAdministrativa = unidadeAdministrativa;
	}

	public UnidadeAdministrativaTO getUnidadeAdministrativa() {
		return unidadeAdministrativa;
	}
}
 
