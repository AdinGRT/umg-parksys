package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.constantes.MetodoDeEntradaSalida;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import java.io.Serializable;

/**
 *
 * @author gian_
 */
public class PanelSalida implements Serializable {
    private int idPanel;
    private String descripcionPanel;
    //private MetodoDeEntradaSalida metodoEntradaSalida;

    public PanelSalida(int idPanel, String nombrePanel) {
        this.idPanel = idPanel;
        this.descripcionPanel = nombrePanel;
    }

    public int getIdPanel() {
        return idPanel;
    }

    public String getDescripcionPanel() {
        return descripcionPanel;
    }
    
    public String identificarMatricula() {
        //OCR u otro metodo de reconocimiento
        return "000ZZZ";
    }
    
    public boolean leerTicket() {
        return true;
    }
    
    public boolean leerDispositivo() {
        return true;
    }
    
    public boolean leerQR() {
        return true;
    }
    
    public boolean procesarPago() {
        return true;
    }
}
