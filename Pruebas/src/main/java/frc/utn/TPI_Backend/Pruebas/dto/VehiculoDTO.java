package frc.utn.TPI_Backend.Pruebas.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class VehiculoDTO {

    private int id;
    private String patente;
    private String modeloDescripcion;
    private String marcaNombre;


    public VehiculoDTO(int id, String patente, String modeloDescripcion, String marcaNombre) {
        this.id = id;
        this.patente = patente;
        this.modeloDescripcion = modeloDescripcion;
        this.marcaNombre = marcaNombre;

    }


}
