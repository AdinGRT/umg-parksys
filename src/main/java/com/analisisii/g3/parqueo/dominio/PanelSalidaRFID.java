package com.analisisii.g3.parqueo.dominio;

/**
 *
 * @author gian_
 */
public class PanelSalidaRFID extends Panel {

    public PanelSalidaRFID(String idPanel) {
        super(idPanel);
    }

    public boolean leerDispositivo() {
        return true;
    }
    
    public boolean procesarPago() {
        return true;
    }
    
}
