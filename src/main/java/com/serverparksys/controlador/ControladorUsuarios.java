package com.serverparksys.controlador;

import com.serverparksys.dao.UsuarioDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.serverparksys.modelo.Usuario;
import com.serverparksys.vista.Administrador;
import com.serverparksys.vista.MantenimientoUsuarios;

/**
 *
 * @author gian_
 */
public class ControladorUsuarios implements ActionListener{

    private Usuario usuario = new Usuario();
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private Administrador pAdmin = new Administrador();
    private MantenimientoUsuarios mantUs = new MantenimientoUsuarios();

    public ControladorUsuarios(MantenimientoUsuarios mantUs) {
        this.mantUs = mantUs;
        this.mantUs.getjButtonBuscar().addActionListener(this);
        this.mantUs.getjButtonNuevo().addActionListener(this);
        this.mantUs.getjButtonGuardar().addActionListener(this);
        this.mantUs.getjButtonActualizar().addActionListener(this);
        this.mantUs.getjButtonBorrar().addActionListener(this);
        this.mantUs.getjButtonCancelar().addActionListener(this);
        this.mantUs.getjButtonSalir().addActionListener(this);
        
    }
    
    public void insertarUsuario() {
        int reg;
        this.usuario.setNombre(this.mantUs.getjTextNombre().getText());
        this.usuario.setApellido(this.mantUs.getjTextApellido().getText());
        this.usuario.setCui(this.mantUs.getjTextDPI().getText());
        this.usuario.setEmail(this.mantUs.getjTextCorreo().getText());
        this.usuario.setTelefono(this.mantUs.getjTextTelefono().getText());
        reg = this.usuarioDao.insertarUsuario(usuario);
        if (reg == 1) {
            JOptionPane.showMessageDialog(null, "Usuario ingresado.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Algo salio mal.");
        }
    }
    
    public void buscarUsuario(){
        String cui = this.mantUs.getjTextDPI().getText();
        this.usuario = this.usuarioDao.buscarUsuarioPorCui(cui);
        if (this.usuario.getCui() != null) {
            JOptionPane.showMessageDialog(null, "Usuario encontrado.");
            this.mantUs.setjTextNombre(this.usuario.getNombre());
            this.mantUs.setjTextApellido(this.usuario.getApellido());
            this.mantUs.setjTextDPI(this.usuario.getCui());
            this.mantUs.setjTextCorreo(this.usuario.getEmail());
            this.mantUs.setjTextTelefono(this.usuario.getTelefono());
            
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no encontrado.");
            limpiarCampos();
        }
    }
    
    public void actualizarUsuario() {
        int reg;
        this.usuario.setNombre(this.mantUs.getjTextNombre().getText());
        this.usuario.setApellido(this.mantUs.getjTextApellido().getText());
        this.usuario.setCui(this.mantUs.getjTextDPI().getText());
        this.usuario.setEmail(this.mantUs.getjTextCorreo().getText());
        this.usuario.setTelefono(this.mantUs.getjTextTelefono().getText());
        reg = this.usuarioDao.actualizarUsuario(usuario);
        if (reg == 1) {
            JOptionPane.showMessageDialog(null, "Usuario actualizado.");
        } else {
            JOptionPane.showMessageDialog(null, "Algo salio mal.");
        }
    }
    
    public void eliminarUsuario() {
        int reg;
        reg = this.usuarioDao.eliminarUsuario(usuario);
        if (reg == 1) {
            JOptionPane.showMessageDialog(null, "Usuario eliminado.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(null, "Algo salio mal.");
            limpiarCampos();
        }
    }
    
    public void limpiarCampos(){
        this.mantUs.setjTextNombre("");
        this.mantUs.setjTextApellido("");
        this.mantUs.setjTextDPI("");
        this.mantUs.setjTextCorreo("");
        this.mantUs.setjTextTelefono("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.mantUs.getjButtonGuardar()) {
            insertarUsuario();
        }
        if (e.getSource() == this.mantUs.getjButtonBuscar()) {
            buscarUsuario();
        }
        if (e.getSource() == this.mantUs.getjButtonActualizar()) {
            actualizarUsuario();
        }
        if (e.getSource() == this.mantUs.getjButtonBorrar()) {
            eliminarUsuario();
        }
    }
    
}
