package com.analisisii.g3.parqueo.modelo;

/**
 *
 * @author gian_
 */
public class RegParqueoPanel {
    private int idRegistro;
    private int idPanelES;
    private int idMetodoES;
    private int idTarjeta;
    private int idCliente;
    private int idUsuario;

    public RegParqueoPanel() {
        
    }

    public RegParqueoPanel(int idRegistro, int idPanelES, int idMetodoES, int idUsuario) {
        this.idRegistro = idRegistro;
        this.idPanelES = idPanelES;
        this.idMetodoES = idMetodoES;
        this.idUsuario = idUsuario;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdPanelES() {
        return idPanelES;
    }

    public void setIdPanelES(int idPanelES) {
        this.idPanelES = idPanelES;
    }

    public int getIdMetodoES() {
        return idMetodoES;
    }

    public void setIdMetodoES(int idMetodoES) {
        this.idMetodoES = idMetodoES;
    }

    public int getIdTarjeta() {
        return idTarjeta;
    }

    public void setIdTarjeta(int idTarjeta) {
        this.idTarjeta = idTarjeta;
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
        sb.append("RegParqueoPanel{idRegistro=").append(idRegistro);
        sb.append(", idPanelES=").append(idPanelES);
        sb.append(", idMetodoES=").append(idMetodoES);
        sb.append(", idTarjeta=").append(idTarjeta);
        sb.append(", idCliente=").append(idCliente);
        sb.append(", idUsuario=").append(idUsuario);
        sb.append('}');
        return sb.toString();
    }

    
    
    
}
