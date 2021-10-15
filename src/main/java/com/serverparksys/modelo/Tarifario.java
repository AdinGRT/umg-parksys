package com.serverparksys.modelo;

import com.serverparksys.constantes.TipoDeTarifa;
import com.serverparksys.constantes.TipoDeVehiculo;

/**
 *
 * @author gian_
 */

public class Tarifario {
    private Integer idTarifa;
    private Double montoTarifa;
    private TipoDeTarifa tipoTarifa;
    private TipoDeVehiculo tipoVehiculo;

    public Tarifario() {
    }

    public Tarifario(Integer idTarifa, Double montoTarifa, TipoDeTarifa tipoTarifa, TipoDeVehiculo tipoVehiculo) {
        this.idTarifa = idTarifa;
        this.montoTarifa = montoTarifa;
        this.tipoTarifa = tipoTarifa;
        this.tipoVehiculo = tipoVehiculo;
    }

    public Integer getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(Integer idTarifa) {
        this.idTarifa = idTarifa;
    }

    public Double getMontoTarifa() {
        return montoTarifa;
    }

    public void setMontoTarifa(Double montoTarifa) {
        this.montoTarifa = montoTarifa;
    }

    public TipoDeTarifa getTipoTarifa() {
        return tipoTarifa;
    }

    public void setTipoTarifa(TipoDeTarifa tipoTarifa) {
        this.tipoTarifa = tipoTarifa;
    }

    public TipoDeVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoDeVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Tarifario{idTarifa=").append(idTarifa);
        sb.append(", montoTarifa=").append(montoTarifa);
        sb.append(", tipoTarifa=").append(tipoTarifa.name());
        sb.append(", tipoVehiculo=").append(tipoVehiculo.name());
        sb.append('}');
        return sb.toString();
    }

    
}
