package controlador;

import dao.TicketDAO;
import dao.VehiculoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Ticket;
import modelo.TipoVehiculo;
import modelo.Vehiculo;
import vista.AgregarVehiculoDialog;
import vista.SeleccionarVehiculo;

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

    public ControladorIngresoVehiculo(SeleccionarVehiculo sV, int idTipoVehiculo, int idUsuario) {
        this.idTipoVehiculo = idTipoVehiculo;
        this.idUsuario = idUsuario;
        this.agregarVehiculo = new AgregarVehiculoDialog(sV, true);
        this.agregarVehiculo.getjButtonValidarPlaca().addActionListener(this);
        this.agregarVehiculo.getjButtonAceptarIngreso().addActionListener(this);
        this.agregarVehiculo.getjButtonGenerarTicket().addActionListener(this);
        this.agregarVehiculo.setLocationRelativeTo(sV);
        this.agregarVehiculo.setResizable(false);
        this.agregarVehiculo.setVisible(true);
    }
    
    public void validarVehiculo() {
        String placa;
        placa = this.agregarVehiculo.getjTextPlaca().getText();
        this.vehiculo = this.vehiculoDao.buscarPorPlaca(placa, idTipoVehiculo);
        this.agregarVehiculo.setjTextMarca(this.vehiculo.getMarca());
        this.agregarVehiculo.setjTextModelo(this.vehiculo.getModelo());
        this.agregarVehiculo.setjTextColor(this.vehiculo.getColor());
        if(this.vehiculo.getPlaca() == null) {
            JOptionPane.showMessageDialog(null, "Vehiculo no existe.");
        } else {
            JOptionPane.showMessageDialog(null, "Vehiculo si existe.");
        }
    }
    
    public void ingresarNuevoVehiculo() {
        int registrosGuardados;
        this.vehiculo.setPlaca(this.agregarVehiculo.getjTextPlaca().getText());
        this.vehiculo.setMarca(this.agregarVehiculo.getjTextMarca().getText());
        this.vehiculo.setModelo(this.agregarVehiculo.getjTextModelo().getText());
        this.vehiculo.setColor(this.agregarVehiculo.getjTextColor().getText());
        this.vehiculo.setIdTipoVehiculo(idTipoVehiculo);
        registrosGuardados = this.vehiculoDao.insertar(this.vehiculo);
        String placa;
        placa = this.agregarVehiculo.getjTextPlaca().getText();
        this.vehiculo = this.vehiculoDao.buscarPorPlaca(placa, idTipoVehiculo);
        if (registrosGuardados == 1) {
            JOptionPane.showMessageDialog(null, "Vehiculo ingresado.");
        }
    }
    
    public void generarTicket(){
        this.ticket = new Ticket(idUsuario, this.vehiculo.getIdVehiculo(), 1);
        this.ticketDao = new TicketDAO();
        int resultado = this.ticketDao.insertar(this.ticket);
        if (resultado == 1) {
            JOptionPane.showMessageDialog(null, "Ticket Generado.");
            Ticket ultimoTicket = this.ticketDao.verUltimoTicket(1);
            this.agregarVehiculo.setTxtaTicket(mostrarTicket(ultimoTicket, "CARRO O MOTO", this.agregarVehiculo.getjTextPlaca().getText()));
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ticket No Generado.");
        }
    }
    
    public String mostrarTicket(Ticket ticket, String tipoVehiculo, String placa) {
        StringBuilder sb = new StringBuilder();
        sb.append("---- TICKET ---").append("\n");
        sb.append("Ticket No.: ").append("\t").append(ticket.getIdTicket()).append("\n");
        sb.append("Tipo de Vehículo: ").append("\t").append(tipoVehiculo).append("\n");
        sb.append("Placa de Vehículo: ").append("\t").append(placa).append("\n");
        sb.append("Fecha y hora de ingreso: ").append("\t").append(ticket.getHorarioEntrada()).append("\n");
        return sb.toString();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.agregarVehiculo.getjButtonValidarPlaca()) {
            validarVehiculo();
        }
        if (e.getSource() == this.agregarVehiculo.getjButtonAceptarIngreso()) {
            ingresarNuevoVehiculo();
        }
        if (e.getSource() == this.agregarVehiculo.getjButtonGenerarTicket()) {
            generarTicket();
        }
    }
        
}
