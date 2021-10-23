package com.analisisii.g3.parqueo.dominio;

import com.analisisii.g3.parqueo.constantes.MetodoDeEntradaSalida;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import java.io.Serializable;

/**
 *
 * @author gian_
 */
public class PanelEntrada implements Serializable {
    private int idPanel;
    private String descripcionPanel;
    //private MetodoDeEntradaSalida metodoDeEntradaSalida;

    
    
    public PanelEntrada(int idPanel, String nombrePanel) {
        this.idPanel = idPanel;
        this.descripcionPanel = nombrePanel;
    }
    
    public String identificarMatricula() {
        //OCR u otro metodo de reconocimiento
        return "000ZZZ";
    }
    
    public void imprimirTicket(String matricula, RegistroDeParqueo registro) {
        System.out.println(matricula + "\n" + registro.getIdRegistroParqueo() + "\n" + registro.getFechaHoraEntrada());
    }
    
    public boolean leerDispositivo() {
        return true;
    }
    
    public boolean leerQR() {
        return true;
    }   

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PanelEntrada{idPanel=").append(idPanel);
        sb.append(", descripcionPanel=").append(descripcionPanel);
        sb.append('}');
        return sb.toString();
    }

    public int getIdPanel() {
        return idPanel;
    }

    public String getDescripcionPanel() {
        return descripcionPanel;
    }
    
    
}
