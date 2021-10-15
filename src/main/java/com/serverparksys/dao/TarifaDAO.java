package com.serverparksys.dao;

import com.serverparksys.modelo.Tarifa;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;

/**
 *
 * @author gian_
 */
public class TarifaDAO {

    private static String baseURI = "http://localhost:8080/tarifas-parksys/api/tarifas";
    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    public List<Tarifa> verTarifas() {
        ArrayList<Tarifa> tarifas = null;
        try {
            tarifas = JSON_MAPPER.readValue(new URL("http://localhost:8080/tarifas-parksys/api/tarifas"),
                    JSON_MAPPER.getTypeFactory().constructCollectionType(ArrayList.class, Tarifa.class));
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        return tarifas;
    }

    public void actualizarTarifa(Tarifa tarifa) {
        WebTarget target = getWebTarget();
        
        String id = tarifa.getIdTarifa().toString();
        Response response = target.path(id).request()
                .put(Entity.entity(tarifa, MediaType.APPLICATION_JSON), Response.class);
        System.out.println(response);
    }
    
    private static WebTarget getWebTarget() {
        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
         
        return client.target(baseURI);     
    }

}
