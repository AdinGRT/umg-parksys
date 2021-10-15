package com.serverparksys.main;

import com.serverparksys.dao.TarifaDAO;
import com.serverparksys.modelo.Tarifa;
import java.util.List;

/**
 *
 * @author gian_
 */
public class Test {
    public static void main(String[] args) {
        
        Tarifa tarifa = new Tarifa(1, 5.0);
        TarifaDAO tarifaDao = new TarifaDAO();
        List<Tarifa> tarifas = tarifaDao.verTarifas();
        System.out.println(tarifas);
        tarifaDao.actualizarTarifa(tarifa);
        tarifas = tarifaDao.verTarifas();
        System.out.println(tarifas);
        
    }
}
