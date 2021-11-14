package com.analisisii.g3.cobros.all;

/**
 *
 * @author gian_
 */
public class DetalleFactura {
    private int idFactura;
    private int idTarifa;
    private int cantidad;
    private double descuento;
    private double monto;

    public DetalleFactura() {
    }

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getIdTarifa() {
        return idTarifa;
    }

    public void setIdTarifa(int idTarifa) {
        this.idTarifa = idTarifa;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("DetalleFactura{idFactura=").append(idFactura);
        sb.append(", idTarifa=").append(idTarifa);
        sb.append(", cantidad=").append(cantidad);
        sb.append(", descuento=").append(descuento);
        sb.append(", monto=").append(monto);
        sb.append('}');
        return sb.toString();
    }
    
    
}
