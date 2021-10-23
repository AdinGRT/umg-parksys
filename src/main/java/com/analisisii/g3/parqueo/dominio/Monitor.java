package com.analisisii.g3.parqueo.dominio;

import java.io.Serializable;

/**
 *
 * @author gian_
 */
public class Monitor implements Serializable {
    private int idMonitor;
    private String descripcionMonitor;

    public Monitor(int idMonitor, String descripcionMonitor) {
        this.idMonitor = idMonitor;
        this.descripcionMonitor = descripcionMonitor;
    }

    public int getIdMonitor() {
        return idMonitor;
    }

    public String getDescripcionMonitor() {
        return descripcionMonitor;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Monitor{idMonitor=").append(idMonitor);
        sb.append(", descripcionMonitor=").append(descripcionMonitor);
        sb.append('}');
        return sb.toString();
    }
    
    
    
}
