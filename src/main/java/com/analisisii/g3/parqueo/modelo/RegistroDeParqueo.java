package com.analisisii.g3.parqueo.modelo;

import com.analisisii.g3.parqueo.constantes.EstadoRegistroDeParqueo;
import com.analisisii.g3.utilidades.Horario;
import java.io.Serializable;

/**
 *
 * @author gian_
 */
public class RegistroDeParqueo implements Serializable{
    private Integer idRegistroParqueo;
    private String fechaHoraEntrada;
    private String fechaHoraSalida;
    private Integer idVehiculo;
    private EstadoRegistroDeParqueo estado;
    
    //private Integer cantidadTiempo;
    //private Double montoTicket;
    private Horario horario = new Horario();
    
    public RegistroDeParqueo(){
        this.fechaHoraEntrada = this.horario.obtenerFechaHora();
        this.estado = EstadoRegistroDeParqueo.ACTIVO;
    }

    public String getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public EstadoRegistroDeParqueo getEstado() {
        return estado;
    }

    public String getFechaHoraEntrada() {
        return fechaHoraEntrada;
    }

    public Integer getIdRegistroParqueo() {
        return idRegistroParqueo;
    }
    
    public void setIdRegistroParqueo(Integer idRegistroParqueo) {
        this.idRegistroParqueo = idRegistroParqueo;
    }

    public void setFechaHoraEntrada(String fechaHoraEntrada) {
        this.fechaHoraEntrada = fechaHoraEntrada;
    }

    public void setFechaHoraSalida(String fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public void setEstado(EstadoRegistroDeParqueo estado) {
        this.estado = estado;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RegistroDeParqueo{idRegistroParqueo=").append(idRegistroParqueo);
        sb.append(", fechaHoraEntrada=").append(fechaHoraEntrada);
        sb.append(", fechaHoraSalida=").append(fechaHoraSalida);
        sb.append(", estado=").append(estado.name());
        sb.append('}');
        return sb.toString();
    }

    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(Integer idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

}
