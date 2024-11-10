package frc.utn.TPI_Backend.PruebasAgencia.controllers;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.HttpStatus;


import java.util.List;

//@Slf4j
@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    private PruebaService service;

    @Autowired
    public PruebaController(PruebaService service){
        this.service=service;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Prueba>> getAllPruebas(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> obtenerPrueba(@PathVariable int id) {
        // Usar la instancia de pruebaService (service) para llamar al método no estático
        Prueba prueba = service.obtenerPruebaPorId(id); // Llamada correcta a través de la instancia

        if (prueba != null) {
            return ResponseEntity.ok(prueba);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<String> crearPrueba(
            @RequestParam("idInteresado") int idInteresado,
            @RequestParam("idVehiculo") int idVehiculo,
            @RequestParam("idEmpleado") int idEmpleado) {

        try {
            // Llamamos al servicio para crear la prueba
            boolean resultado = service.crearPrueba(idInteresado, idVehiculo, idEmpleado);
            if (resultado) {
                return ResponseEntity.ok("Prueba creada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la prueba");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}

