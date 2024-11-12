package frc.utn.TPI_Backend.PruebasAgencia.controllers;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;


import java.util.List;

//@Slf4j
@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    private PruebaService pruebaService;

    @Autowired
    public PruebaController(PruebaService pruebaService){
        this.pruebaService=pruebaService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Prueba>> getAllPruebas(){
        return ResponseEntity.ok(pruebaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prueba> obtenerPrueba(@PathVariable int id) {
        // Usar la instancia de pruebaService (pruebaService) para llamar al método no estático
        Prueba prueba = pruebaService.obtenerPruebaPorId(id); // Llamada correcta a través de la instancia

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
            boolean resultado = pruebaService.crearPrueba(idInteresado, idVehiculo, idEmpleado);
            if (resultado) {
                return ResponseEntity.ok("Prueba creada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear la prueba");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/activas")
    public List<Prueba> listarPruebasActivas() {
        return pruebaService.obtenerPruebasActivas();
    }

    @PatchMapping("/finalizar")
    public ResponseEntity<String> finalizarPrueba(
            @RequestParam("idPrueba") int idPrueba,
            @RequestParam("comentario") String comentario) {
        try {
            boolean resultado = pruebaService.finalizarPrueba(idPrueba, comentario);
            if (resultado) {
                return ResponseEntity.ok("Prueba finalizada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prueba no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al finalizar la prueba");
        }
    }
}

