package frc.utn.TPI_Backend.Notificaciones.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosicionDTO {

    private LocalDateTime fechaHora;
    private double latitud;
    private double longitud;

    public PosicionDTO(LocalDateTime fechaHora, double latitud, double longitud) {
        this.fechaHora = fechaHora;
        this.latitud = latitud;
        this.longitud = longitud;
    }

}
