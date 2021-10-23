package com.analisisii.g3.parqueo.servidor;

/**
 *
 * @author gian_
 */
public class TestServer {

    public static void main(String[] args) {
        Servidor server = new Servidor(5000);
        Thread hilo = new Thread(server);
        hilo.start();

    }
}
