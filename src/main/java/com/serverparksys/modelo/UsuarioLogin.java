package com.serverparksys.modelo;

import com.serverparksys.constantes.TipoDeRol;

/**
 *
 * @author gian_
 */
public class UsuarioLogin {
    private String nombreUsuario;
    private String passwordUsuario;
    private TipoDeRol tipoRol;

    public UsuarioLogin() {
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public void setPasswordUsuario(String passwordUsuario) {
        this.passwordUsuario = passwordUsuario;
    }

    public TipoDeRol getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(TipoDeRol tipoRol) {
        this.tipoRol = tipoRol;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UsuarioLogin{nombreUsuario=").append(nombreUsuario);
        sb.append(", passwordUsuario=").append(passwordUsuario);
        sb.append(", tipoRol=").append(tipoRol.name());
        sb.append('}');
        return sb.toString();
    }
    
    
}
