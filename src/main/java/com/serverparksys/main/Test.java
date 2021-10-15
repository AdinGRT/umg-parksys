package com.serverparksys.main;

import com.serverparksys.dao.TarifaDAO;
import com.serverparksys.modelo.Tarifario;
import java.util.List;

/**
 *
 * @author gian_
 */
public class Test {
    public static void main(String[] args) {
        
        Tarifario tarifa = new Tarifario(1, 5.0);
        TarifaDAO tarifaDao = new TarifaDAO();
        List<Tarifario> tarifas = tarifaDao.verTarifas();
        System.out.println(tarifas);
        tarifaDao.actualizarTarifa(tarifa);
        tarifas = tarifaDao.verTarifas();
        System.out.println(tarifas);
        
    }
}
