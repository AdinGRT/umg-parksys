package com.analisisii.g3.parqueo.dominio;

/**
 *
 * @author gian_
 */
public class PanelEntradaRFID extends Panel {

    public PanelEntradaRFID(String idPanel) {
        super(idPanel);
    }
    
    public boolean leerDispositivo() {
        return true;
    }
}
