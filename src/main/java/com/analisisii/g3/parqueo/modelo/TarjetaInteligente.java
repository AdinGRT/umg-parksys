package com.analisisii.g3.parqueo.modelo;

/**
 *
 * @author gian_
 */
public class TarjetaInteligente {
    private Integer id;
    private String numeroDispositivo;

    public TarjetaInteligente(Integer id, String numeroDispositivo) {
        this.id = id;
        this.numeroDispositivo = numeroDispositivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeroDispositivo() {
        return numeroDispositivo;
    }

    public void setNumeroDispositivo(String numeroDispositivo) {
        this.numeroDispositivo = numeroDispositivo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TarjetaInteligente{id=").append(id);
        sb.append(", numeroDispositivo=").append(numeroDispositivo);
        sb.append('}');
        return sb.toString();
    }
    
    
}
