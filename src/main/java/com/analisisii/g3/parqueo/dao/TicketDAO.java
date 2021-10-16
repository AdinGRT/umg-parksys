package com.analisisii.g3.parqueo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class TicketDAO {
    private static final String SQL_INSERT_INGRESO = "INSERT INTO ticket(horario_entrada, id_usuario, id_vehiculo, id_ticket_status) "
            + "VALUES (?, ?, ?, ?)";
    
    private static final String SQL_UPDATE_SALIDA = "UPDATE ticket SET horario_salida = ? WHERE id_ticket = ?";
    
    private static final String SQL_SELECT_ULTIMO_TICKET = "SELECT * FROM ticket WHERE id_vehiculo = ? ORDER BY id_ticket DESC LIMIT 1";
    
    private static final String SQL_SELECT_IDTICKET = "SELECT id_ticket_status FROM ticket WHERE id_ticket = ?";
    
    private static final String SQL_UPDATE_TICKETSTATUS = "UPDATE ticket SET id_ticket_status = ? WHERE id_ticket = ?";
    
    private static final String SQL_COUNT_TICKETSTATUS = "SELECT COUNT(*) \n" +
            "FROM ticket\n" +
            "INNER JOIN vehiculo ON ticket.id_vehiculo = vehiculo.id_vehiculo\n" +
            "INNER JOIN tipo_vehiculo ON vehiculo.id_tipo_vehiculo = tipo_vehiculo.id_tipo_vehiculo\n" +
            "WHERE vehiculo.id_tipo_vehiculo = ? AND ticket.id_ticket_status = ?";
    
    public int contarVehiculos(int idTipoVehiculo, int idTicketStatus) {
        int conteo = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_COUNT_TICKETSTATUS);
            stmt.setInt(1, idTipoVehiculo);
            stmt.setInt(2, idTicketStatus);
            rs = stmt.executeQuery();
            while(rs.next()) {
                conteo = rs.getInt(1);
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
        
        return conteo;
    }
    
    
    
    public int updateTicketStatus(int idTicketStatus, int idTicket) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_UPDATE_TICKETSTATUS);
            
            stmt.setInt(1, idTicketStatus);
            stmt.setInt(2, idTicket);
                        
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
    
    public int getTicketStatus(int idTicket) {
        int status = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_IDTICKET);
            stmt.setInt(1, idTicket);
            rs = stmt.executeQuery();
            while(rs.next()) {
                status = rs.getInt(1);
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
        
        return status;
    }
    
    public RegistroDeParqueo verUltimoTicket(int idVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        RegistroDeParqueo ticket = new RegistroDeParqueo();
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
        
        
    public int insertar(RegistroDeParqueo ticket) {
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
    
    public int actualizar(RegistroDeParqueo ticket) {
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
