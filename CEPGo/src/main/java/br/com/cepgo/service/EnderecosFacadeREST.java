package br.com.cepgo.service;

import br.com.cepgo.Enderecos;
import static br.com.cepgo.util.RequisicaoJson.readJsonFromUrl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateful;
import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
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
@Singleton
@Path("cep")
public class EnderecosFacadeREST extends AbstractFacade<Enderecos> {

    //@PersistenceUnit(unitName = "CEPGo_PU")
    private EntityManager em;

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
    public Enderecos cepJSON(@PathParam("cep") String cep) {
        return consultaCEP(cep);
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
        if (cep != null && !cep.isEmpty()) {
            cep = cep.replaceAll("[A-Za-z.-]", "");
            if (cep.trim().length() == 8) {
                //Incluir um traço antes de consultar
                try {
                    cep = cep.substring(0, 5).concat("-").concat(cep.substring(5, 8));
                    enderecos = getEntityManager().createQuery("SELECT e FROM Enderecos e WHERE e.cep = :cep", Enderecos.class)
                            .setParameter("cep", cep)
                            .getSingleResult();
                } catch (NoResultException ex) {
                    return null;
                } catch (java.lang.StringIndexOutOfBoundsException ex) {
                    return null;
                }
            }
        }
        return enderecos;
    }

    private Enderecos consultaCEPCorreios(String cep) {
        Enderecos enderecos = new Enderecos();
        try {
            JSONObject json = readJsonFromUrl("http://localhost/CEPGoB/?cep=74825140");
            System.out.println(json.get("cliente"));
            System.out.println(json.get("uf"));
            System.out.println(json.get("cidade"));
            System.out.println(json.get("logradouro"));
            System.out.println(json.get("bairro"));
            System.out.println(json.get("cep"));
            return consultaCEP(cep);
        } catch (IOException ex) {
            Logger.getLogger(EnderecosFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(EnderecosFacadeREST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return enderecos;
    }

}
