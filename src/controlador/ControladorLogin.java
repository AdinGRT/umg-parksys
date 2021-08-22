package controlador;

import dao.UsuarioLoginDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.UsuarioLogin;
import vista.Administrador;
import vista.Login;
import vista.SeleccionarVehiculo;

/**
 *
 * @author gian_
 */
public class ControladorLogin implements ActionListener {
    private UsuarioLogin usuarioLogin = new UsuarioLogin();
    private UsuarioLoginDAO usuarioDao = new UsuarioLoginDAO();
    private Login pantallaLogin = new Login();

    public ControladorLogin(Login pantallaLogin) {
        this.pantallaLogin = pantallaLogin;
        this.pantallaLogin.setLocationRelativeTo(null);
        this.pantallaLogin.getjButtonIniciar().addActionListener(this);
        
    }

    public void validarUsuario(){
        String usuario = this.pantallaLogin.getjTextUsuario().getText();
        String password = this.pantallaLogin.getjPassword().getText();
        String rol = (String) this.pantallaLogin.getjComboBoxRol().getSelectedItem();
        int idRol = 0;
        if(rol.equals("ADMINISTRADOR")){
            idRol = 1;
        } else if (rol.equals("OPERADOR")) {
            idRol = 2;
        }
        this.usuarioLogin = this.usuarioDao.validarUsuario(usuario, password, idRol);
        if(this.usuarioLogin.getNombreUsuario().equals(usuario)) {
            if(this.usuarioLogin.getIdRol() == 1) {
                Administrador administrador = new Administrador();
                administrador.setLocationRelativeTo(null);
                administrador.setVisible(true);
                this.pantallaLogin.dispose();
                
            } else if(this.usuarioLogin.getIdRol() == 2) {
                SeleccionarVehiculo seleccionarVehiculo = new SeleccionarVehiculo();
                seleccionarVehiculo.setVisible(true);
                this.pantallaLogin.dispose();                
                ControladorSeleccionarVehiculo conSeleV = new ControladorSeleccionarVehiculo(seleccionarVehiculo, 1);
            } else {
                JOptionPane.showMessageDialog(this.pantallaLogin, "NO HIZO NADA");
            }
        }
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.pantallaLogin.getjButtonIniciar()) {
            validarUsuario();
        }
    }
    
    
    
    
}
