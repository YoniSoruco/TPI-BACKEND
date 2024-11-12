package frc.utn.TPI_Backend.Vehiculos.controllers;

import frc.utn.TPI_Backend.Vehiculos.dto.VehiculoDTO;
import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.services.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {

    private VehiculoServiceImpl service;

    @Autowired
    public VehiculoController(VehiculoServiceImpl service){
        this.service=service;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Vehiculo>> getAllVehiculos(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    private VehiculoDTO obtenerVehiculo(@PathVariable int id){
        return service.obtenerVehiculoDTO(id);
    }


}
