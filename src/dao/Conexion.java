package dao;

import java.sql.*;

/**
 *
 * @author gian_
 */
public class Conexion {
    private static final String URL = "jdbc:mysql://localhost:3306/parqueo?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    public static void desconectar(ResultSet rs) throws SQLException {
        rs.close();
    }
    
    public static void desconectar(PreparedStatement stmt) throws SQLException {
        stmt.close();
    }
    
    public static void desconectar(Statement stmt) throws SQLException {
        stmt.close();
    }
    
    public static void desconectar(Connection conn) throws SQLException {
        conn.close();
    }
    
    
    
}
