package com.analisisii.g3.parqueo.pruebas;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.dao.RegistroDeParqueoDAO;
import com.analisisii.g3.usuarios.constantes.TipoDeRol;
import com.analisisii.g3.parqueo.dao.UsuarioLoginDAO;
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
        
        try {
            //Pruebas de Login
            /*UsuarioLoginDAO login = new UsuarioLoginDAO();
            UsuarioLogin usuarioLogin = new UsuarioLogin();
            usuarioLogin = login.validarUsuario("arubio", "1345", 2);
            
            System.out.println(usuarioLogin);*/
            
            //Prueba de Registro de Vehiculo
            Parqueo parqueo = Parqueo.obtenerInstanciaDeParqueo();
            Vehiculo vehiculo = new Vehiculo("111BBB", TipoDeVehiculo.PARTICULAR);
            System.out.println(vehiculo);
            RegistroDeParqueo registro;
            
            registro = parqueo.generarRegistroDeParqueo(vehiculo);
            vehiculo.setRegistroDeParqueo(registro);
            System.out.println(registro);
            System.out.println(vehiculo);
            
            RegistroDeParqueoDAO registroDao = new RegistroDeParqueoDAO();
            Map<String, RegistroDeParqueo> registros = new HashMap<>();
            registros = registroDao.listarRegistrosPorMatriculaEstado(1);
            
            //Recuperar Mapa
            Set<String> matriculas = registros.keySet();
            for (String matricula : matriculas) {
                System.out.println(matricula + " -> " + registros.get(matricula));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Pruebas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
}
