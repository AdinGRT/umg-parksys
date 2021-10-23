package com.analisisii.g3.parqueo.clientes;

import com.analisisii.g3.parqueo.dominio.Monitor;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author gian_
 */
public class ConexionSocketMonitor {
    
    public static boolean conectarAlServidor(String ip, int puerto, Socket socket, Monitor monitor, String solicitud) {
        boolean estaConectado = false;
        try {
            socket = new Socket(ip, puerto);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            
            //Enviar info al Servidor
            dos.writeUTF(solicitud);
            
            //Recibir Respuesta del Servidor
            String respuesta = dis.readUTF();
            
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            //Enviar info al Servidor
            oos.writeObject(monitor);
            
            System.out.println("Servidor: " + respuesta);
            if(respuesta.equals("OK")){
                estaConectado = true;
            } else {
                estaConectado = false;
            }
            
            dis.close();
            dos.close();
            oos.close();
            socket.close();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            return estaConectado;
        }
    }
}
