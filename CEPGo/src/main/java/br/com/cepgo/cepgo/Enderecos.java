package br.com.cepgo.cepgo;

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
 * <b>Classe:</b> Enderecos.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> CEPGo <br>
 * <b>Pacote:</b> br.com.cepgo.cepgo <br>
 * <b>Empresa:</b> Flávios Calçados e Esportes <br>
 *
 * @author marcelocaser
 * @version Revision: $$ Date: 12/09/2014
 */
@Entity
@Table(name = "enderecos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Enderecos.findAll", query = "SELECT e FROM Enderecos e"),
    @NamedQuery(name = "Enderecos.findById", query = "SELECT e FROM Enderecos e WHERE e.id = :id"),
    @NamedQuery(name = "Enderecos.findByBairroId", query = "SELECT e FROM Enderecos e WHERE e.bairroId = :bairroId"),
    @NamedQuery(name = "Enderecos.findByCep", query = "SELECT e FROM Enderecos e WHERE e.cep = :cep"),
    @NamedQuery(name = "Enderecos.findByCidadeId", query = "SELECT e FROM Enderecos e WHERE e.cidadeId = :cidadeId"),
    @NamedQuery(name = "Enderecos.findByLogracompl", query = "SELECT e FROM Enderecos e WHERE e.logracompl = :logracompl"),
    @NamedQuery(name = "Enderecos.findByLogradouro", query = "SELECT e FROM Enderecos e WHERE e.logradouro = :logradouro"),
    @NamedQuery(name = "Enderecos.findByNomeclog", query = "SELECT e FROM Enderecos e WHERE e.nomeclog = :nomeclog"),
    @NamedQuery(name = "Enderecos.findByNomeslog", query = "SELECT e FROM Enderecos e WHERE e.nomeslog = :nomeslog"),
    @NamedQuery(name = "Enderecos.findByUf", query = "SELECT e FROM Enderecos e WHERE e.uf = :uf"),
    @NamedQuery(name = "Enderecos.findByUfCod", query = "SELECT e FROM Enderecos e WHERE e.ufCod = :ufCod")})
public class Enderecos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "bairro_id")
    private int bairroId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "cep")
    private String cep;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cidade_id")
    private int cidadeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "logracompl")
    private String logracompl;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "logradouro")
    private String logradouro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nomeclog")
    private String nomeclog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nomeslog")
    private String nomeslog;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "uf")
    private String uf;
    @Basic(optional = false)
    @NotNull
    @Column(name = "uf_cod")
    private int ufCod;

    public Enderecos() {
    }

    public Enderecos(Integer id) {
        this.id = id;
    }

    public Enderecos(Integer id, int bairroId, String cep, int cidadeId, String logracompl, String logradouro, String nomeclog, String nomeslog, String uf, int ufCod) {
        this.id = id;
        this.bairroId = bairroId;
        this.cep = cep;
        this.cidadeId = cidadeId;
        this.logracompl = logracompl;
        this.logradouro = logradouro;
        this.nomeclog = nomeclog;
        this.nomeslog = nomeslog;
        this.uf = uf;
        this.ufCod = ufCod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getBairroId() {
        return bairroId;
    }

    public void setBairroId(int bairroId) {
        this.bairroId = bairroId;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(int cidadeId) {
        this.cidadeId = cidadeId;
    }

    public String getLogracompl() {
        return logracompl;
    }

    public void setLogracompl(String logracompl) {
        this.logracompl = logracompl;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNomeclog() {
        return nomeclog;
    }

    public void setNomeclog(String nomeclog) {
        this.nomeclog = nomeclog;
    }

    public String getNomeslog() {
        return nomeslog;
    }

    public void setNomeslog(String nomeslog) {
        this.nomeslog = nomeslog;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getUfCod() {
        return ufCod;
    }

    public void setUfCod(int ufCod) {
        this.ufCod = ufCod;
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
        if (!(object instanceof Enderecos)) {
            return false;
        }
        Enderecos other = (Enderecos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.cepgo.cepgo.Enderecos[ id=" + id + " ]";
    }

}
