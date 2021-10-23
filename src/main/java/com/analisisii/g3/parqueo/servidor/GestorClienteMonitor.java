package com.analisisii.g3.parqueo.servidor;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.controlador.ParqueoLogica;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
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
public class GestorClienteMonitor implements Runnable {

    private Socket sc;
    private DataInputStream dis;
    private DataOutputStream dos;
    //private List<String> datos = new ArrayList<>();
    private String matricula;

    private Map<String, RegistroDeParqueo> registroEntrante;

    public GestorClienteMonitor(Socket sc, Map<String, RegistroDeParqueo> registroEntrante) {
        this.sc = sc;
        this.registroEntrante = registroEntrante;
    }

    @Override
    public void run() {
        try {
            dis = new DataInputStream(sc.getInputStream());
            dos = new DataOutputStream(sc.getOutputStream());

            synchronized (registroEntrante) {
                try {
                    dis = new DataInputStream(this.sc.getInputStream());
                    matricula = dis.readUTF();
                    ParqueoLogica parqueoLogica = new ParqueoLogica();
                    RegistroDeParqueo registro;
                    Vehiculo vehiculo = new Vehiculo(matricula, TipoDeVehiculo.PARTICULAR);
                    registro = parqueoLogica.crearRegistro(vehiculo);
                    registroEntrante.put(matricula, registro);
                    registroEntrante.notify();
                    System.out.println("Servidor: se agrego el dato: " + matricula + " " + registro);
                    dos.writeUTF("REGISTRO CREADO");
                } catch (IOException ex) {
                    Logger.getLogger(GestorClienteMonitor.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(GestorClienteMonitor.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(GestorClienteMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
