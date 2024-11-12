package frc.utn.TPI_Backend.Vehiculos.dto;

import lombok.Data;

import java.text.DateFormat;

@Data
public class PruebaDTO {
    private int id;
    private int idVehiculo;
    private int idInteresado;
    private int idEmpleado;
    private DateFormat fechaHoraInicio;
    private DateFormat fechaHoraFin;
    private String comentarios;
    public PruebaDTO(int id, int idVehiculo, int idInteresado, int idEmpleado, DateFormat fechaHoraInicio, DateFormat fechaHoraFin, String comentarios) {
        this.id = id;
        this.idVehiculo = idVehiculo;
        this.idInteresado = idInteresado;
        this.idEmpleado = idEmpleado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentarios = comentarios;
    }
}

