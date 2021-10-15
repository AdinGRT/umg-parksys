package com.analisisii.controlador;

import com.analisisii.dao.TicketDAO;
import com.analisisii.dao.VehiculoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import com.analisisii.modelo.Ticket;
import com.analisisii.modelo.TipoVehiculo;
import com.analisisii.modelo.Vehiculo;
import com.analisisii.vista.AgregarVehiculoDialog;
import com.analisisii.vista.PantallaTodoEnUno;

/**
 *
 * @author gian_
 */
public class ControladorIngresoVehiculo implements ActionListener {

    private TipoVehiculo tipoVehiculo = new TipoVehiculo();
    private Vehiculo vehiculo = new Vehiculo();
    private VehiculoDAO vehiculoDao = new VehiculoDAO();
    private int idTipoVehiculo;
    private Ticket ticket = null;
    private TicketDAO ticketDao = null;
    private int idUsuario;

    private AgregarVehiculoDialog agregarVehiculo;

    public ControladorIngresoVehiculo(PantallaTodoEnUno sV, int idTipoVehiculo, int idUsuario) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.idUsuario = idUsuario;
        this.agregarVehiculo = new AgregarVehiculoDialog(sV, true);
        this.agregarVehiculo.getjButtonValidarPlaca().addActionListener(this);
        this.agregarVehiculo.getjButtonAceptarIngreso().addActionListener(this);
        this.agregarVehiculo.getjButtonGenerarTicket().addActionListener(this);
        this.agregarVehiculo.getjButtonCancelar().addActionListener(this);
        this.agregarVehiculo.setLocationRelativeTo(sV);
        this.agregarVehiculo.setSize(500,400);
        this.agregarVehiculo.setResizable(false);
        this.setTipoVehiculo();
        this.agregarVehiculo.getjTextMarca().setEditable(false);
        this.agregarVehiculo.getjTextModelo().setEditable(false);
        this.agregarVehiculo.getjTextColor().setEditable(false);
        this.agregarVehiculo.getTxtaTicket().setEditable(false);
        this.agregarVehiculo.getjButtonAceptarIngreso().setEnabled(false);
        this.agregarVehiculo.getjButtonGenerarTicket().setEnabled(false);
        this.agregarVehiculo.setVisible(true);

    }

    public void setTipoVehiculo() {
        if (this.idTipoVehiculo == 1) {
            this.agregarVehiculo.setJlblTipoVehiculo("M0");
        } else if (this.idTipoVehiculo == 2) {
            this.agregarVehiculo.setJlblTipoVehiculo("P0");
        }
    }

    public void validarVehiculo() {
        String placa;
        placa = this.agregarVehiculo.getjTextPlaca().getText();
        this.vehiculo = this.vehiculoDao.buscarPorPlaca(placa, idTipoVehiculo);
    }

    public boolean verificarVehiculoEnParqueo(String placa, int idTicketStatus) {
        boolean vehiculoEnParqueo;
        vehiculoEnParqueo = this.vehiculoDao.verificarVehiculo(placa, idTicketStatus, this.idTipoVehiculo);
        return vehiculoEnParqueo;
    }

    public void llenarCampos() {
        if (this.vehiculo.getPlaca() == null) {
            JOptionPane.showMessageDialog(null, "Vehiculo no registrado. Llena los campos.");
            this.agregarVehiculo.getjTextMarca().setEditable(true);
            this.agregarVehiculo.getjTextModelo().setEditable(true);
            this.agregarVehiculo.getjTextColor().setEditable(true);
            this.agregarVehiculo.getjButtonAceptarIngreso().setEnabled(true);
            this.agregarVehiculo.getjTextPlaca().setEditable(false);
        } else {
            JOptionPane.showMessageDialog(null, "Vehiculo ya registrado.");
            this.agregarVehiculo.setjTextMarca(this.vehiculo.getMarca());
            this.agregarVehiculo.setjTextModelo(this.vehiculo.getModelo());
            this.agregarVehiculo.setjTextColor(this.vehiculo.getColor());
            this.agregarVehiculo.getjButtonGenerarTicket().setEnabled(true);
        }
    }

    public void limpiarCampos() {
        this.agregarVehiculo.setjTextMarca("");
        this.agregarVehiculo.setjTextModelo("");
        this.agregarVehiculo.setjTextColor("");
        this.agregarVehiculo.setTxtaTicket("");
    }

    public void ingresarNuevoVehiculo() {
        int registrosGuardados;
        this.vehiculo.setPlaca(this.agregarVehiculo.getjTextPlaca().getText());
        this.vehiculo.setMarca(this.agregarVehiculo.getjTextMarca().getText());
        this.vehiculo.setModelo(this.agregarVehiculo.getjTextModelo().getText());
        this.vehiculo.setColor(this.agregarVehiculo.getjTextColor().getText());
        this.vehiculo.setIdTipoVehiculo(idTipoVehiculo);
        registrosGuardados = this.vehiculoDao.insertar(this.vehiculo);
        if (registrosGuardados == 1) {
            JOptionPane.showMessageDialog(null, "Vehiculo ingresado.");
            this.agregarVehiculo.getjButtonGenerarTicket().setEnabled(true);
            this.agregarVehiculo.getjButtonAceptarIngreso().setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(null, "Algo salio mal.");
        }
    }

    public void generarTicket() {
        this.ticket = new Ticket(idUsuario, this.vehiculo.getIdVehiculo(), 1);
        this.ticketDao = new TicketDAO();
        int resultado = this.ticketDao.insertar(this.ticket);
        if (resultado == 1) {
            JOptionPane.showMessageDialog(null, "Ticket Generado.");
            Ticket ultimoTicket = this.ticketDao.verUltimoTicket(this.vehiculo.getIdVehiculo());
            String vehiculo = "";
            if (idTipoVehiculo == 1) {
                vehiculo = "MOTO";
            } else if (idTipoVehiculo == 2) {
                vehiculo = "CARRO";
            }
            this.agregarVehiculo.setTxtaTicket(mostrarTicket(ultimoTicket, vehiculo, this.agregarVehiculo.getjTextPlaca().getText()));
        } else {
            JOptionPane.showMessageDialog(null, "Ticket No Generado.");
        }
    }

    public void cancelarIngreso() {
        this.agregarVehiculo.setjTextPlaca("");
        this.limpiarCampos();
        this.agregarVehiculo.getjTextPlaca().setEditable(true);
    }
    
    public String mostrarTicket(Ticket ticket, String tipoVehiculo, String placa) {
        StringBuilder sb = new StringBuilder();
        sb.append("-------- TICKET -------").append("\n\n");
        sb.append("Ticket No.: ").append("\n").append(ticket.getIdTicket()).append("\n\n");
        sb.append("Tipo de Vehículo: ").append("\n").append(tipoVehiculo).append("\n\n");
        sb.append("Placa de Vehículo: ").append("\n").append(placa).append("\n\n");
        sb.append("Fecha y hora de ingreso: ").append("\n").append(ticket.getHorarioEntrada()).append("\n\n");
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.agregarVehiculo.getjButtonValidarPlaca()) {
            validarVehiculo();
            if (verificarVehiculoEnParqueo(this.vehiculo.getPlaca(), 1)) {
                JOptionPane.showMessageDialog(null, "ALERTA! Vehículo aparece adentro del parqueo.");
                this.agregarVehiculo.getjButtonGenerarTicket().setEnabled(false);
            } else {
                this.limpiarCampos();
                this.llenarCampos();
            }
        }
        if (e.getSource() == this.agregarVehiculo.getjButtonAceptarIngreso()) {
            ingresarNuevoVehiculo();
            validarVehiculo();
        }
        if (e.getSource() == this.agregarVehiculo.getjButtonGenerarTicket()) {
            generarTicket();
        }
        if (e.getSource() == this.agregarVehiculo.getjButtonCancelar()) {
            cancelarIngreso();
        }
    }

}
