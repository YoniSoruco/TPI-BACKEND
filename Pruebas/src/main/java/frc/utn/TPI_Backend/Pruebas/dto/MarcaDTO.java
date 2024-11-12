package frc.utn.TPI_Backend.Pruebas.dto;

import lombok.Data;

@Data
public class MarcaDTO {
    private int id;
    private String nombre;

    public MarcaDTO(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
}

