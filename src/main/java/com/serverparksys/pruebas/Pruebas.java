package com.serverparksys.pruebas;

import com.serverparksys.constantes.TipoDeRol;
import com.serverparksys.dao.UsuarioLoginDAO;
import com.serverparksys.modelo.UsuarioLogin;

/**
 *
 * @author gian_
 */
public class Pruebas {
    public static void main(String[] args) {
        
        UsuarioLoginDAO login = new UsuarioLoginDAO();
        UsuarioLogin usuarioLogin = new UsuarioLogin();
        usuarioLogin = login.validarUsuario("arubio", "1345", 2);
        
        System.out.println(usuarioLogin);
        
    }
}
