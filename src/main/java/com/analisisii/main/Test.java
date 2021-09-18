package com.analisisii.main;

import com.analisisii.controlador.ControladorIngresoVehiculo;
import com.analisisii.controlador.ControladorLogin;
import com.analisisii.controlador.ControladorSeleccionarVehiculo;
import com.analisisii.dao.TicketDAO;
import com.analisisii.dao.VehiculoDAO;
import java.sql.*;
import com.analisisii.modelo.Ticket;
import com.analisisii.modelo.Vehiculo;
import com.analisisii.vista.Login;
import com.analisisii.vista.SeleccionarVehiculo;

/**
 *
 * @author gian_
 */
public class Test {
    public static void main(String[] args) {
//        Connection conexion = null;
//        VehiculoDAO vehiculoDao = new VehiculoDAO();
//
//        Vehiculo vehiculo = new Vehiculo("110AAA", "Toyota", "Corola", "Negro", 2);
//
//        int resultado = vehiculoDao.insertar(vehiculo);
//
//        System.out.println(resultado);

//        Ticket ticket = new Ticket(1, 1, 2);
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
//        Ticket ticket = new Ticket();
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
//        SeleccionarVehiculo seleccionarVehiculo = new SeleccionarVehiculo();
//        seleccionarVehiculo.setVisible(true);
//        ControladorSeleccionarVehiculo conSeleV = new ControladorSeleccionarVehiculo(seleccionarVehiculo, 1);
          Login login = new Login();
          login.setVisible(true);
          ControladorLogin controlador = new ControladorLogin(login);
          
    }
    
}
