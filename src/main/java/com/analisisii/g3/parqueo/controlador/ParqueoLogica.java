package com.analisisii.g3.parqueo.controlador;

import com.analisisii.g3.parqueo.constantes.EstadoRegistroDeParqueo;
import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.dao.RegistroDeParqueoDAO;
import com.analisisii.g3.parqueo.dominio.Parqueo;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author gian_
 */
public class ParqueoLogica {
    
    
    
    private RegistroDeParqueoDAO registroDao = new RegistroDeParqueoDAO();
    private RegistroDeParqueo registro = new RegistroDeParqueo();
    private TipoDeVehiculo tipoVehiculo = TipoDeVehiculo.PARTICULAR;
    int cantidadParticulares;
    private TipoDeVehiculo tipoVehiculo2 = TipoDeVehiculo.MOTOCICLETA;
    int cantidadMotocicletas;
    private EstadoRegistroDeParqueo estadoRegistro = EstadoRegistroDeParqueo.ACTIVO;
    private Map<String, RegistroDeParqueo> registrosDeParqueoActivos;
    private Parqueo parqueo;
    private Vehiculo vehiculo;
    private static int id;

    //CONSTRUCTOR
    public ParqueoLogica() throws SQLException {
        this.cantidadParticulares = registroDao.contarEspaciosOcupados(tipoVehiculo.ordinal());
        this.cantidadMotocicletas = registroDao.contarEspaciosOcupados(tipoVehiculo2.ordinal());
        
        this.registrosDeParqueoActivos = registroDao.listarRegistrosPorMatriculaEstado(estadoRegistro.ordinal());
        
        this.parqueo = Parqueo.obtenerInstanciaDeParqueo("UMG Parksys", "UMG Escuintla", this.cantidadParticulares, this.cantidadMotocicletas, 10, 20, registrosDeParqueoActivos);
    
        this.id = registroDao.contarRegistros() + 1;
    }
    
    //1. Opcion Generar Registro
    
    
    public synchronized RegistroDeParqueo crearRegistro(Vehiculo vehiculo) {
        this.registro = parqueo.generarRegistroDeParqueo(vehiculo, this.id);
        this.id++;
        return registro;
    }
    
    public void persistirRegistro(RegistroDeParqueo registro) {
        System.out.println("REGISTRO GUARDADOS");
    }

    public Map<String, RegistroDeParqueo> getRegistrosDeParqueoActivos() {
        return registrosDeParqueoActivos;
    }
    
    
    
    
}
