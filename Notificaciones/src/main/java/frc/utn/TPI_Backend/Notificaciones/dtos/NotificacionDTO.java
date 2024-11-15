package frc.utn.TPI_Backend.Notificaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificacionDTO {
    VehiculoDTO vehiculoDTO;
    InteresadoDTO interesadoDTO;
    LocalDateTime fechaNotificacion;
    EmpleadoDTO empleadoDTO;
}
