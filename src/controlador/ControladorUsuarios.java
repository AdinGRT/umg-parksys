package controlador;

import dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Usuario;
import vista.Administrador;

/**
 *
 * @author gian_
 */
public class ControladorUsuarios implements ActionListener{

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private Administrador pAdmin = new Administrador();
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
