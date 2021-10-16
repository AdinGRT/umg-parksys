package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;

/**
 *
 * @author gian_
 */
public class PanelEntradaTicket extends Panel {

    public PanelEntradaTicket(String idPanel) {
        super(idPanel);
    }
    
    public boolean imprimirTicket(RegistroDeParqueo ticket) {
        return true;
    }
}
