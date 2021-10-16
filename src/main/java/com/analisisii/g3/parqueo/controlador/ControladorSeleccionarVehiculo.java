/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.analisisii.g3.parqueo.controlador;

import com.analisisii.g3.parqueo.dao.TicketDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.serverparksys.modelo.TipoVehiculo;
import com.analisisii.g3.parqueo.vista.PantallaTodoEnUno;

/**
 *
 * @author gian_
 */
public class ControladorSeleccionarVehiculo implements ActionListener {
    
    private TipoVehiculo tipoVehiculo = new TipoVehiculo();
    private PantallaTodoEnUno seleccionarVehiculo = new PantallaTodoEnUno();
    private int idUsuario;
    private TicketDAO ticketDao = new TicketDAO();
    private int capacidadMotos = 50;
    private int capacidadCarros = 70;

    public ControladorSeleccionarVehiculo(PantallaTodoEnUno seleccionarVehiculo, int idUsuario) {
        this.seleccionarVehiculo = seleccionarVehiculo;
        this.idUsuario = idUsuario;
        this.seleccionarVehiculo.getjButtonMoto().addActionListener((ActionListener) this);
        this.seleccionarVehiculo.getjButtonCarro().addActionListener((ActionListener) this);
        this.seleccionarVehiculo.getjButtonRetirar().addActionListener((ActionListener) this);
        this.disponibilidadVehiculos();
    }
    
    
    public void disponibilidadVehiculos() {
        int motosDentro = this.ticketDao.contarVehiculos(1, 1);
        int carrosDentro = this.ticketDao.contarVehiculos(2, 1);
        int dispMotos = this.capacidadMotos - motosDentro;
        int dispCarros = this.capacidadCarros - carrosDentro;
        this.seleccionarVehiculo.setJlblMoto(""+dispMotos);
        this.seleccionarVehiculo.setJlblCarro(""+dispCarros);
        
    }
    
    public void ingresarMoto() {
        ControladorIngresoVehiculo controlador = new ControladorIngresoVehiculo(this.seleccionarVehiculo, 1, this.idUsuario);
    } 
    
    public void ingresarCarro() {
        ControladorIngresoVehiculo controlador = new ControladorIngresoVehiculo(this.seleccionarVehiculo, 2, this.idUsuario);
    }
    
    public void retirarVehiculoPorTicket() {
        int idTicket = Integer.parseInt(this.seleccionarVehiculo.getjTextTicket().getText());
        int ticketStatus = this.ticketDao.getTicketStatus(idTicket);
        if (ticketStatus == 0) {
            JOptionPane.showMessageDialog(null, "TICKET NO ENCONTRADO");
        } else if (ticketStatus == 1) {
            JOptionPane.showMessageDialog(null, "TICKET NO PAGADO");
        } else if (ticketStatus == 2) {
            int rows = this.ticketDao.updateTicketStatus(3, idTicket);
            if (rows == 1) {
                JOptionPane.showMessageDialog(null, "TICKET RECIBIDO, PUEDE SALIR");
            } else {
                JOptionPane.showMessageDialog(null, "ALGO SALIO MAL");
            }
        } else if (ticketStatus == 3) {
            JOptionPane.showMessageDialog(null, "ALERTA!! TICKET YA SE RECIBIO");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seleccionarVehiculo.getjButtonMoto()) {
            ingresarMoto();
            disponibilidadVehiculos();
        }
        if (e.getSource() == seleccionarVehiculo.getjButtonCarro()) {
            ingresarCarro();
            disponibilidadVehiculos();
        }
        if (e.getSource() == seleccionarVehiculo.getjButtonRetirar()) {
            retirarVehiculoPorTicket();
            disponibilidadVehiculos();
        }
    }
    
    
}
