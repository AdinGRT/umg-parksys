package modelo;

/**
 *
 * @author gian_
 */
public class UsuarioLogin {
    private String nombreUsuario;
    private String passwordUsuario;
    private int idRol;

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

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    @Override
    public String toString() {
        return "UsuarioLogin{" + "nombreUsuario=" + nombreUsuario + ", passwordUsuario=" + passwordUsuario + ", idRol=" + idRol + '}';
    }
    
    
}
