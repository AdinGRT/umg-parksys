package com.analisisii.g3.utilidades;

import java.io.Serializable;
import java.text.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
    
    public long obtenerCantidadHoras(String fechaHora1, String fechaHora2) throws ParseException{
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date hora1 = dateFormat.parse(fechaHora1);
        Date hora2 = dateFormat.parse(fechaHora2);
        long diferencia = hora2.getTime() - hora1.getTime();
        TimeUnit time = TimeUnit.HOURS;
        long dif = time.convert(diferencia, TimeUnit.MILLISECONDS);
        return dif;
    }
}
