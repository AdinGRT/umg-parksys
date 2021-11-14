package com.analisisii.g3.cobros.all;

/**
 *
 * @author gian_
 */
public class Factura {
    private int idFactura;
    private String fechaHoraEmision;
    private String nombreCliente;
    private String direccionCliente;
    private String nitCliente;
    private double totalFactura;
    private int idRegistroParqueo;
    private int idKioskoPago;
    private int idCliente;
    private int idUsuario;

    public Factura() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public String getFechaHoraEmision() {
        return fechaHoraEmision;
    }

    public void setFechaHoraEmision(String fechaHoraEmision) {
        this.fechaHoraEmision = fechaHoraEmision;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getNitCliente() {
        return nitCliente;
    }

    public void setNitCliente(String nitCliente) {
        this.nitCliente = nitCliente;
    }

    public double getTotalFactura() {
        return totalFactura;
    }

    public void setTotalFactura(double totalFactura) {
        this.totalFactura = totalFactura;
    }

    public int getIdRegistroParqueo() {
        return idRegistroParqueo;
    }

    public void setIdRegistroParqueo(int idRegistroParqueo) {
        this.idRegistroParqueo = idRegistroParqueo;
    }

    public int getIdKioskoPago() {
        return idKioskoPago;
    }

    public void setIdKioskoPago(int idKioskoPago) {
        this.idKioskoPago = idKioskoPago;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Factura{idFactura=").append(idFactura);
        sb.append(", fechaHoraEmision=").append(fechaHoraEmision);
        sb.append(", nombreCliente=").append(nombreCliente);
        sb.append(", direccionCliente=").append(direccionCliente);
        sb.append(", nitCliente=").append(nitCliente);
        sb.append(", totalFactura=").append(totalFactura);
        sb.append(", idRegistroParqueo=").append(idRegistroParqueo);
        sb.append(", idKioskoPago=").append(idKioskoPago);
        sb.append(", idCliente=").append(idCliente);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append('}');
        return sb.toString();
    }
    
    
    
    
}
