package com.analisisii.g3.cobros.all;

import com.analisisii.g3.parqueo.dao.RegistroDeParqueoDAO;
import com.analisisii.g3.parqueo.dao.TarifaDAO;
import com.analisisii.g3.parqueo.modelo.RegistroDeParqueo;
import com.analisisii.g3.parqueo.modelo.Tarifario;
import com.analisisii.g3.utilidades.Horario;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gian_
 */
public class CobroLogica {

    private RegistroDeParqueoDAO registroDao = new RegistroDeParqueoDAO();
    private RegistroDeParqueo registro;
    private TarifaDAO tarifaDao = new TarifaDAO();
    private Tarifario tarifa = new Tarifario();

    public CobroLogica() {
    }

    //1. Validar registro
    public synchronized boolean estaActivoRegistro(int idRegistro) {
        this.registro = registroDao.buscarRegistroPorId(idRegistro);
        if (registro.getEstado().name().equalsIgnoreCase("ACTIVO")) {
            return true;
        } else {
            return false;
        }
    }

    //2. Si esta activo
    public synchronized double determinarMonto() {
        Horario horario = new Horario();
        String fechaHoraEntrada = this.registro.getFechaHoraEntrada();
        String fechaHoraPago = horario.obtenerFechaHora();
        String tipoVehiculo = this.registroDao.obtenerTipoVehiculo(this.registro.getIdRegistroParqueo());
        List<Tarifario> tarifas = this.tarifaDao.verTarifas();
        
        try {
            double horasPagar = horario.obtenerCantidadHoras(fechaHoraEntrada, fechaHoraPago);
            if (horasPagar <= 8) {
                for(Tarifario t : tarifas) {
                    if(t.getTipoVehiculo().name().equals(tipoVehiculo) && t.getTipoTarifa().name().equals("HORA")) {
                        this.tarifa = t;
                    }
                }
                double monto = (long) (horasPagar * this.tarifa.getMontoTarifa());
                return monto;
            } else if (horasPagar > 8 && horasPagar < 16) {
                for(Tarifario t : tarifas) {
                    if(t.getTipoVehiculo().name().equals(tipoVehiculo) && t.getTipoTarifa().name().equals("DIA")) {
                        this.tarifa = t;
                    }
                }
                double monto = this.tarifa.getMontoTarifa();
                return monto;
            } else {
                return 0;
            }
        } catch (ParseException ex) {
            Logger.getLogger(CobroLogica.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }
}
