package com.analisisii.g3.parqueo.constantes;

/**
 *
 * @author gian_
 */
public enum EstadoRegistroDeParqueo {
    ACTIVO("Adentro del parqueo."), PAGADO("Tarifa pagada."),
    INACTIVO("Salio del parqueo."), PERDIDO("Ticket Extraviado");

    private String descripcion;

    private EstadoRegistroDeParqueo(String descripcion) {
        this.descripcion = descripcion;
    }

}
