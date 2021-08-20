/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.TipoVehiculo;
import vista.SeleccionarVehiculo;

/**
 *
 * @author gian_
 */
public class ControladorSeleccionarVehiculo implements ActionListener {
    
    private TipoVehiculo tipoVehiculo = new TipoVehiculo();
    private SeleccionarVehiculo seleccionarVehiculo = new SeleccionarVehiculo();
    private int idUsuario;
    //private AgregarVehiculoDialog agregarVehiculo;

    public ControladorSeleccionarVehiculo(SeleccionarVehiculo seleccionarVehiculo, int idUsuario) {
        this.seleccionarVehiculo = seleccionarVehiculo;
        this.idUsuario = idUsuario;
        this.seleccionarVehiculo.getjButtonMoto().addActionListener((ActionListener) this);
        this.seleccionarVehiculo.getjButtonCarro().addActionListener((ActionListener) this);
    }
    
    public void ingresarMoto() {
        JOptionPane.showMessageDialog(null, "MOTO");
        ControladorIngresoVehiculo controlador = new ControladorIngresoVehiculo(this.seleccionarVehiculo, 1, this.idUsuario);
    } 
    
    public void ingresarCarro() {
        JOptionPane.showMessageDialog(null, "CARRO");
        ControladorIngresoVehiculo controlador = new ControladorIngresoVehiculo(this.seleccionarVehiculo, 2, this.idUsuario);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seleccionarVehiculo.getjButtonMoto()) {
            ingresarMoto();
        }
        if (e.getSource() == seleccionarVehiculo.getjButtonCarro()) {
            ingresarCarro();
        }
    }
    
    
}
