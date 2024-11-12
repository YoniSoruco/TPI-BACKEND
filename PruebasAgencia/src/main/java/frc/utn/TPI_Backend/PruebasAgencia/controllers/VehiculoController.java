package frc.utn.TPI_Backend.PruebasAgencia.controllers;

import frc.utn.TPI_Backend.PruebasAgencia.models.Vehiculo;
import frc.utn.TPI_Backend.PruebasAgencia.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/{id}")
    public Vehiculo getVehiculo(@PathVariable int id) {
        Vehiculo vehiculo = vehiculoService.getVehiculoById(id);
        if (vehiculo == null) {
            throw new RuntimeException("Vehículo no encontrado con id " + id);
        }
        return vehiculo;
    }
}
