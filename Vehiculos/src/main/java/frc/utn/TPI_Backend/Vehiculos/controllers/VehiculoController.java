package frc.utn.TPI_Backend.Vehiculos.controllers;

import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.services.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private VehiculoService service;

    @Autowired
    public VehiculoController(VehiculoService service){
        this.service=service;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Vehiculo>> getAllVehiculos(){
        return ResponseEntity.ok(service.getAll());
    }


}
