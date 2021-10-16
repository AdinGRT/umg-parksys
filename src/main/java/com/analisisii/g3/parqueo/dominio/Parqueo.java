package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Tarifario;
import com.analisisii.g3.parqueo.modelo.Vehiculo;
import java.util.HashMap;

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
    
    private HashMap<String, Vehiculo> vehiculosEnParqueo;
    private HashMap<String, RegistroDeParqueo> registrosDeParqueoActivos;
    
    private static Parqueo parqueo = null;

    private Parqueo(int maximoVehiculos, int maximoMotocicletas) {
        //Inicializar provisionalmente
        this.nombreEstacionamiento = "Parqueo Grupo 3";
        this.direccion = "4ta Av. Zona 1";
        this.registrosDeParqueoActivos = new HashMap();
        this.vehiculosEnParqueo = new HashMap();
        this.maximoVehiculos = maximoVehiculos;
        this.maximoMotocicletas = maximoMotocicletas;
    }

    public static synchronized Parqueo obtenerInstanciaDeParqueo() {
        if (parqueo == null) {
            parqueo = new Parqueo(20, 20);
        }
        return parqueo;
    }

    @Override
    public synchronized RegistroDeParqueo generarRegistroDeParqueo(Vehiculo vehiculo) {
        if (this.estaLleno(vehiculo.getTipoVehiculo())) {
            System.out.println("EL PARQUEO SE ENCUENTRA EN SU CAPACIDAD MAXIMA.");
        }
        RegistroDeParqueo registro = new RegistroDeParqueo();
        //System.out.println(registro);
        vehiculo.setRegistroDeParqueo(registro);
        
        this.registrosDeParqueoActivos.put("nada", registro);
        this.vehiculosEnParqueo.put(vehiculo.getMatricula(), vehiculo);
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
