package controlador;

import dao.TicketDAO;
import dao.VehiculoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Ticket;
import modelo.TipoVehiculo;
import modelo.Vehiculo;
import vista.AgregarVehiculo;

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
    
    private AgregarVehiculo agregarVehiculo = new AgregarVehiculo();

    public ControladorIngresoVehiculo(AgregarVehiculo agregarVehiculo, int idTipoVehiculo, int idUsuario) {
        this.agregarVehiculo = agregarVehiculo;
        this.agregarVehiculo.getjButtonValidarPlaca().addActionListener((ActionListener) this);
        this.agregarVehiculo.getjButtonAceptarIngreso().addActionListener((ActionListener) this);
        this.agregarVehiculo.getjButtonGenerar().addActionListener((ActionListener) this);
        this.idTipoVehiculo = idTipoVehiculo;
        this.idUsuario = idUsuario;
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
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ticket No Generado.");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agregarVehiculo.getjButtonValidarPlaca()) {
            validarVehiculo();
        }
        if (e.getSource() == agregarVehiculo.getjButtonAceptarIngreso()) {
            ingresarNuevoVehiculo();
        }
        if (e.getSource() == agregarVehiculo.getjButtonGenerar()) {
            generarTicket();
        }
    }
        
}
