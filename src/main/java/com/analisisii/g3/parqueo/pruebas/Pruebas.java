package com.analisisii.g3.parqueo.pruebas;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.controlador.ParqueoLogica;
import com.analisisii.g3.parqueo.dao.RegistroDeParqueoDAO;
import com.analisisii.g3.usuarios.constantes.TipoDeRol;
import com.analisisii.g3.parqueo.dao.UsuarioLoginDAO;
import com.analisisii.g3.parqueo.dao.VehiculoDAO;
import com.analisisii.g3.parqueo.dominio.Parqueo;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import com.analisisii.g3.usuarios.modelo.UsuarioLogin;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class Pruebas {

    public static void main(String[] args) {

        Vehiculo vehiculo = new Vehiculo("999XXX", TipoDeVehiculo.PARTICULAR);
        System.out.println("vehiculo antes de insertar = " + vehiculo);
        
        VehiculoDAO vehiculoDao = new VehiculoDAO();
        
        if(vehiculoDao.insertVehiculo(vehiculo) == 1) {
            System.out.println("vehiculo despues de insertar = " + vehiculo);
        } else {
            System.out.println("NO SE INSERTO VEHICULO...");
        }
        
        RegistroDeParqueoDAO registro = new RegistroDeParqueoDAO();
        System.out.println(registro.contarEspaciosOcupados(2));
        
        ParqueoLogica parqueo = new ParqueoLogica();
        
        
        System.out.println("PARTICULARES = " + parqueo.getCantidadParticulares());
        System.out.println("MOTOCICLETAS = " + parqueo.getCantidadMotocicletas());
        System.out.println("CAPACIDAD PARTICULARES = " + parqueo.getParqueo().getMaximoVehiculos());
        System.out.println("CAPACIDAD MOTOCICLETAS = " + parqueo.getParqueo().getMaximoMotocicletas());
        System.out.println("ESTA LLENO? = " + parqueo.getParqueo().estaLleno(TipoDeVehiculo.PARTICULAR));
        
        
        Vehiculo vehiculo2;
        vehiculo2 = vehiculoDao.buscarPorMatricula("111PPP", TipoDeVehiculo.MOTOCICLETA.ordinal());
        if(vehiculo2.getMatricula() != null) {
            System.out.println("vehiculo2 = " + vehiculo2);
        }
        
        
        System.out.println(TipoDeVehiculo.valueOf("PARTICULAR").ordinal());
        
        
        
        
        
        /*
        try {
            //Pruebas de Login
            UsuarioLoginDAO login = new UsuarioLoginDAO();
            UsuarioLogin usuarioLogin = new UsuarioLogin();
            usuarioLogin = login.validarUsuario("arubio", "1345", 2);
            
            System.out.println(usuarioLogin);
        //Prueba de Registro de Vehiculo
        //Iniciar aplicacion
        //Servidor
        ParqueoLogica parqueoLogica = new ParqueoLogica();
        RegistroDeParqueo registro;

        //Cliente Monitor
        Vehiculo vehiculo = new Vehiculo("111BBB", TipoDeVehiculo.PARTICULAR);
        System.out.println("Monitor -> Ingresando: " + vehiculo);

        //Cliente Panel
        String opcion = "TICKET";
        System.out.println("Panel de Entrada -> Dame un ticket.");

        //Servidor
        if (opcion.equals("TICKET")) {
            System.out.println("Servidor -> Procesando...");
            registro = parqueoLogica.crearRegistro(vehiculo);
            if (registro != null) {
                //Enviar registro al cliente panel
                //El Cliente Panel imprime
                System.out.println("Panel Entrada -> Imprimiendo: " + registro);
                //Si todo sale bien devuelve mensaje para almacenar en base de datos
                parqueoLogica.persistirRegistro(registro);
                System.out.println("Panel Entrada -> Pase adelante...");
            } else {
                System.out.println("ALGO SALIO MAL");
            }

        }
        vehiculo = new Vehiculo("333ZZZ", TipoDeVehiculo.PARTICULAR);
        System.out.println("Monitor -> Ingresando: " + vehiculo);

        //Cliente Panel
        opcion = "TICKET";
        System.out.println("Panel de Entrada -> Dame un ticket.");

        //Servidor
        if (opcion.equals("TICKET")) {
            System.out.println("Servidor -> Procesando...");
            registro = parqueoLogica.crearRegistro(vehiculo);
            if (registro != null) {
                //Enviar registro al cliente panel
                //El Cliente Panel imprime
                System.out.println("Panel Entrada -> Imprimiendo: " + registro);
                //Si todo sale bien devuelve mensaje para almacenar en base de datos
                parqueoLogica.persistirRegistro(registro);
                System.out.println("Panel Entrada -> Pase adelante...");
            } else {
                System.out.println("ALGO SALIO MAL");
            }

        }

        //Cliente Monitor Actualiza su lista de ingresos
        Map<String, RegistroDeParqueo> registros = parqueoLogica.getRegistrosDeParqueoActivos();
        //Recuperar Mapa
        Set<String> matriculas = registros.keySet();
        for (String matricula : matriculas) {
            System.out.println(matricula + " -> " + registros.get(matricula));
        }
        */

    }

}
