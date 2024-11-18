package frc.utn.TPI_Backend.Pruebas.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PosicionDTO {

    private LocalDateTime fechaHora;
    private Coordenadas coordenadas;

    public PosicionDTO(LocalDateTime fechaHora, Coordenadas coordenadas) {
        this.fechaHora = fechaHora;
        this.coordenadas = coordenadas;
    }

}
