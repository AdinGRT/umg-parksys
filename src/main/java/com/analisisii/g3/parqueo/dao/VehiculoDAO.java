package com.analisisii.g3.parqueo.dao;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import java.sql.*;
import com.analisisii.g3.parqueo.modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class VehiculoDAO {
    private static final String SQL_INSERT = "INSERT INTO vehiculo(matricula_vehiculo, marca_vehiculo,"
            + " modelo_vehiculo, color_vehiculo, id_tipo_vehiculo) "
            + "VALUES (?, ?, ?, ?, ?)";

    private static final String SQL_SELECT_MATRICULA = "SELECT * FROM vehiculo WHERE matricula_vehiculo = ? AND id_tipo_vehiculo = ?";

    private static final String SQL_SELECT_VEHICULO_STATUS = "SELECT vehiculo.placa, ticket.id_ticket, ticket_status.id_ticket_status, ticket_status.descripcion_ticket_status\n"
            + "FROM ticket\n"
            + "INNER JOIN vehiculo ON ticket.id_vehiculo = vehiculo.id_vehiculo\n"
            + "INNER JOIN ticket_status ON ticket.id_ticket_status = ticket_status.id_ticket_status\n"
            + "WHERE vehiculo.placa = ? AND ticket_status.id_ticket_status = ?\n"
            + "AND vehiculo.id_tipo_vehiculo = ?";

    public int insertVehiculo(Vehiculo vehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getTipoVehiculo().ordinal());

            rows = stmt.executeUpdate();
            
            rs = stmt.getGeneratedKeys();
            while(rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt(1));
            }
            
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

    public Vehiculo buscarPorMatricula(String matricula, int idTipoVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vehiculo vehiculo = new Vehiculo();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_MATRICULA);
            stmt.setString(1, matricula);
            stmt.setInt(2, idTipoVehiculo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt(1));
                vehiculo.setMatricula(rs.getString(2));
                vehiculo.setMarca(rs.getString(3));
                vehiculo.setModelo(rs.getString(4));
                vehiculo.setColor(rs.getString(5));
                vehiculo.setTipoVehiculo(TipoDeVehiculo.values()[rs.getInt(6)]);
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

        return vehiculo;
    }
    
    public boolean verificarVehiculo(String placa, int idTicketStatus, int idTipoVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_VEHICULO_STATUS);
            stmt.setString(1, placa);
            stmt.setInt(2, idTicketStatus);
            stmt.setInt(3, idTipoVehiculo);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
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
        return false;
    }

    

    public int insertar(Vehiculo vehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vehiculo.getMatricula());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getTipoVehiculo().ordinal());

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
