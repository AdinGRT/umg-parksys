package com.serverparksys.constantes;

/**
 *
 * @author gian_
 */
public enum TipoDeVehiculo {
    PARTICULAR("P0", "Vehículo Particular"), MOTOCICLETA("M0", "Motocicleta");
    
    private String prefijo;
    private String descripcion;

    private TipoDeVehiculo(String prefijo, String descripcion) {
        this.prefijo = prefijo;
        this.descripcion = descripcion;
    }
    
    
    
}
