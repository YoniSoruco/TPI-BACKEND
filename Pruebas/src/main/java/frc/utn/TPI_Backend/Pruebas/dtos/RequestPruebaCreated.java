package frc.utn.TPI_Backend.Pruebas.dtos;

import lombok.Data;

@Data
public class RequestPruebaCreated {
    private int idVehiculo;
    private int idInteresado;
    private int idEmpleado;

    public RequestPruebaCreated(int idVehiculo, int idInteresado, int idEmpleado){
        this.idVehiculo = idVehiculo;
        this.idInteresado = idInteresado;
        this.idEmpleado = idEmpleado;

    }
}

