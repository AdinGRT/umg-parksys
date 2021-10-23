package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.constantes.TipoDeVehiculo;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Vehiculo;

/**
 *
 * @author gian_
 */
public interface ParqueoInterface {
    public RegistroDeParqueo generarRegistroDeParqueo(Vehiculo vehiculo, int id);
    
    public boolean estaLleno(TipoDeVehiculo tipoDeVehiculo);
    
    public boolean estaLleno();
    
    public void agregarPanel();
    
    public void incrementarContadoresPorTipo(TipoDeVehiculo tipoDeVehiculo);
    
}
