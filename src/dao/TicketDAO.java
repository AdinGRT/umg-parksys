package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import modelo.Ticket;
import modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class TicketDAO {
    private static final String SQL_INSERT_INGRESO = "INSERT INTO ticket(horario_entrada, id_usuario, id_vehiculo, id_ticket_status) "
            + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_SALIDA = "UPDATE ticket SET horario_salida = ? WHERE id_ticket = ?";
    
    public int insertar(Ticket ticket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT_INGRESO);
            stmt.setString(1, ticket.getHorarioEntrada());
            stmt.setInt(2, ticket.getIdUsuario());
            stmt.setInt(3, ticket.getIdVehiculo());
            stmt.setInt(4, ticket.getIdTicketStatus());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rows;
    }
    
    public int actualizar(Ticket ticket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_UPDATE_SALIDA);
            
            stmt.setString(1, ticket.getHorarioSalida());
            stmt.setInt(2, ticket.getIdTicket());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return rows;
    }
    
    
}
