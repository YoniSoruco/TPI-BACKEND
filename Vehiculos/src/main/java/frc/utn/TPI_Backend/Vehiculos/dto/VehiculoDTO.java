package frc.utn.TPI_Backend.Vehiculos.dto;

import lombok.Data;

import java.util.List;

@Data
public class VehiculoDTO {
    private int id;
    private String patente;
    private String modelo;
    private String marca;



    public VehiculoDTO(int id, String patente, String modelo,String marca) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;


    }

}
