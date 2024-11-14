package frc.utn.TPI_Backend.Vehiculos.dto;

import lombok.Data;

import java.util.List;

@Data
public class AgenciaDTO {

    private Coordenadas coordenadasAgencia;
    private double radioAdmitidoKm;
    private List<ZonaRestringida> zonasRestringidas;

    public AgenciaDTO(Coordenadas coordenadasAgencia,double radioAdmitidoKm,List<ZonaRestringida> zonasRestringidas){
        this.coordenadasAgencia = coordenadasAgencia;
        this.radioAdmitidoKm = radioAdmitidoKm;
        this.zonasRestringidas = zonasRestringidas;
    }
}
