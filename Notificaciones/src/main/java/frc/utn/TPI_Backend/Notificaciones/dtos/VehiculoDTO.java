package frc.utn.TPI_Backend.Notificaciones.dtos;

import lombok.Data;


@Data
public class VehiculoDTO {
    private int id;
    private String patente;
    private String modelo;
    private String marca;
    private PosicionDTO ultimaPos;


    public VehiculoDTO(int id, String patente, String modelo,String marca,PosicionDTO ultimaPos) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.ultimaPos = ultimaPos;


    }

}

