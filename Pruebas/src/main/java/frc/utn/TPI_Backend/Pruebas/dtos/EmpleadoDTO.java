package frc.utn.TPI_Backend.Pruebas.dtos;

import lombok.Data;

@Data
public class EmpleadoDTO {
    private int legajo;
    private String nombre;
    private String apellido;
    private String telefono;

    public EmpleadoDTO(int legajo, String nombre, String apellido, String telefono) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }




}
