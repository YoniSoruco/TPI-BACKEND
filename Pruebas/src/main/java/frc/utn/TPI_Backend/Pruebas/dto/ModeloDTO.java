package frc.utn.TPI_Backend.Pruebas.dto;

import lombok.Data;

@Data
public class ModeloDTO {
    private int id;
    private String descripcion;
    private MarcaDTO marca;

    public ModeloDTO(int id, String descripcion, MarcaDTO marca) {
        this.id = id;
        this.descripcion = descripcion;
        this.marca = marca;
    }
}
