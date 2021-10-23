package com.analisisii.g3.parqueo.servidor;

import com.analisisii.g3.parqueo.dominio.Monitor;
import com.analisisii.g3.parqueo.dominio.PanelEntrada;
import com.analisisii.g3.parqueo.dominio.PanelSalida;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor implements Runnable {

    private int numeroDePuerto;
    private ServerSocket socketServidor;
    private Socket socketCliente;

    private String ipCliente;
    private Socket socketEnvio;

    private DataInputStream dis;
    private DataOutputStream dos;

    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    private String solicitud;
    private String respuesta;
    private List<String> datos = new ArrayList<>();
    private Map<String, String> clientesConectados;

    private PanelEntrada panelEntrada;
    private PanelSalida panelSalida;
    private Monitor monitor;

    private Map<String, RegistroDeParqueo> registroEntrante;

    //private ClienteProductor clienteProductor;
    //private ClienteConsumidor clienteConsumidor;
    public Servidor(int numeroDePuerto) {
        this.numeroDePuerto = numeroDePuerto;
        this.clientesConectados = new HashMap<>();
    }

    @Override
    public void run() {
        try {
            socketServidor = new ServerSocket(numeroDePuerto);
            registroEntrante = new HashMap<>();
            while (true) {

                System.out.println("Server Test: Esperando conexion...");
                socketCliente = socketServidor.accept();
                System.out.println("Server Test: Nueva conexion recibida...");

                //Obteniendo input y output stream                
                dis = new DataInputStream(socketCliente.getInputStream());
                dos = new DataOutputStream(socketCliente.getOutputStream());

                //Recibir info cliente
                solicitud = dis.readUTF();
                System.out.println("Cliente: " + solicitud);

                if (solicitud.equalsIgnoreCase("ticket")) {
                    GestorClienteEntrada clienteEntrada = new GestorClienteEntrada(socketCliente, registroEntrante);
                    Thread threadEntrada = new Thread(clienteEntrada);
                    threadEntrada.start();
                }
                
                if (solicitud.equalsIgnoreCase("ingreso")) {
                    GestorClienteMonitor clienteMonitor = new GestorClienteMonitor(socketCliente, registroEntrante);
                    Thread threadMonitor = new Thread(clienteMonitor);
                    threadMonitor.start();
                }

                //Obtener Ip
                //Recibir Tipo Cliente
                if (solicitud.equalsIgnoreCase("entrada")) {
                    //Respuesta
                    respuesta = "OK";
                    dos.writeUTF(respuesta);

                    InetAddress inetAdress = socketCliente.getInetAddress();
                    ipCliente = inetAdress.getHostAddress();
                    System.out.println("Online: " + ipCliente);
                    ois = new ObjectInputStream(socketCliente.getInputStream());
                    panelEntrada = (PanelEntrada) ois.readObject();
                    System.out.println("Test Server: " + panelEntrada);
                    clientesConectados.put(panelEntrada.getDescripcionPanel(), ipCliente);

                } else if (solicitud.equalsIgnoreCase("salida")) {
                    //Respuesta
                    respuesta = "OK";
                    dos.writeUTF(respuesta);
                    InetAddress inetAdress = socketCliente.getInetAddress();
                    ipCliente = inetAdress.getHostAddress();
                    System.out.println("Online: " + ipCliente);
                    ois = new ObjectInputStream(socketCliente.getInputStream());
                    panelSalida = (PanelSalida) ois.readObject();
                    clientesConectados.put(panelSalida.getDescripcionPanel(), ipCliente);
                } else if (solicitud.equalsIgnoreCase("monitor")) {
                    //Respuesta
                    respuesta = "OK";
                    dos.writeUTF(respuesta);
                    InetAddress inetAdress = socketCliente.getInetAddress();
                    ipCliente = inetAdress.getHostAddress();
                    System.out.println("Online: " + ipCliente);
                    ois = new ObjectInputStream(socketCliente.getInputStream());
                    monitor = (Monitor) ois.readObject();
                    clientesConectados.put(monitor.getDescripcionMonitor(), ipCliente);
                }

                //Recuperar Mapa
                Set<String> clientes = clientesConectados.keySet();
                for (String cliente : clientes) {
                    System.out.println(cliente + " -> " + clientesConectados.get(cliente));
                }

                /*
                //Asignar un hilo al cliente
                if (mensaje.equals("productor")) {
                    clienteProductor = new ClienteProductor(socketCliente, datos);
                    Thread threadProductor = new Thread(clienteProductor);
                    threadProductor.start();
                } else if (mensaje.equals("consumidor")) {
                    System.out.println("Test Server: Entro al else...");
                    clienteConsumidor = new ClienteConsumidor(socketCliente, datos);
                    Thread threadConsumidor = new Thread(clienteConsumidor);
                    System.out.println("Test Server: Creo el hilo...");
                    threadConsumidor.start();
                    
                }*/
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
