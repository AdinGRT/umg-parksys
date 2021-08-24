/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author leona
 */
public class Usuario extends Persona {
    private int id_Usuario;
    private String nombre_login;
    private String Password_usuario;
    
    public Usuario(){
        
    }
    
    public Usuario (int id_Usuario, String nombre_login, String Password_usuario) {
       this.id_Usuario = id_Usuario;
       this.nombre_login = nombre_login;
       this.Password_usuario = Password_usuario;
    }
    public int getId_Usuario() {
        return id_Usuario;
    }

    public void setId_Usuario(int idUsuario) {
        this.id_Usuario = idUsuario;
    }

    public String getnombre_login() {
        return nombre_login;
    }

    public void setnombre_login(String Login) {
        this.nombre_login = nombre_login;
    }

    public String getPassword_usuario() {
        return Password_usuario;
    }

    public void setPassword_usuario(String Password) {
        this.Password_usuario = Password_usuario;
    }

    public void ConectarBasedeDatos() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
