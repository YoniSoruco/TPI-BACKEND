package frc.utn.TPI_Backend.Vehiculos.controllers;

import frc.utn.TPI_Backend.Vehiculos.dtos.VehiculoDTO;
import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.services.VehiculoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/vehiculos/protegido-vehiculos")
public class ProtegidoVehiculoController {
    private VehiculoServiceImpl vehiculoService;

    @Autowired
    public ProtegidoVehiculoController(VehiculoServiceImpl vehiculoService){
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

    @GetMapping("/posiciones")
    private VehiculoDTO obtenerVehiculoConPos(@RequestParam int idVehiculo, @RequestParam LocalDateTime fechaInicio, @RequestParam(required = false) LocalDateTime fechaFin){
        return vehiculoService.obtenerVehiculoConPos(idVehiculo,fechaInicio,fechaFin);
    }
}
