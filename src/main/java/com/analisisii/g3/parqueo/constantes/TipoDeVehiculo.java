package com.analisisii.g3.parqueo.constantes;

/**
 *
 * @author gian_
 */
public enum TipoDeVehiculo {
    ERROR("Tipo de Vehiculo no valido."), PARTICULAR("P0", "Vehículo Particular"), MOTOCICLETA("M0", "Motocicleta");

    private String prefijo;
    private String descripcion;

    private TipoDeVehiculo(String descripcion) {
        this.descripcion = descripcion;
    }

    private TipoDeVehiculo(String prefijo, String descripcion) {
        this.prefijo = prefijo;
        this.descripcion = descripcion;
    }

}
