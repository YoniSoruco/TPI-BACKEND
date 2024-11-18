package frc.utn.TPI_Backend.Pruebas.dtos;

import lombok.Data;

import java.util.List;

@Data
public class VehiculoDTO {
    private int id;
    private String patente;
    private String modelo;
    private String marca;
    private PosicionDTO ultimaPos;
    private List<PosicionDTO> posicionDTOS;

    public VehiculoDTO(int id, String patente, String modelo,String marca,PosicionDTO ultimaPos,List<PosicionDTO> posicionDTOS) {
        this.id = id;
        this.patente = patente;
        this.modelo = modelo;
        this.marca = marca;
        this.ultimaPos = ultimaPos;
        this.posicionDTOS = posicionDTOS;
    }

    public VehiculoDTO(int id, String patente, String modelo,String marca,PosicionDTO ultimaPos){
        this(id, patente, modelo, marca, ultimaPos, null);
    }

    public VehiculoDTO() {
    }

}



