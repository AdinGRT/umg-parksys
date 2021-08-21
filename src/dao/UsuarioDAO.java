package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;
import modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public class UsuarioDAO {
    private static final String SQL_INSERT = "INSERT INTO usuario(nombre, apellido, cui, email, telefono) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_BUSCAR = "SELECT * FROM usuario WHERE cui = ?";
    private static final String SQL_UPDATE = "UPDATE usuario SET nombre = ?, apellido = ?, cui =?, email = ?, telefono = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuario WHERE id_usuario = ?";
    
    
    public int insertarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCui());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefono());
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
    
    public Usuario buscarUsuarioPorCui(String cui) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = new Usuario();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_BUSCAR);
            stmt.setString(1, cui);
            rs = stmt.executeQuery();
            while(rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombre(rs.getString(2));
                usuario.setApellido(rs.getString(3));
                usuario.setCui(rs.getString(4));
                usuario.setEmail(rs.getString(5));
                usuario.setTelefono(rs.getString(6));
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
        return usuario;
    }
    
    public int actualizarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getApellido());
            stmt.setString(3, usuario.getCui());
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getTelefono());
            stmt.setInt(6, usuario.getIdUsuario());
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
    
    public int eliminarUsuario(Usuario usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, usuario.getIdUsuario());
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
