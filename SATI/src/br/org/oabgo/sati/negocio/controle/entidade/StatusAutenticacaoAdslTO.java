package br.org.oabgo.sati.negocio.controle.entidade;

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

/**
 * <b>Classe:</b> StatusAutenticacaoAdslTO.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> SATI <br>
 * <b>Pacote:</b> br.org.oabgo.sati.negocio.controle.entidade <br>
 * <b>Empresa:</b> Ordem dos Advogados do Brasil - Seção de Goiás <br>
 * 
 *    Copyright (c) 2010 OAB-GO - Todos os direitos reservados.
 * 
 * @author Marcelo Caser
 * @version Revision: $ Date: $
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "tbDsStatusAutenticacoesADSL", uniqueConstraints = @UniqueConstraint(columnNames={"tynCodigo"}))
public class StatusAutenticacaoAdslTO extends TransferObject {
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "Id", unique = true, nullable = false)
	private Long id;
	
	@Column(name="tynCodigo", nullable = false)
	private Integer tynCodigo;
	
	@Column(name="vchDescricao", nullable = false)
	private String vchDescricao;
	
	public StatusAutenticacaoAdslTO() {
		
	}
	
	public StatusAutenticacaoAdslTO(Long id) {
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
	
	public Integer getTynCodigo() {
		return tynCodigo;
	}
	
	public void setTynCodigo(Integer tynCodigo) {
		this.tynCodigo = tynCodigo;
	}

	public String getVchDescricao() {
		return vchDescricao;
	}

	public void setVchDescricao(String vchDescricao) {
		this.vchDescricao = vchDescricao;
	}

}
