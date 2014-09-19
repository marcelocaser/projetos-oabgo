/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cepgo.service;

import br.com.cepgo.CEP;
import br.com.cepgo.Enderecos;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author marcelocaser
 */
@Path("cep2")
public class CEPRest {
    
    @Inject
    EnderecosFacadeREST enderecosFacadeREST;

    @GET
    @Path("{cep}.json")
    @Produces(MediaType.APPLICATION_JSON)
    public CEP cepJSON(@PathParam("cep") String cep) {
        Enderecos enderecos = enderecosFacadeREST.consultaCEP(cep);
        CEP cepRetorno = new CEP();
        cepRetorno.setLogradouro("");
        cepRetorno.setBairro("");
        cepRetorno.setLocalidade("");
        cepRetorno.setUf("");
        cepRetorno.setCep("");
        return cepRetorno;
    }

}
