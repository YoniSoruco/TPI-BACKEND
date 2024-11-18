package frc.utn.TPI_Backend.Notificaciones.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class PruebaDTO {

    private VehiculoDTO vehiculoDTO;
    private InteresadoDTO interesado;
    private EmpleadoDTO empleado;
    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;
    private String comentario;

    public PruebaDTO(VehiculoDTO vehiculoDTO, InteresadoDTO interesado, EmpleadoDTO empleado, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String comentario) {
        this.vehiculoDTO = vehiculoDTO;
        this.interesado = interesado;
        this.empleado = empleado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
    }


}