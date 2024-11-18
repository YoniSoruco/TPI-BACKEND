package frc.utn.TPI_Backend.Pruebas.dtos;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RequestVehiculo {
    private int idVehiculo;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public RequestVehiculo(int idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.idVehiculo = idVehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }
}
