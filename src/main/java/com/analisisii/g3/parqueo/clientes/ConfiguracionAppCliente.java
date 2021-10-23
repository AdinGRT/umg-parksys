package com.analisisii.g3.parqueo.clientes;

import java.util.ResourceBundle;

/**
 *
 * @author gian_
 */
public class ConfiguracionAppCliente {
    
    private String nombreApp;
    private String descripcionApp;
    
    private int idPanel;
    private String descripcionPanel;
    
    private boolean habilitarTicket;
    private boolean habilitarRFID;
    private boolean habilitarQR;
    
    private String ipServidor;
    private int puerto;
    
    ResourceBundle applicationProperties = ResourceBundle.getBundle("application");

    public ConfiguracionAppCliente() {
        this.nombreApp = applicationProperties.getString("app.nombre");
        this.descripcionApp = applicationProperties.getString("app.descripcion");
        this.habilitarTicket = Boolean.parseBoolean(applicationProperties.getString("panel.habilitar-ticket"));
        this.habilitarRFID = Boolean.parseBoolean(applicationProperties.getString("panel.habilitar-rfid"));
        this.habilitarQR = Boolean.parseBoolean(applicationProperties.getString("panel.habilitar-qr"));
        this.ipServidor = applicationProperties.getString("servidor.ip");
        this.puerto = Integer.parseInt(applicationProperties.getString("servidor.puerto"));
        this.idPanel = Integer.parseInt(applicationProperties.getString("panel.id"));
        this.descripcionPanel = applicationProperties.getString("panel.descripcion");
    }

    public int getIdPanel() {
        return idPanel;
    }

    public String getDescripcionPanel() {
        return descripcionPanel;
    }

    public String getNombreApp() {
        return nombreApp;
    }

    public String getDescripcionApp() {
        return descripcionApp;
    }

    public boolean isHabilitarTicket() {
        return habilitarTicket;
    }

    public boolean isHabilitarRFID() {
        return habilitarRFID;
    }

    public boolean isHabilitarQR() {
        return habilitarQR;
    }

    public String getIpServidor() {
        return ipServidor;
    }

    public int getPuerto() {
        return puerto;
    }
    
    
    
}
