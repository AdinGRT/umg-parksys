package modelo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author gian_
 */
public class Ticket {
    private int idTicket;
    private String horarioEntrada;
    private String horarioSalida;
    private double montoTicket;
    private int idUsuario;
    private int idVehiculo;
    private int idTicketStatus;

    public Ticket() {
    }

    public int getIdTicket() {
        return idTicket;
    }

        
    public Ticket(int idUsuario, int idVehiculo, int idTicketStatus) {
        this.horarioEntrada = this.obtenerHorario();
        this.idUsuario = idUsuario;
        this.idVehiculo = idVehiculo;
        this.idTicketStatus = idTicketStatus;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }
    
    private String obtenerHorario() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
               
        String horario = dateFormat.format(date);
        
        return horario;
    }

    public String getHorarioSalida() {
        return horarioSalida;
    }
    
    public void setHorarioSalida(String horarioSalida) {
        this.horarioSalida = horarioSalida;
    }

    public String getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(String horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdTicketStatus() {
        return idTicketStatus;
    }

    public void setIdTicketStatus(int idTicketStatus) {
        this.idTicketStatus = idTicketStatus;
    }

    public double getMontoTicket() {
        return montoTicket;
    }

    public void setMontoTicket(double montoTicket) {
        this.montoTicket = montoTicket;
    }
    
    
    
}
