package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Tarifario;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gian_
 */
public class Parqueo implements ParqueoInterface {

    private String nombreEstacionamiento;
    private String direccion;
    private HashMap<String, Tarifario> tarifario;
    
    private int contadorParticulares;
    private int contadorMotocicletas;
    private final int maximoVehiculos;
    private final int maximoMotocicletas;
    
    
    private HashMap<String, Panel> panelesDeEntrada;
    private HashMap<String, Panel> panelesDeSalida;
    
    //private HashMap<String, Vehiculo> vehiculosEnParqueo;
    private Map<String, RegistroDeParqueo> registrosDeParqueoActivos;
    
    private static Parqueo parqueo = null;

    private Parqueo(String nombreEstacionamiento, String direccion, int contadorParticulares, int contadorMotocicletas, int maximoVehiculos, int maximoMotocicletas, Map<String, RegistroDeParqueo> registrosDeParqueoActivos) {
        this.nombreEstacionamiento = nombreEstacionamiento;
        this.direccion = direccion;
        this.contadorParticulares = contadorParticulares;
        this.contadorMotocicletas = contadorMotocicletas;
        this.maximoVehiculos = maximoVehiculos;
        this.maximoMotocicletas = maximoMotocicletas;
        this.registrosDeParqueoActivos = registrosDeParqueoActivos;
    }


    public static synchronized Parqueo obtenerInstanciaDeParqueo(String nombreEstacionamiento, String direccion, int contadorParticulares, int contadorMotocicletas, int maximoVehiculos, int maximoMotocicletas, Map<String, RegistroDeParqueo> registrosDeParqueoActivos) {
        if (parqueo == null) {
            parqueo = new Parqueo(nombreEstacionamiento, direccion, contadorParticulares, contadorMotocicletas, maximoVehiculos, maximoMotocicletas, registrosDeParqueoActivos);
        }
        return parqueo;
    }

    @Override
    public synchronized RegistroDeParqueo generarRegistroDeParqueo(Vehiculo vehiculo) {
        if (this.estaLleno(vehiculo.getTipoVehiculo())) {
            System.out.println("EL PARQUEO SE ENCUENTRA EN SU CAPACIDAD MAXIMA.");
        }
        RegistroDeParqueo registro = new RegistroDeParqueo();
        vehiculo.setRegistroDeParqueo(registro);
        
        this.registrosDeParqueoActivos.put(vehiculo.getMatricula(), registro);
        this.incrementarContadoresPorTipo(vehiculo.getTipoVehiculo());
        
        return registro;
    }

    @Override
    public boolean estaLleno(TipoDeVehiculo tipoDeVehiculo) {
        if (tipoDeVehiculo == TipoDeVehiculo.PARTICULAR) {
            return this.contadorParticulares >= this.maximoVehiculos;
        } 
        else if (tipoDeVehiculo == TipoDeVehiculo.MOTOCICLETA) {
            return this.contadorParticulares >= this.maximoMotocicletas;
        } else {
            return true;
        }
    } 
    
    @Override
    public boolean estaLleno(){
        return true;
    }
    
    @Override
    public void agregarPanel() {
    }
    
    @Override
    public void incrementarContadoresPorTipo(TipoDeVehiculo tipoDeVehiculo){
        if (tipoDeVehiculo == TipoDeVehiculo.PARTICULAR) {
            contadorParticulares++;
        } else if (tipoDeVehiculo == TipoDeVehiculo.MOTOCICLETA) {
            contadorMotocicletas++;
        }
    }
    
}
