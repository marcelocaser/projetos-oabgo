package br.com.cepgo.cepgo.service;

import br.com.cepgo.cepgo.Enderecos;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
@Stateless
@Path("cep")
public class EnderecosFacadeREST extends AbstractFacade<Enderecos> {

    @PersistenceContext(unitName = "br.com.cepgo_CEPGo_war_1.0.0PU")
    private EntityManager em;

    public EnderecosFacadeREST() {
        super(Enderecos.class);
    }

    @POST
    @Override
    @Consumes({"application/xml", "application/json"})
    public void create(Enderecos entity) {
        super.create(entity);
    }

    @PUT
    @Path("{id}")
    @Consumes({"application/xml", "application/json"})
    public void edit(@PathParam("id") Integer id, Enderecos entity) {
        super.edit(entity);
    }

    @DELETE
    @Path("{id}")
    public void remove(@PathParam("id") Integer id) {
        super.remove(super.find(id));
    }

    /*@GET
     @Path("{id}")
     @Produces({"application/xml", "application/json"})
     public Enderecos find(@PathParam("id") Integer id) {
     return super.find(id);
     }*/
    @GET
    @Path("{cep}.json")
    @Produces({"application/json"})
    public Enderecos cepJSON(@PathParam("cep") String cep) {
        return consultaCEP(cep);
    }

    @GET
    @Path("{cep}.xml")
    @Produces({"application/xml"})
    public Enderecos cepXML(@PathParam("cep") String cep) {
        return consultaCEP(cep);
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<Enderecos> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<Enderecos> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    private Enderecos consultaCEP(String cep) {
        Enderecos enderecos;
        try {
            enderecos = getEntityManager().createQuery("SELECT e FROM Enderecos e WHERE e.cep = :cep", Enderecos.class)
                    .setParameter("cep", cep)
                    .getSingleResult();
        } catch (NoResultException ex) {
           return null;
        }
        return enderecos;
    }

}
