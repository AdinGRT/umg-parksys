package dao;

import java.sql.*;
import modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class VehiculoDAO {
    private static final String SQL_INSERT = "INSERT INTO vehiculo(placa, marca, modelo, color, id_tipo_vehiculo) "
            + "VALUES (?, ?, ?, ?, ?)";
    
    private static final String SQL_SELECT_PLACA = "SELECT * FROM vehiculo WHERE placa = ? AND id_tipo_vehiculo = ?";
    
    
    
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
