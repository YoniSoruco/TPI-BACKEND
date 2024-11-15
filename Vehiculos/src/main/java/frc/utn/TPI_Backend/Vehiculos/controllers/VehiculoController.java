package frc.utn.TPI_Backend.Vehiculos.controllers;

import frc.utn.TPI_Backend.Vehiculos.dto.VehiculoDTO;
import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.services.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    private VehiculoServiceImpl vehiculoService;

    @Autowired
    public VehiculoController(VehiculoServiceImpl vehiculoService){
        this.vehiculoService = vehiculoService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Vehiculo>> getAllVehiculos(){
        return ResponseEntity.ok(vehiculoService.getAll());
    }

    @GetMapping("/{id}")
    private VehiculoDTO obtenerVehiculo(@PathVariable int id){
        return vehiculoService.obtenerVehiculoDTO(id);
    }


}
