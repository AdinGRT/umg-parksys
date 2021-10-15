package com.serverparksys.modelo;

/**
 *
 * @author gian_
 */

public class Tarifa {
    private Integer idTarifa;
    private String tipoVehiculo;
    private String tipoTarifa;
    private Double montoTarifa;

    public Tarifa() {
    }

    public Tarifa(Integer idTarifa, Double montoTarifa) {
        this.idTarifa = idTarifa;
        this.montoTarifa = montoTarifa;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(String tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public Double getMontoTarifa() {
        return montoTarifa;
    }

    public void setMontoTarifa(Double montoTarifa) {
        this.montoTarifa = montoTarifa;
    }

    @Override
    public String toString() {
        return "Tarifa{" + "idTarifa=" + idTarifa + ", tipoVehiculo=" + tipoVehiculo + ", tipoTarifa=" + tipoTarifa + ", montoTarifa=" + montoTarifa + '}';
    }
    
    
}
