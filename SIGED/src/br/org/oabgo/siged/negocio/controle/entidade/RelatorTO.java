package br.org.oabgo.siged.negocio.controle.entidade;

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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdRelatores", uniqueConstraints = @UniqueConstraint(columnNames={"vchNome"}))
public class RelatorTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "vchNome", nullable = false)
	private String vchNome;
	
	@ManyToMany(targetEntity=DocumentoEletronicoTO.class, 
		    fetch=FetchType.LAZY, 
		    cascade={CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name="tbAxDocumentosEletronicosRelatores", 
		   joinColumns={@JoinColumn(name="IdRelator")},
		   inverseJoinColumns={@JoinColumn(name="IdDocumentoEletronico")})
	private Set<DocumentoEletronicoTO> listaDocumentoEletronico;
	
	public RelatorTO() {
		
	}
	
	public RelatorTO(Long id) {
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

	public Set<DocumentoEletronicoTO> getListaDocumentoEletronico() {
		return listaDocumentoEletronico;
	}

	public void setListaDocumentoEletronico(Set<DocumentoEletronicoTO> listaDocumentoEletronico) {
		this.listaDocumentoEletronico = listaDocumentoEletronico;
	}

}
