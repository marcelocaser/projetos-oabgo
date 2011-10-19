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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> PaginaTO.java <br>
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
@Table(name="tbCdPaginasSistemas")
public class PaginaTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchPagina", nullable=false, length=120)
	private String vchPagina;
	
	@Column(name="vchDescricao", nullable=false, length=500)
	private String vchDescricao;
	
	@ManyToOne
	@JoinColumn(name = "IdSistema", nullable = false)
	private SistemaTO sistema;
	
	//associação que indica as Acoes vinculadas a pagina
	@OneToMany(fetch=FetchType.LAZY, cascade=CascadeType.REMOVE)
	@JoinColumn(name="IdPagina")
	private Set<AcaoMenuTO> listaAcao;
	
	//associação que indica por quais PERFIL DE ACESSO a url pode ser acessada
	@ManyToMany(targetEntity=PerfilAcessoTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxPerfilAcessoPagina", 
		   joinColumns={@JoinColumn(name="Id")},
		   inverseJoinColumns={@JoinColumn(name="IdPerfilAcesso")})
	private Set<PerfilAcessoTO> listaPerfilAcesso;
	


	public PaginaTO() {
		
	}
	
	public PaginaTO(Long id) {
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

	public String getVchPagina() {
		return vchPagina;
	}

	public void setVchPagina(String vchPagina) {
		this.vchPagina = vchPagina;
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

	public Set<AcaoMenuTO> getListaAcao() {
		return listaAcao;
	}

	public void setListaAcao(Set<AcaoMenuTO> listaAcao) {
		this.listaAcao = listaAcao;
	}

	public Set<PerfilAcessoTO> getListaPerfilAcesso() {
		return listaPerfilAcesso;
	}

	public void setListaPerfilAcesso(Set<PerfilAcessoTO> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

}
