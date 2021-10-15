package com.serverparksys.dominio;

/**
 *
 * @author gian_
 */
public class Estacionamiento {

    private String nombreEstacionamiento;
    private String direccion;

    public Estacionamiento() {
    }

    public Estacionamiento(String nombreEstacionamiento, String direccion) {
        this.nombreEstacionamiento = nombreEstacionamiento;
        this.direccion = direccion;
    }
    
    
    
    public boolean agregarPanel() {
        return true;
    }

    public boolean quitarPanel() {
        return true;
    }

    public String getNombreEstacionamiento() {
        return nombreEstacionamiento;
    }

    public void setNombreEstacionamiento(String nombreEstacionamiento) {
        this.nombreEstacionamiento = nombreEstacionamiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    

}
