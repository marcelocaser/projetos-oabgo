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
 * <b>Classe:</b> AcaoMenuTO.java <br>
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
@Table(name="tbCdAcoesMenus")
public class AcaoMenuTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="vchNome", nullable=false, length = 120)
	private String vchNome;
	
	@Column(name="vchDescricao", nullable=false, length = 120)
    private String vchDescricao;
	
	@Column(name="vchTransferObject", nullable=false, length = 50)
    private String vchTransferObject;
	
	@Column(name="vchFaseObjeto", nullable=true, length = 10)
	private String vchFaseObjeto;
	
	@Column(name="vchMetodoAlvo", nullable=true, length = 20)
	private String vchMetodoAlvo;
	
	@Column(name = "vhcClasseEstiloCss", nullable = false, length = 120)
	private String vhcClasseEstiloCss;
	
	@Column(name="vchSubmitModeJSF", nullable=true, length = 20)
	private String vchSubmitModeJSF;
	
	@Column(name="vchRenderedJSF", nullable=true, length = 20)
	private String vchRenderedJSF;
	
	@Column(name="vchRenderedIcone", nullable=true, length = 20)
	private String vchRenderedIcone;
	
	@Column(name="vchChaveTitulo", nullable=false, length = 50)
    private String vchChaveTitulo;
	
	@Column(name="vchIcone", nullable=true, length = 50)
	private String vchIcone;
	
	@Column(name="vchAcaoOnClick", nullable=true, length = 50)
	private String vchAcaoOnClick;

	//atributo que indica que se os campos INPUT serão validados pelo JSF.
	@Column(name="bitAcaoImmediate", nullable=false)
	private Boolean bitAcaoImmediate;
	
	@Column(name = "intHierarquia", nullable = false)
	private Integer intHierarquia;
	
	@Column(name="bitAgrupador", nullable=false)
	private Boolean bitAgrupador;
	
	@ManyToOne
	@JoinColumn(name = "IdAcaoMenuPai", nullable = true)
	private AcaoMenuTO acaoMenuPai;
	
	@ManyToOne
	@JoinColumn(name = "IdPagina", nullable = false)
	private PaginaTO pagina;
	
	@Column(name = "bitAtivo", nullable = false)
	private Boolean bitAtivo;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "IdAcaoMenuPai")
	@OrderBy(value="intHierarquia")
	private Set<AcaoMenuTO> listaAcaoFilho;
	
	@ManyToMany(targetEntity=PerfilAcessoTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxPerfilAcessoAcaoMenu", 
		   joinColumns={@JoinColumn(name="IdAcaoMenu")},
		   inverseJoinColumns={@JoinColumn(name="IdPerfilAcesso")})
	private Set<PerfilAcessoTO>	listaPerfilAcesso;
	
	
	public AcaoMenuTO() {
		
	}
	
	public AcaoMenuTO(Long id) {
		this.id = id;
	}
	
	@Transient
	public Serializable getKey() {
		return getId();
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setId(Serializable id) {
		this.id = (Long)id;
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

	public String getVchTransferObject() {
		return vchTransferObject;
	}

	public void setVchTransferObject(String vchTransferObject) {
		this.vchTransferObject = vchTransferObject;
	}

	public String getVchFaseObjeto() {
		return vchFaseObjeto;
	}

	public void setVchFaseObjeto(String vchFaseObjeto) {
		this.vchFaseObjeto = vchFaseObjeto;
	}

	public String getVchMetodoAlvo() {
		return vchMetodoAlvo;
	}

	public void setVchMetodoAlvo(String vchMetodoAlvo) {
		this.vchMetodoAlvo = vchMetodoAlvo;
	}

	public String getVhcClasseEstiloCss() {
		return vhcClasseEstiloCss;
	}

	public void setVhcClasseEstiloCss(String vhcClasseEstiloCss) {
		this.vhcClasseEstiloCss = vhcClasseEstiloCss;
	}

	public String getVchSubmitModeJSF() {
		return vchSubmitModeJSF;
	}

	public void setVchSubmitModeJSF(String vchSubmitModeJSF) {
		this.vchSubmitModeJSF = vchSubmitModeJSF;
	}

	public String getVchRenderedJSF() {
		return vchRenderedJSF;
	}

	public void setVchRenderedJSF(String vchRenderedJSF) {
		this.vchRenderedJSF = vchRenderedJSF;
	}

	public String getVchRenderedIcone() {
		return vchRenderedIcone;
	}

	public void setVchRenderedIcone(String vchRenderedIcone) {
		this.vchRenderedIcone = vchRenderedIcone;
	}

	public String getVchChaveTitulo() {
		return vchChaveTitulo;
	}

	public void setVchChaveTitulo(String vchChaveTitulo) {
		this.vchChaveTitulo = vchChaveTitulo;
	}

	public String getVchIcone() {
		return vchIcone;
	}

	public void setVchIcone(String vchIcone) {
		this.vchIcone = vchIcone;
	}

	public String getVchAcaoOnClick() {
		return vchAcaoOnClick;
	}

	public void setVchAcaoOnClick(String vchAcaoOnClick) {
		this.vchAcaoOnClick = vchAcaoOnClick;
	}

	public Boolean getBitAcaoImmediate() {
		return bitAcaoImmediate;
	}

	public void setBitAcaoImmediate(Boolean bitAcaoImmediate) {
		this.bitAcaoImmediate = bitAcaoImmediate;
	}

	public Integer getIntHierarquia() {
		return intHierarquia;
	}

	public void setIntHierarquia(Integer intHierarquia) {
		this.intHierarquia = intHierarquia;
	}

	public Boolean getBitAgrupador() {
		return bitAgrupador;
	}

	public void setBitAgrupador(Boolean bitAgrupador) {
		this.bitAgrupador = bitAgrupador;
	}

	public AcaoMenuTO getAcaoMenuPai() {
		return acaoMenuPai;
	}

	public void setAcaoMenuPai(AcaoMenuTO acaoMenuPai) {
		this.acaoMenuPai = acaoMenuPai;
	}

	public Set<AcaoMenuTO> getListaAcaoFilho() {
		return listaAcaoFilho;
	}

	public void setListaAcaoFilho(Set<AcaoMenuTO> listaAcaoFilho) {
		this.listaAcaoFilho = listaAcaoFilho;
	}

	public PaginaTO getPagina() {
		return pagina;
	}

	public void setPagina(PaginaTO pagina) {
		this.pagina = pagina;
	}

	public Boolean getBitAtivo() {
		return bitAtivo;
	}

	public void setBitAtivo(Boolean bitAtivo) {
		this.bitAtivo = bitAtivo;
	}

	public Set<PerfilAcessoTO> getListaPerfilAcesso() {
		return listaPerfilAcesso;
	}

	public void setListaPerfilAcesso(Set<PerfilAcessoTO> listaPerfilAcesso) {
		this.listaPerfilAcesso = listaPerfilAcesso;
	}

}
