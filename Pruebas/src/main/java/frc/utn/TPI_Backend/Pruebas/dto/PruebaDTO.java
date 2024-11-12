package frc.utn.TPI_Backend.Pruebas.dto;

import frc.utn.TPI_Backend.Pruebas.models.Empleado;
import frc.utn.TPI_Backend.Pruebas.models.Interesado;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class PruebaDTO {
    private int id;
    private VehiculoDTO vehiculoDTO;
    private Interesado interesado;
    private Empleado empleado;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String comentario;

    public PruebaDTO(int id, VehiculoDTO vehiculoDTO, Interesado interesado, Empleado empleado, String fechaHoraInicio, String fechaHoraFin, String comentario) {
        this.id = id;
        this.vehiculoDTO = vehiculoDTO;
        this.interesado = interesado;
        this.empleado = empleado;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.comentario = comentario;
    }
}
