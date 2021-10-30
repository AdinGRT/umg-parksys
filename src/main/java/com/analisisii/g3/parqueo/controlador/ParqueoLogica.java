package com.analisisii.g3.parqueo.controlador;

import com.analisisii.g3.parqueo.constantes.EstadoRegistroDeParqueo;
import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.dao.RegistroDeParqueoDAO;
import com.analisisii.g3.parqueo.dao.VehiculoDAO;
import com.analisisii.g3.parqueo.dominio.Parqueo;
import com.analisisii.g3.parqueo.modelo.RegParqueoPanel;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class ParqueoLogica {

    private Parqueo parqueo;
    private EstadoRegistroDeParqueo estadoRegistro = EstadoRegistroDeParqueo.ACTIVO;
    private Map<String, RegistroDeParqueo> registrosDeParqueoActivos;
    
    private RegistroDeParqueoDAO registroDao = new RegistroDeParqueoDAO();
    private RegistroDeParqueo registro = new RegistroDeParqueo();
    private RegParqueoPanel regParqueoPanel;
    
    private VehiculoDAO vehiculoDao;
    private Vehiculo vehiculo;
    private TipoDeVehiculo tipoVehiculo = TipoDeVehiculo.PARTICULAR;
    int cantidadParticulares;
    private TipoDeVehiculo tipoVehiculo2 = TipoDeVehiculo.MOTOCICLETA;
    int cantidadMotocicletas;
    
    
    
    
    
    
    private static int id;

    //CONSTRUCTOR
    public ParqueoLogica() {
        iniciarComponentes();
    }

    //INICIAR COMPONENTES
    private void iniciarComponentes() {
        this.cantidadParticulares = registroDao.contarEspaciosOcupados(tipoVehiculo.ordinal());
        this.cantidadMotocicletas = registroDao.contarEspaciosOcupados(tipoVehiculo2.ordinal());

        try {
            this.registrosDeParqueoActivos = registroDao.listarRegistrosPorMatriculaEstado(estadoRegistro.ordinal());
        } catch (SQLException ex) {
            Logger.getLogger(ParqueoLogica.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.parqueo = Parqueo.obtenerInstanciaDeParqueo("UMG Parksys", "UMG Escuintla", this.cantidadParticulares, this.cantidadMotocicletas, 10, 20, registrosDeParqueoActivos);

        this.id = registroDao.contarRegistros() + 1;
    }

    //1. Opcion Generar Registro
    public synchronized RegistroDeParqueo crearRegistro(Vehiculo vehiculo) {
        this.registro = parqueo.generarRegistroDeParqueo(vehiculo, this.id);
        if(this.registro != null) {
            this.id++;
        }
        return this.registro;
    }
    
    //
    public void persistirRegistro(RegistroDeParqueo registro, RegParqueoPanel regpp) {
        if(this.registroDao.insertRegistroDeParqueo(registro) >=1){
            System.out.println("ALMACENADO REGISTRO");
            System.out.println(registro);
        }
        if(this.registroDao.insertRegPanelES(regpp) >= 0){
            System.out.println("ALMACENADO REGPP");
            System.out.println(regpp);
        }
        
    }

    public Map<String, RegistroDeParqueo> getRegistrosDeParqueoActivos() {
        return registrosDeParqueoActivos;
    }

    //2. Opcion Vehiculo
    public synchronized Vehiculo obtenerVehiculo(String matricula, String tipoDeVehiculo) throws VehiculoException {
        this.vehiculoDao = new VehiculoDAO();
        this.vehiculo = vehiculoDao.buscarPorMatricula(matricula, TipoDeVehiculo.valueOf(tipoDeVehiculo).ordinal());
        if (this.vehiculo.getMatricula() != null) {
            System.out.println("Vehiculo existente.");
            return this.vehiculo;
        } else {
            this.vehiculo = new Vehiculo(matricula, TipoDeVehiculo.valueOf(tipoDeVehiculo));
            if (this.vehiculoDao.insertVehiculo(this.vehiculo) == 0) {
                throw new VehiculoException("Ha ocurrido un error, no se ha almacenado!");
            } else {
                System.out.println("Vehiculo nuevo.");
                return this.vehiculo;
            }
        }
    }

    public int getCantidadParticulares() {
        return cantidadParticulares;
    }

    public int getCantidadMotocicletas() {
        return cantidadMotocicletas;
    }

    public Parqueo getParqueo() {
        return parqueo;
    }

}
