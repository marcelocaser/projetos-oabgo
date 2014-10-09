package br.com.cepgo.service;

import br.com.cepgo.CEP;
import br.com.cepgo.SimpleClass;
import br.com.cepgo.entity.Bairros;
import br.com.cepgo.entity.Cidades;
import br.com.cepgo.entity.Enderecos;
import static br.com.cepgo.util.RequisicaoJson.readJsonFromUrl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * <b>Classe:</b> EnderecosFacadeREST.java <br>
 * <b>Descrição:</b>     <br>
 *
 * <b>Projeto:</b> CEPGo <br>
 * <b>Pacote:</b> br.com.cepgo.cepgo.service <br>
 * <b>Empresa:</b> Flávios Calçados e Esportes <br>
 *
 * @author marcelocaser
 * @version Revision: $$ Date: 12/09/2014
 */
@Path("cep")
public class EnderecosFacadeREST extends AbstractFacade<Enderecos> {

    //@PersistenceUnit(unitName = "CEPGo_PU")
    private EntityManager em;

    @Inject
    private CEP cepConsulta;

    @Inject
    private CidadesFacadeREST cidadesFacadeREST;

    @Inject
    private SimpleClass simple;

    public EnderecosFacadeREST() {
        super(Enderecos.class);
    }

    /*@POST
     @Override
     @Consumes({"application/xml", "application/json"})
     public void create(Enderecos entity) {
     super.create(entity);
     }*/

    /*@PUT
     @Path("{id}")
     @Consumes({"application/xml", "application/json"})
     public void edit(@PathParam("id") Integer id, Enderecos entity) {
     super.edit(entity);
     }*/

    /*@DELETE
     @Path("{id}")
     public void remove(@PathParam("id") Integer id) {
     super.remove(super.find(id));
     }*/

    /*@GET
     @Path("{id}")
     @Produces({"application/xml", "application/json"})
     public Enderecos find(@PathParam("id") Integer id) {
     return super.find(id);
     }*/
    @GET
    @Path("{cep}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CEP cepJSON(@PathParam("cep") String cep) {
        
        //Busca CEP banco de dados.
        Enderecos enderecos = consultaCEP(cep);
        CEP cepRetorno = new CEP();
        if (enderecos == null) {
            //Busca CEP na base dos correios.
            enderecos = consultaCEPCorreios(cep);
        }
        if (enderecos == null) {
            return cepRetorno;
        }
        cepRetorno.setLogradouro(enderecos.getNomeclog());
        cepRetorno.setBairro(String.valueOf(enderecos.getBairroId().getNome()));
        cepRetorno.setLocalidade(String.valueOf(enderecos.getCidadeId()));
        cepRetorno.setUf(enderecos.getUf());
        cepRetorno.setCep(enderecos.getCep());
        return cepRetorno;
    }

    @GET
    @Path("{cep}.xml")
    @Produces(MediaType.APPLICATION_XML)
    public Enderecos cepXML(@PathParam("cep") String cep) {
        return consultaCEP(cep);
    }

    /*@GET
     @Override
     @Produces({"application/xml", "application/json"})
     public List<Enderecos> findAll() {
     return super.findAll();
     }*/

    /*@GET
     @Path("{from}/{to}")
     @Produces({"application/xml", "application/json"})
     public List<Enderecos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
     return super.findRange(new int[]{from, to});
     }*/

    /*@GET
     @Path("count")
     @Produces("text/plain")
     public String countREST() {
     return String.valueOf(super.count());
     }*/
    @Override
    protected EntityManager getEntityManager() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("CEPGo_PU");
        return em = factory.createEntityManager();
    }

    public Enderecos consultaCEP(String cep) {
        Enderecos enderecos = null;
        cep = isCEPValido(cep);
        if (cep != null) {
            //Incluir um traço antes de consultar
            try {
                enderecos = getEntityManager().createQuery("SELECT e FROM Enderecos e WHERE e.cep = :cep", Enderecos.class)
                        .setParameter("cep", cep)
                        .getSingleResult();
            } catch (NoResultException | java.lang.StringIndexOutOfBoundsException ex) {
                return null;
            }
        }
        return enderecos;
    }

    private Enderecos consultaCEPCorreios(String cep) {
        Enderecos enderecos = null;
        Cidades cidades = null;
        Bairros bairros = null;
        cep = isCEPValido(cep);
        if (cep != null) {
            try {
                JSONObject json = readJsonFromUrl("http://localhost/CEPGoB/?cep=".concat(cep));
                cidades = consultaCidadePeloNome(json.get("cidade").toString());
                bairros = consultaBairrosPeloNome(json.get("bairro").toString(), json.get("uf").toString());
                enderecos = new Enderecos();
                if (bairros != null) {
                    enderecos.setBairroId(bairros);
                }
                enderecos.setCep(json.get("cep").toString());
                enderecos.setCidadeId(cidades.getId());
                enderecos.setNomeclog(json.get("logradouro").toString());
                enderecos.setUf(cidades.getUf());
                enderecos.setUfCod(cidades.getEstadoCod());
                return enderecos;
            } catch (IOException ex) {
                Logger.getLogger(EnderecosFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JSONException ex) {
                Logger.getLogger(EnderecosFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return enderecos;
    }

    private String isCEPValido(String cep) {
        if (cep != null && !cep.isEmpty()) {
            cep = cep.replaceAll("[A-Za-z.-]", "");
            if (cep.trim().length() == 8) {
                return cep.substring(0, 5).concat("-").concat(cep.substring(5, 8));
            }
        }
        return null;
    }

    private Cidades consultaCidadePeloNome(String nome) {
        Cidades cidades = null;
        if (nome != null && !nome.isEmpty()) {
            try {
                cidades = getEntityManager().createQuery("SELECT c FROM Cidades c WHERE c.nome LIKE :nome", Cidades.class)
                        .setParameter("nome", nome)
                        .getSingleResult();
            } catch (NoResultException ex) {
                return null;
            } catch (java.lang.StringIndexOutOfBoundsException ex) {
                return null;
            }
        }
        return cidades;
    }

    private Bairros consultaBairrosPeloNome(String nome, String uf) {
        Bairros bairros = null;
        if (nome != null && !nome.isEmpty()) {
            try {
                bairros = getEntityManager().createQuery("SELECT b FROM Bairros b WHERE b.nome LIKE :nome", Bairros.class)
                        .setParameter("nome", nome)
                        .setParameter("uf", uf)
                        .getSingleResult();
            } catch (NoResultException | java.lang.StringIndexOutOfBoundsException ex) {
                return null;
            }
        }
        return bairros;
    }

}
