package main;

import dao.TicketDAO;
import dao.VehiculoDAO;
import java.sql.*;
import modelo.Ticket;
import modelo.Vehiculo;

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

        Ticket ticket = new Ticket(1, 1, 1);
        System.out.println(ticket.getHorarioEntrada());
        
        TicketDAO ticketDao = new TicketDAO();
        
        int resultado = ticketDao.insertar(ticket);
        System.out.println(resultado);
        
        
    }
    
}
