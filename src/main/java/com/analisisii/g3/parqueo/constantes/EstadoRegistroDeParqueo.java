package com.analisisii.g3.parqueo.constantes;

/**
 *
 * @author gian_
 */
public enum EstadoRegistroDeParqueo {
    ERROR ("Estado no valido"), ACTIVO("Adentro del parqueo."), PAGADO("Tarifa pagada."),
    INACTIVO("Salio del parqueo."), EXTRAVIADO("Ticket Extraviado");

    private String descripcion;

    private EstadoRegistroDeParqueo(String descripcion) {
        this.descripcion = descripcion;
    }

}
