package controlador;

import dao.VehiculoDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.TipoVehiculo;
import modelo.Vehiculo;
import vista.AgregarVehiculo;

/**
 *
 * @author gian_
 */
public class ControladorIngresoVehiculo implements ActionListener {
    TipoVehiculo tipoVehiculo = new TipoVehiculo();
    Vehiculo vehiculo = new Vehiculo();
    VehiculoDAO vehiculoDao = new VehiculoDAO();
    int idTipoVehiculo;
    
    AgregarVehiculo agregarVehiculo = new AgregarVehiculo();

    public ControladorIngresoVehiculo(AgregarVehiculo agregarVehiculo, int idTipoVehiculo) {
        this.agregarVehiculo = agregarVehiculo;
        this.agregarVehiculo.getjButtonValidarPlaca().addActionListener((ActionListener) this);
        this.agregarVehiculo.getjButtonAceptarIngreso().addActionListener((ActionListener) this);
        this.idTipoVehiculo = idTipoVehiculo;
    }
    
    public void validarVehiculo() {
        String placa;
        placa = this.agregarVehiculo.getjTextPlaca().getText();
        this.vehiculo = this.vehiculoDao.buscarPorPlaca(placa, idTipoVehiculo);
        this.agregarVehiculo.setjTextMarca(this.vehiculo.getMarca());
        this.agregarVehiculo.setjTextModelo(this.vehiculo.getModelo());
        this.agregarVehiculo.setjTextColor(this.vehiculo.getColor());
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agregarVehiculo.getjButtonValidarPlaca()) {
            validarVehiculo();
        }
    }
        
}
