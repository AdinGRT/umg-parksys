package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    private static final String SQL_SELECT_ULTIMO_TICKET = "SELECT * FROM ticket WHERE id_vehiculo = ? ORDER BY id_ticket DESC LIMIT 1";
    
    public Ticket verUltimoTicket(int idVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Ticket ticket = new Ticket();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_ULTIMO_TICKET);
            stmt.setInt(1, idVehiculo);
            rs = stmt.executeQuery();
            while(rs.next()) {
                ticket.setIdTicket(rs.getInt(1));
                ticket.setHorarioEntrada(rs.getString(2));
                ticket.setHorarioSalida(rs.getString(3));
                ticket.setMontoTicket(rs.getDouble(4));
                ticket.setIdVehiculo(rs.getInt(5));
                ticket.setIdUsuario(rs.getInt(6));
                ticket.setIdTicketStatus(rs.getInt(7));
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                Conexion.desconectar(stmt);
                Conexion.desconectar(conn);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        
        return ticket;
    }
        
        
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
