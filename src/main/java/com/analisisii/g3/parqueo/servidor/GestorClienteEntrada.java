package com.analisisii.g3.parqueo.servidor;

import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class GestorClienteEntrada implements Runnable {

    private Socket sc;
    private DataInputStream dis;
    private DataOutputStream dos;
    //private List<String> datos = new ArrayList<>();
    private String solicitud;
    

    private Map<String, RegistroDeParqueo> registroEntrante;

    public GestorClienteEntrada(Socket sc, Map<String, RegistroDeParqueo> registroEntrante) {
        this.sc = sc;
        this.registroEntrante = registroEntrante;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());
            
            
            synchronized (registroEntrante) {
                if (registroEntrante.isEmpty()) {
                    String mensaje = "Panel Entrada: Esperando datos...";
                    System.out.println(mensaje);
                    dos.writeUTF(mensaje);
                    registroEntrante.wait();
                }
                
                Set<String> matriculas = registroEntrante.keySet();
                ObjectOutputStream oos = new ObjectOutputStream(sc.getOutputStream());
                oos.writeObject(registroEntrante);
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(GestorClienteEntrada.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorClienteEntrada.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
