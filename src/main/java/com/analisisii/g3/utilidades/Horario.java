package com.analisisii.g3.utilidades;

import java.io.Serializable;
import java.text.*;
import java.util.*;

/**
 *
 * @author gian_
 */
public class Horario implements Serializable {
    
    public String obtenerFechaHora() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        String fechaHora = dateFormat.format(date);
        return fechaHora;
    }
    
    public int obtenerCantidadHoras(String fechaHora1, String fechaHora2){
        return 0;
    }
}
