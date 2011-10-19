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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> MenuSistemaTO.java <br>
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
@Table(name = "tbCdMenuSistemas")
public class MenuSistemaTO extends TransferObject {
 
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	 
	@Column(name = "vchDescricao", nullable = false, length = 120)
	private String vchDescricao;
	
	@Column(name = "vchChaveTitulo", nullable = false, length = 120)
	private String vchChaveTitulo;
	 
	@Column(name = "vchAcaoOnClick", nullable = false, length = 120)
	private String vchAcaoOnClick;
	
	@Column(name = "vchIcone", nullable = false, length = 120)
	private String vchIcone;
	
	@Column(name = "vchMetodoAlvo", nullable = false, length = 120)
	private String vchMetodoAlvo;
	
	@Column(name = "vchClasseEstiloCss", nullable = false, length = 120)
	private String vchClasseEstiloCss;
	
	@Column(name = "bitAgrupador", nullable = false)
	private Boolean bitAgrupador;
	
	//atributo que indica que se os campos INPUT serão validados pelo JSF.
	@Column(name = "bitAcaoImmediate")
	private Boolean bitAcaoImmediate;
	
	@Column(name = "bitAtivo")
	private Boolean bitAtivo;
	 
	@Column(name = "intHierarquia", nullable = false)
	private Integer intHierarquia;
	 
	@ManyToOne
	@JoinColumn(name="IdMenuPai")
	private MenuSistemaTO menuPai;
	
	@ManyToOne
	@JoinColumn(name = "IdTipoMenuSistema")
	private TipoMenuSistemaTO tipoMenuSistema;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "IdMenuPai")
	@OrderBy(value="intHierarquia")
	private Set<MenuSistemaTO> listaMenuFilho;

	@ManyToMany(targetEntity=PerfilAcessoTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="ACS_PERFIL_ACESSO_MENU",
		   joinColumns={@JoinColumn(name="id_menu")},
		   inverseJoinColumns={@JoinColumn(name="id_perfil_acesso")})
	private Set<PerfilAcessoTO>	listaPerfilAcesso;
	
	public MenuSistemaTO() {
	}

	public MenuSistemaTO(Long id){
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

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

	public String getVchChaveTitulo() {
		return vchChaveTitulo;
	}

	public void setVchChaveTitulo(String vchChaveTitulo) {
		this.vchChaveTitulo = vchChaveTitulo;
	}

	public String getVchAcaoOnClick() {
		return vchAcaoOnClick;
	}

	public void setVchAcaoOnClick(String vchAcaoOnClick) {
		this.vchAcaoOnClick = vchAcaoOnClick;
	}

	public String getVchIcone() {
		return vchIcone;
	}

	public void setVchIcone(String vchIcone) {
		this.vchIcone = vchIcone;
	}

	public String getVchMetodoAlvo() {
		return vchMetodoAlvo;
	}

	public void setVchMetodoAlvo(String vchMetodoAlvo) {
		this.vchMetodoAlvo = vchMetodoAlvo;
	}

	public String getVchClasseEstiloCss() {
		return vchClasseEstiloCss;
	}

	public void setVchClasseEstiloCss(String vchClasseEstiloCss) {
		this.vchClasseEstiloCss = vchClasseEstiloCss;
	}

	public Boolean getBitAgrupador() {
		return bitAgrupador;
	}

	public void setBitAgrupador(Boolean bitAgrupador) {
		this.bitAgrupador = bitAgrupador;
	}

	public Boolean getBitAcaoImmediate() {
		return bitAcaoImmediate;
	}

	public void setBitAcaoImmediate(Boolean bitAcaoImmediate) {
		this.bitAcaoImmediate = bitAcaoImmediate;
	}

	public Boolean getBitAtivo() {
		return bitAtivo;
	}

	public void setBitAtivo(Boolean bitAtivo) {
		this.bitAtivo = bitAtivo;
	}

	public Integer getIntHierarquia() {
		return intHierarquia;
	}

	public void setIntHierarquia(Integer intHierarquia) {
		this.intHierarquia = intHierarquia;
	}

	public MenuSistemaTO getMenuPai() {
		return menuPai;
	}

	public void setMenuPai(MenuSistemaTO menuPai) {
		this.menuPai = menuPai;
	}

	public TipoMenuSistemaTO getTipoMenuSistema() {
		return tipoMenuSistema;
	}

	public void setTipoMenuSistema(TipoMenuSistemaTO tipoMenuSistema) {
		this.tipoMenuSistema = tipoMenuSistema;
	}

	public Set<MenuSistemaTO> getListaMenuFilho() {
		return listaMenuFilho;
	}

	public void setListaMenuFilho(Set<MenuSistemaTO> listaMenuFilho) {
		this.listaMenuFilho = listaMenuFilho;
	}

	public Set<PerfilAcessoTO> getListaPerfilAcesso() {
		return listaPerfilAcesso;
	}

	public void setListaPerfilAcesso(Set<PerfilAcessoTO> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

}
 
