package com.analisisii.g3.parqueo.clientes;

import com.analisisii.g3.cobros.all.CobroLogica;
import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.controlador.ParqueoLogica;
import com.analisisii.g3.parqueo.controlador.VehiculoException;
import com.analisisii.g3.parqueo.modelo.RegParqueoPanel;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class ServerTCP implements Runnable {

    private int opcion;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;
    private ParqueoLogica parqueo;
    private List<Vehiculo> vehiculosEntrantes;
    private RegParqueoPanel regpp;
    private CobroLogica cobros;
    
    private Vehiculo vehiculo;
    private RegistroDeParqueo registro;
    
    public ServerTCP(Socket socket, List<Vehiculo> vehiculosEntrantes, ParqueoLogica parqueo, RegParqueoPanel regpp, CobroLogica cobros) {
        this.socket = socket;
        this.vehiculosEntrantes = vehiculosEntrantes;
        this.parqueo = parqueo;
        this.regpp = regpp;
        this.cobros = cobros;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(this.socket.getInputStream());
            dos = new DataOutputStream(this.socket.getOutputStream());
            int codSvr = dis.readInt();
            System.out.println("Server Test -> Opcion: " + codSvr);
            switch (codSvr) {
                case 0:
                    //establecerConexion();
                    break;
                case 1:
                    registrarEntrada(dis, dos);
                    break;
                case 2:
                    realizarCobro(dis, dos);
                    break;
                case 3:
                    //registrarSalida();
                    break;
                default:
                    //
                    break;
            }

        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void realizarCobro(DataInputStream dis, DataOutputStream dos){
        try {
            String cliente = dis.readUTF();
            System.out.println("Server Test -> Cliente conectado: " + cliente);
            int numeroRegistro = dis.readInt();
            boolean estaActivo = cobros.estaActivoRegistro(numeroRegistro);
            dos.writeBoolean(estaActivo);
            if(estaActivo){
                double montoAPagar = cobros.determinarMonto();
                dos.writeDouble(montoAPagar);
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }   

    public boolean validarVehiculo(DataInputStream dis, DataOutputStream dos) {
        try {
            String matriculaRecibida = dis.readUTF();
            System.out.println("Server Test -> Cliente monitor envia matricula " + matriculaRecibida);
            String tipoDeVehiculoRecibido = dis.readUTF();
            System.out.println("Server Test -> Cliente monitor envia tipo de vehiculo " + tipoDeVehiculoRecibido);
            this.vehiculo = parqueo.obtenerVehiculo(matriculaRecibida, tipoDeVehiculoRecibido);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (VehiculoException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public void registrarEntrada(DataInputStream dis, DataOutputStream dos) {
        try {
            String matricula;
            String cliente = dis.readUTF();
            System.out.println("Server Test -> Cliente conectado: " + cliente);
            if (cliente.equalsIgnoreCase("entrada")) {
                this.regpp.setIdPanelES(1);
                
                //validar metodoES
                this.regpp.setIdMetodoES(1);
                
                System.out.println("Server Test -> Cliente entrada esperando...");
                this.vehiculo = getVehiculoEntrante();
                System.out.println("Server Test -> Cliente entrada obtuvo: " + this.vehiculo);
                this.registro = this.parqueo.crearRegistro(this.vehiculo);
                System.out.println("Server Test -> Se creo el registro: " + this.vehiculo.getRegistroDeParqueo());
                //
                
                this.regpp.setIdRegistro(this.registro.getIdRegistroParqueo());
                this.parqueo.persistirRegistro(this.registro, this.regpp);
                
                String enviaMatricula = this.vehiculo.getMatricula();
                dos.writeUTF(enviaMatricula);
                String enviaFechaHoraEntrada = this.vehiculo.getRegistroDeParqueo().getFechaHoraEntrada();
                dos.writeUTF(enviaFechaHoraEntrada);
                int enviaNumTicket = this.vehiculo.getRegistroDeParqueo().getIdRegistroParqueo();
                dos.writeInt(enviaNumTicket);
            } else if (cliente.equalsIgnoreCase("monitor")) {
                this.regpp.setIdUsuario(1);
                boolean validacion = validarVehiculo(dis, dos);
                if(validacion) {
                    addVehiculoEntrante(this.vehiculo);
                }
                System.out.println("Prueba...");
                Set<String> matriculas = this.parqueo.getRegistrosDeParqueoActivos().keySet();
                dos.writeInt(matriculas.size());
                for (String m : matriculas) {
                    dos.writeUTF(m);
                }
                
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException(ex);
        } finally {
            try {
                if (dis != null) {
                    dis.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
                throw new RuntimeException(ex);
            }
        }
    }

    private Vehiculo getVehiculoEntrante() {
        synchronized (this.vehiculosEntrantes) {
            if (this.vehiculosEntrantes.size() == 0) {
                try {
                    this.vehiculosEntrantes.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ServerTCP.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Vehiculo vehiculoEntrante = this.vehiculosEntrantes.get(0);
            this.vehiculosEntrantes.remove(0);
            return vehiculoEntrante;
        }

    }

    private void addVehiculoEntrante(Vehiculo vehiculoEntrante) {
        synchronized (this.vehiculosEntrantes) {
            this.vehiculosEntrantes.add(vehiculoEntrante);
            this.vehiculosEntrantes.notify();
        }

    }
}
