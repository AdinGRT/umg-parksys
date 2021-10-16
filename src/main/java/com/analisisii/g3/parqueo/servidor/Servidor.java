package com.analisisii.g3.parqueo.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {

    private String ipServidor;
    private String ipCliente;

    private int numeroDePuerto;
    private ServerSocket socketServidor;
    private Socket socketCliente;

    private DataInputStream dis;
    private DataOutputStream dos;

    public Servidor(int numeroDePuerto) {
        this.numeroDePuerto = numeroDePuerto;
    }

    public void arrancarServidor() {
        while (true) {
            try {
                socketServidor = new ServerSocket(numeroDePuerto);
                System.out.println("Server Test: Esperando conexion...");
                socketCliente = socketServidor.accept();
                System.out.println("Server Test: Nueva conexion recibida...");

                //Obteniendo input y output stream
                dis = new DataInputStream(socketCliente.getInputStream());
                dos = new DataOutputStream(socketCliente.getOutputStream());

                //Asignar un hilo al cliente
                
                
            } catch (IOException ex) {
                Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void pararServidor() {

    }

}
