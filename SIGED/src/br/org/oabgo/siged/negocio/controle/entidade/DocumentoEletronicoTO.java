package br.org.oabgo.siged.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdDocumentosEletronicos", uniqueConstraints = @UniqueConstraint(columnNames={"vchNumeroProcesso"}))
public class DocumentoEletronicoTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "vchNumeroProcesso", nullable = false)
	private String vchNumeroProcesso;
	
	@Column(name = "datProcesso", nullable = false)
	private Date datProcesso;
	
	@Column(name = "datDecisao", nullable = false)
	private Date datDecisao;
	
	@OneToMany(mappedBy = "documentoEletronico", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
	private Set<DocumentoEletronicoArquivoTO> listaDocumentoEletronicoArquivo;
	
	@ManyToMany(targetEntity=RelatorTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxDocumentosEletronicosRelatores", 
		   joinColumns={@JoinColumn(name="IdDocumentoEletronico")},
		   inverseJoinColumns={@JoinColumn(name="IdRelator")})
	private Set<RelatorTO> listaRelator;
	
	public DocumentoEletronicoTO() {
		
	}
	
	public DocumentoEletronicoTO(Long id) {
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

	public String getVchNumeroProcesso() {
		return vchNumeroProcesso;
	}

	public void setVchNumeroProcesso(String vchNumeroProcesso) {
		this.vchNumeroProcesso = vchNumeroProcesso;
	}

	public Date getDatProcesso() {
		return datProcesso;
	}

	public void setDatProcesso(Date datProcesso) {
		this.datProcesso = datProcesso;
	}

	public Date getDatDecisao() {
		return datDecisao;
	}
	
	public void setDatDecisao(Date datDecisao) {
		this.datDecisao = datDecisao;
	}

	public Set<DocumentoEletronicoArquivoTO> getListaDocumentoEletronicoArquivo() {
		return listaDocumentoEletronicoArquivo;
	}

	public void setListaDocumentoEletronicoArquivo(
			Set<DocumentoEletronicoArquivoTO> listaDocumentoEletronicoArquivo) {
		this.listaDocumentoEletronicoArquivo = listaDocumentoEletronicoArquivo;
	}
	
	public Set<RelatorTO> getListaRelator() {
		return listaRelator;
	}

	public void setListaRelator(Set<RelatorTO> listaRelator) {
		this.listaRelator = listaRelator;
	}
}
