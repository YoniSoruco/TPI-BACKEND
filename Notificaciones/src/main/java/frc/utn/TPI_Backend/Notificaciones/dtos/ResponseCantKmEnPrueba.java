package frc.utn.TPI_Backend.Notificaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseCantKmEnPrueba {

    VehiculoDTO vehiculoDTO;
    double kilometrosRecorridos;
    LocalDateTime fechaInicio;
    LocalDateTime fechaFin;

}
