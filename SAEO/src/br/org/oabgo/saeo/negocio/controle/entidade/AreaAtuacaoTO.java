package br.org.oabgo.saeo.negocio.controle.entidade;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.dao.TransferObject;

/**
 * <b>Classe:</b> AreaAtuacaoTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SAEO <br>
 * <b>Pacote:</b> br.org.oabgo.saeo.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbDsAreasAtuacoes")
public class AreaAtuacaoTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "intCodigo")
	private Integer intCodigo;
	
	@Column(name = "vchDescricao", nullable = false)
	private String vchDescricao;
	
	@Column(name = "bitExameDeOrdem")
	private Boolean bitExameDeOrdem;
	
	public AreaAtuacaoTO() {
	}
	
	public AreaAtuacaoTO(Long id) {
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
	
	public Boolean getBitExameDeOrdem() {
		return bitExameDeOrdem;
	}

	public void setBitExameDeOrdem(Boolean bitExameDeOrdem) {
		this.bitExameDeOrdem = bitExameDeOrdem;
	}
}
