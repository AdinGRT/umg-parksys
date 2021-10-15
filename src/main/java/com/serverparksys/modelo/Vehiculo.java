package com.serverparksys.modelo;
import com.serverparksys.constantes.TipoDeVehiculo;
/**
 *
 * @author gian_
 */
public class Vehiculo {
    private Integer idVehiculo;
    private String matricula;
    private String marca;
    private String modelo;
    private String color;
    private TipoDeVehiculo tipoVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(int idVehiculo, String matricula, String marca, String modelo, String color, TipoDeVehiculo tipoVehiculo) {
        this.idVehiculo = idVehiculo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.tipoVehiculo = tipoVehiculo;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TipoDeVehiculo getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(TipoDeVehiculo tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vehiculo{idVehiculo=").append(idVehiculo);
        sb.append(", matricula=").append(matricula);
        sb.append(", tipoVehiculo=").append(tipoVehiculo.name());
        sb.append('}');
        return sb.toString();
    }

    
    
}
