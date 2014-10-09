/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cepgo.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marcelocaser
 */
@Entity
@Table(name = "cidades")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cidades.findAll", query = "SELECT c FROM Cidades c"),
    @NamedQuery(name = "Cidades.findById", query = "SELECT c FROM Cidades c WHERE c.id = :id"),
    @NamedQuery(name = "Cidades.findByCep", query = "SELECT c FROM Cidades c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cidades.findByCodigoIbge", query = "SELECT c FROM Cidades c WHERE c.codigoIbge = :codigoIbge"),
    @NamedQuery(name = "Cidades.findByEstadoCod", query = "SELECT c FROM Cidades c WHERE c.estadoCod = :estadoCod"),
    @NamedQuery(name = "Cidades.findByNome", query = "SELECT c FROM Cidades c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cidades.findByUf", query = "SELECT c FROM Cidades c WHERE c.uf = :uf")})
public class Cidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "codigo_ibge")
    private String codigoIbge;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estado_cod")
    private int estadoCod;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "nome")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "uf")
    private String uf;

    public Cidades() {
    }

    public Cidades(Integer id) {
        this.id = id;
    }

    public Cidades(Integer id, String cep, String codigoIbge, int estadoCod, String nome, String uf) {
        this.id = id;
        this.cep = cep;
        this.codigoIbge = codigoIbge;
        this.estadoCod = estadoCod;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCodigoIbge() {
        return codigoIbge;
    }

    public void setCodigoIbge(String codigoIbge) {
        this.codigoIbge = codigoIbge;
    }

    public int getEstadoCod() {
        return estadoCod;
    }

    public void setEstadoCod(int estadoCod) {
        this.estadoCod = estadoCod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cidades)) {
            return false;
        }
        Cidades other = (Cidades) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cepgo.entity.Cidades[ id=" + id + " ]";
    }

}
