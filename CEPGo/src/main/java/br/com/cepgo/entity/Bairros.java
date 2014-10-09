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
@Table(name = "bairros")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bairros.findAll", query = "SELECT b FROM Bairros b"),
    @NamedQuery(name = "Bairros.findById", query = "SELECT b FROM Bairros b WHERE b.id = :id"),
    @NamedQuery(name = "Bairros.findByCidade", query = "SELECT b FROM Bairros b WHERE b.cidade = :cidade"),
    @NamedQuery(name = "Bairros.findByNome", query = "SELECT b FROM Bairros b WHERE b.nome = :nome"),
    @NamedQuery(name = "Bairros.findByUf", query = "SELECT b FROM Bairros b WHERE b.uf = :uf")})
public class Bairros implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cidade")
    private int cidade;
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

    public Bairros() {
    }

    public Bairros(Integer id) {
        this.id = id;
    }

    public Bairros(Integer id, int cidade, String nome, String uf) {
        this.id = id;
        this.cidade = cidade;
        this.nome = nome;
        this.uf = uf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getCidade() {
        return cidade;
    }

    public void setCidade(int cidade) {
        this.cidade = cidade;
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
        if (!(object instanceof Bairros)) {
            return false;
        }
        Bairros other = (Bairros) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cepgo.entity.Bairros[ id=" + id + " ]";
    }
    
}
