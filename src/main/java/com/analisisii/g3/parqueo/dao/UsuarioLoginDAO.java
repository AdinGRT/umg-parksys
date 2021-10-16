package com.analisisii.g3.parqueo.dao;

import com.analisisii.g3.usuarios.constantes.TipoDeRol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.analisisii.g3.usuarios.modelo.UsuarioLogin;

/**
 *
 * @author gian_
 */
public class UsuarioLoginDAO {
    private static final String SQL_LOGIN = "SELECT usuario_login.nombre_usuario, rol.descripcion_rol\n" +
            "FROM usuario_login\n" +
            "INNER JOIN rol_usuario ON usuario_login.id_usuario_login = rol_usuario.id_usuario_login\n" +
            "INNER JOIN rol ON rol_usuario.id_rol = rol.id_rol\n" +
            "WHERE usuario_login.nombre_usuario = ?\n" +
            "AND usuario_login.password_usuario = ?\n" +
            "AND rol_usuario.id_rol = ?";
    
    public UsuarioLogin validarUsuario(String nombreUsuario, String passwordUsuario, int idRol) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        try {
            conn = Conexion.conectar();
            stmt = conn.prepareStatement(SQL_LOGIN);
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, passwordUsuario);
            stmt.setInt(3, idRol);
            rs = stmt.executeQuery();
            while(rs.next()) {
                usuarioLogin.setNombreUsuario(rs.getString(1));
                TipoDeRol tipoRol = TipoDeRol.valueOf(rs.getString(2));
                usuarioLogin.setTipoRol(tipoRol);
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
        return usuarioLogin;
    }
}
