package com.serverparksys.dominio;

import com.serverparksys.modelo.TicketDeParqueo;

/**
 *
 * @author gian_
 */
public class PanelEntradaTicket extends Panel {

    public PanelEntradaTicket(String idPanel) {
        super(idPanel);
    }
    
    public boolean imprimirTicket(TicketDeParqueo ticket) {
        return true;
    }
}
