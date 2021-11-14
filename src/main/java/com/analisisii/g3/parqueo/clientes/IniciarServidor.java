package com.analisisii.g3.parqueo.clientes;

import com.analisisii.g3.cobros.all.CobroLogica;
import com.analisisii.g3.parqueo.controlador.ParqueoLogica;
import com.analisisii.g3.parqueo.modelo.RegParqueoPanel;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class IniciarServidor {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(9999);
            Socket s;
            ParqueoLogica parqueo = new ParqueoLogica();
            Map<String, RegistroDeParqueo> registros = new HashMap<>();
            List<Vehiculo> vehiculos = new ArrayList<>();
            RegParqueoPanel regpp = new RegParqueoPanel();
            CobroLogica cobros = new CobroLogica();
            while(true) {
                System.out.println("Test Server -> Esperando conexiones...");
                s = ss.accept();
                Thread thread = new Thread(new ServerTCP(s, vehiculos, parqueo, regpp, cobros));
                thread.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(IniciarServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
}
