package frc.utn.TPI_Backend.Pruebas.dto;

import lombok.Data;

@Data
public class RequestPrueba {
    private int idVehiculo;
    private int idInteresado;
    private int idEmpleado;

    public RequestPrueba(int idVehiculo,int idInteresado,int idEmpleado){
        this.idVehiculo = idVehiculo;
        this.idInteresado = idInteresado;
        this.idEmpleado = idEmpleado;

    }
}

