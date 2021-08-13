package dao;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class VehiculoDAO {
    private static final String SQL_INSERT = "INSERT INTO vehiculo(placa, marca, modelo, color, id_tipo_vehiculo) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PLACA = "SELECT * FROM vehiculo WHERE placa = ? AND id_tipo_vehiculo = ?";
    
    public Vehiculo buscarPorPlaca(String placa, int idTipoVehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Vehiculo vehiculo = new Vehiculo();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_SELECT_PLACA);
            stmt.setString(1, placa);
            stmt.setInt(2, idTipoVehiculo);
            rs = stmt.executeQuery();
            while(rs.next()) {
                vehiculo.setIdVehiculo(rs.getInt(1));
                vehiculo.setPlaca(rs.getString(2));
                vehiculo.setMarca(rs.getString(3));
                vehiculo.setModelo(rs.getString(4));
                vehiculo.setColor(rs.getString(5));
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
        
    public int insertar(Vehiculo vehiculo) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vehiculo.getPlaca());
            stmt.setString(2, vehiculo.getMarca());
            stmt.setString(3, vehiculo.getModelo());
            stmt.setString(4, vehiculo.getColor());
            stmt.setInt(5, vehiculo.getIdTipoVehiculo());
            
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
