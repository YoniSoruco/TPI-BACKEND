package frc.utn.TPI_Backend.Pruebas.controllers;

import frc.utn.TPI_Backend.Pruebas.dto.PruebaDTO;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<PruebaDTO> getPruebaById(@PathVariable int id) {
        PruebaDTO pruebaDTO = service.getPruebaById(id);
        return ResponseEntity.ok(pruebaDTO);
    }
}
