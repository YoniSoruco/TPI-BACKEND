package frc.utn.TPI_Backend.Pruebas.dto;

import lombok.Data;

@Data
public class InteresadoDTO {

    private int id;
    private String tipoDocumento;
    private String documento;
    private String nombre;
    private String apellido;

    public InteresadoDTO(int id, String tipoDocumento, String documento, String nombre, String apellido) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
