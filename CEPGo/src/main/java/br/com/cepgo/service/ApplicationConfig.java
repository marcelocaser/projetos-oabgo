package br.com.cepgo.service;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * <b>Classe:</b> ApplicationConfig.java <br>
 * <b>DescriÃ§Ã£o:</b>     <br>
 *
 * <b>Projeto:</b> CEPGo <br>
 * <b>Pacote:</b> br.com.cepgo.cepgo.service <br>
 * <b>Empresa:</b> FlÃ¡vios CalÃ§ados e Esportes <br>
 *
 * @author marcelocaser
 * @version Revision: $$ Date: 12/09/2014
 */
@ApplicationPath("/consulta")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method. It is automatically
     * populated with all resources defined in the project. If required, comment
     * out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(br.com.cepgo.service.EnderecosFacadeREST.class);
        resources.add(br.com.cepgo.service.EstadosFacadeREST.class);
    }

}
