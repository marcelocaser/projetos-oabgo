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
@Table(name = "tbDsTiposDocumentos", uniqueConstraints = @UniqueConstraint(columnNames={"intCodigo","vchDescricao"}))
public class TipoDocumentoTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "intCodigo", nullable = false)
	private Integer intCodigo;
	
	@Column(name = "vchDescricao", nullable = false)
	private String vchDescricao;
	
	@Column(name = "bitAtivo", nullable = false)
	private Boolean bitAtivo;
	
	public TipoDocumentoTO() {
		
	}
	
	public TipoDocumentoTO(Long id) {
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

	public Integer getIntCodigo() {
		return intCodigo;
	}

	public void setIntCodigo(Integer intCodigo) {
		this.intCodigo = intCodigo;
	}

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

	public Boolean getBitAtivo() {
		return bitAtivo;
	}

	public void setBitAtivo(Boolean bitAtivo) {
		this.bitAtivo = bitAtivo;
	}

}
