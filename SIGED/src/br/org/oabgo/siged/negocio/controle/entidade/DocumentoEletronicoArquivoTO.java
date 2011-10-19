package br.org.oabgo.siged.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import core.dao.TransferObject;

@SuppressWarnings("serial")
@Entity
@Table(name = "tbCdDocumentosEletronicosArquivos", uniqueConstraints = @UniqueConstraint(columnNames={"vchNome"}))
public class DocumentoEletronicoArquivoTO extends TransferObject {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "vchNome", nullable = false)
	private String vchNome;
	
	@Column(name = "vchNomeAnterior", nullable = false)
	private String vchNomeAnterior;
	
	@Column(name = "vchMimeType", nullable = false)
	private String vchMimeType;
	
	@Column(name = "vchConteudo", nullable = false)
	private String vchConteudo;
	
	@Column(name = "vchCaminho", nullable = false)
	private String vchCaminho;
	
	@Column(name = "intPagina", nullable = false)
	private Integer intPagina;
	
	@Column(name = "intTamanho", nullable = false)
	private Integer intTamanho;
	
	@Column(name = "bitArquivoExcluido", nullable = false)
	private Boolean bitArquivoExcluido;
	
	@Column(name = "bitAcessoAnonimo", nullable = false)
	private Boolean bitAcessoAnonimo;
	
	@Column(name = "imgDados")
	private Blob imgDados;
	
	@Column(name = "datArquivo", nullable = false, columnDefinition = "date default GETDATE()", insertable = false)
	private Date datArquivo;
	
	@ManyToOne
	@JoinColumn(name = "IdTipoDocumento", nullable = false)
	private TipoDocumentoTO tipoDocumento;
	
	@ManyToOne
	@JoinColumn(name = "IdDocumentoEletronico", nullable = false)
	private DocumentoEletronicoTO documentoEletronico;
	
	public DocumentoEletronicoArquivoTO() {
		
	}
	
	public DocumentoEletronicoArquivoTO(Long id) {
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

	public String getVchNomeAnterior() {
		return vchNomeAnterior;
	}

	public void setVchNomeAnterior(String vchNomeAnterior) {
		this.vchNomeAnterior = vchNomeAnterior;
	}

	public String getVchMimeType() {
		return vchMimeType;
	}

	public void setVchMimeType(String vchMimeType) {
		this.vchMimeType = vchMimeType;
	}

	public String getVchConteudo() {
		return vchConteudo;
	}

	public void setVchConteudo(String vchConteudo) {
		this.vchConteudo = vchConteudo;
	}

	public String getVchCaminho() {
		return vchCaminho;
	}

	public void setVchCaminho(String vchCaminho) {
		this.vchCaminho = vchCaminho;
	}

	public Integer getIntPagina() {
		return intPagina;
	}

	public void setIntPagina(Integer intPagina) {
		this.intPagina = intPagina;
	}

	public Integer getIntTamanho() {
		return intTamanho;
	}

	public void setIntTamanho(Integer intTamanho) {
		this.intTamanho = intTamanho;
	}

	public Boolean getBitArquivoExcluido() {
		return bitArquivoExcluido;
	}

	public void setBitArquivoExcluido(Boolean bitArquivoExcluido) {
		this.bitArquivoExcluido = bitArquivoExcluido;
	}

	public Boolean getBitAcessoAnonimo() {
		return bitAcessoAnonimo;
	}

	public void setBitAcessoAnonimo(Boolean bitAcessoAnonimo) {
		this.bitAcessoAnonimo = bitAcessoAnonimo;
	}

	public Blob getImgDados() {
		return imgDados;
	}

	public void setImgDados(Blob imgDados) {
		this.imgDados = imgDados;
	}

	public Date getDatArquivo() {
		return datArquivo;
	}

	public void setDatArquivo(Date datArquivo) {
		this.datArquivo = datArquivo;
	}

	public TipoDocumentoTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(TipoDocumentoTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public DocumentoEletronicoTO getDocumentoEletronico() {
		return documentoEletronico;
	}

	public void setDocumentoEletronico(DocumentoEletronicoTO documentoEletronico) {
		this.documentoEletronico = documentoEletronico;
	}
	
}
