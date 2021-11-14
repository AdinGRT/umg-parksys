package com.analisisii.g3.parqueo.clientes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class ServiceLocatorTCP {

    public static final String SERVER_IP = "192.168.56.1";
    public static final int SERVER_PORT = 9999;

    private List<String> matriculas;

    public ServiceLocatorTCP(List<String> matriculas) {
        this.matriculas = matriculas;
    }

    public ServiceLocatorTCP() {
    }

    public double realizarCobro(String cliente, int numeroRegistro) throws IOException {
        Socket s = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            s = new Socket(SERVER_IP, SERVER_PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            //Solicitud
            dos.writeInt(2);

            //Enviando nombre del cliente
            dos.writeUTF(cliente);

            //Enviar numeroRegistro
            dos.writeInt(numeroRegistro);
            
            //
            if(dis.readBoolean()){
               return dis.readDouble();
            }
            return 0;
            
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    

    

    public void registrarEntrada(String cliente, String tipoVehiculo, String matricula) throws IOException {
        Socket s = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        try {
            s = new Socket(SERVER_IP, SERVER_PORT);
            dis = new DataInputStream(s.getInputStream());
            dos = new DataOutputStream(s.getOutputStream());

            //Solicitud
            dos.writeInt(1);

            //Enviando nombre del cliente
            dos.writeUTF(cliente);

            if (cliente.equalsIgnoreCase("entrada")) {
                String matriculaR = dis.readUTF();
                System.out.println("Cliente Test -> Matricula recibida: " + matriculaR);
                String fechaHora = dis.readUTF();
                System.out.println("Cliente Test -> Fecha hora recibida: " + fechaHora);
                int numTicket = dis.readInt();
                System.out.println("Cliente Test -> Numero Ticket recibido: " + numTicket);
            } else if (cliente.equalsIgnoreCase("monitor")) {
                dos.writeUTF(matricula);
                System.out.println("Cliente Test -> Enviando matricula: " + matricula);
                dos.writeUTF(tipoVehiculo);
                System.out.println("Cliente Test -> Enviando tipo de vehiculo: " + tipoVehiculo);
                //System.out.println("Cliente Test -> Respuesta del Server: " + dis.readUTF());
                int it = dis.readInt();
                for (int i = 0; i < it; i++) {
                    this.matriculas.add(dis.readUTF());
                }
            }
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (s != null) {
                    s.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

}
