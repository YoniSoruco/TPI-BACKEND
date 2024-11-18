package frc.utn.TPI_Backend.Notificaciones.dtos;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestVehiculo {
    private int idVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;


    public RequestVehiculo(int id, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
        this.idVehiculo = id;
        this.fechaInicio = fechaHoraInicio;
        this.fechaFin = fechaHoraFin;
    }
}