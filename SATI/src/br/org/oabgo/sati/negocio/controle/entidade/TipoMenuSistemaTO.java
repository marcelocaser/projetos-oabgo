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

import core.dao.TransferObject;

/**
 * <b>Classe:</b> TipoMenuSistemaTO.java <br>
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
@Table(name = "tbCdTipoMenuSistemas")
public class TipoMenuSistemaTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchNome", nullable = false)
	private String vchNome;

	@Column(name="vchDescricao", nullable = false)
	private String vchDescricao;
	
	@ManyToOne
	@JoinColumn(name = "IdSistema", nullable = false)
	private SistemaTO sistema;
	
	//associação que indica quais os menus estão ligados ao tipo
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="IdTipoMenuSistema")
	private Set<MenuSistemaTO> listaMenu;

	
	public TipoMenuSistemaTO() {
	}
	
	public TipoMenuSistemaTO(Long id) {
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

	public SistemaTO getSistema() {
		return sistema;
	}

	public void setSistema(SistemaTO sistema) {
		this.sistema = sistema;
	}

	public Set<MenuSistemaTO> getListaMenu() {
		return listaMenu;
	}

	public void setListaMenu(Set<MenuSistemaTO> listaMenu) {
		this.listaMenu = listaMenu;
	}

}
