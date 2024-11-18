package frc.utn.TPI_Backend.Vehiculos.dtos;
import lombok.Data;

@Data
public class Coordenadas {

    private double lat;

    private double lon;

    public Coordenadas(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

}

