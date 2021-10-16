package com.analisisii.g3.parqueo.main;

import com.analisisii.g3.parqueo.controlador.ControladorIngresoVehiculo;
import com.analisisii.g3.parqueo.controlador.ControladorLogin;
import com.analisisii.g3.parqueo.controlador.ControladorSeleccionarVehiculo;
import com.analisisii.g3.parqueo.dao.TicketDAO;
import com.analisisii.g3.parqueo.dao.VehiculoDAO;
import java.sql.*;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import com.analisisii.g3.parqueo.vista.Login;
import com.analisisii.g3.parqueo.vista.PantallaTodoEnUno;

/**
 *
 * @author gian_
 */
public class Main {
    public static void main(String[] args) {
//        Connection conexion = null;
//        VehiculoDAO vehiculoDao = new VehiculoDAO();
//
//        Vehiculo vehiculo = new Vehiculo("110AAA", "Toyota", "Corola", "Negro", 2);
//
//        int resultado = vehiculoDao.insertar(vehiculo);
//
//        System.out.println(resultado);

//        RegistroDeParqueo ticket = new RegistroDeParqueo(1, 1, 2);
//        System.out.println(ticket.getHorarioEntrada());
//        
//        TicketDAO ticketDao = new TicketDAO();
//        
//        int resultado = ticketDao.insertar(ticket);
//        
//        System.out.println(resultado);
//        
//        System.out.println("COBRAR EL TICKET");
        
//        Vehiculo vehiculo = null;
//        VehiculoDAO vehiculoDao = new VehiculoDAO();
//        
//        vehiculo = vehiculoDao.buscarPorPlaca("110AAB", 2);
//        
//        System.out.println(vehiculo.getMarca());
        

        //VALIDAR NO. TICKET
            
            
        //DETERMINAR HORARIO DE SALIDA
//        RegistroDeParqueo ticket = new RegistroDeParqueo();
//        
//        ticket.setIdTicket(2);
//        ticket.setHorarioSalida();
//        System.out.println(ticket.getHorarioSalida());
//        
//        TicketDAO ticketDao = new TicketDAO();
//        
//        int resultado = ticketDao.actualizar(ticket);
        
        //DETERMINAR EL MONTO SEGUN TARIFARIO
            
            
        //UPDATE CON LOS DATOS DE HORARIO SALIDA Y MONTO, CAMBIAR STATUS A PAGADO(2)
        
        
        
        //RECEPCION DEL TICKET,
        //VALIDAR TICKET
        //DETERMINAR SI ESTA PAGADO
        //SI ESTA PAGADO DARLE SALIDA, UPDATE ticket STATUS A RECIBIDO(3)
        
        //AgregarVehiculo agregarVehiculo = new AgregarVehiculoDialog();
        //agregarVehiculo.setVisible(true);
        //ControladorIngresoVehiculo conInVe = new ControladorIngresoVehiculo(agregarVehiculo, 2, 1);
//        PantallaTodoEnUno seleccionarVehiculo = new PantallaTodoEnUno();
//        seleccionarVehiculo.setVisible(true);
//        ControladorSeleccionarVehiculo conSeleV = new ControladorSeleccionarVehiculo(seleccionarVehiculo, 1);
          Login login = new Login();
          login.setVisible(true);
          ControladorLogin controlador = new ControladorLogin(login);
          
    }
    
}
