package frc.utn.TPI_Backend.Pruebas.controllers;

import frc.utn.TPI_Backend.Pruebas.dto.PruebaDTO;
import frc.utn.TPI_Backend.Pruebas.dto.RequestPruebaCreated;
import frc.utn.TPI_Backend.Pruebas.dto.RequestPruebaFinalized;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j
@RestController
@RequestMapping("/api/pruebas")
public class PruebaController {

    private PruebaService pruebaService;

    @Autowired
    public PruebaController(PruebaService pruebaService){
        this.pruebaService = pruebaService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Prueba>> getAllPruebas(){
        return ResponseEntity.ok(pruebaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PruebaDTO> getPruebaById(@PathVariable int id) {
        PruebaDTO pruebaDTO = pruebaService.getPruebaById(id);
        return ResponseEntity.ok(pruebaDTO);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prueba crearPrueba(@RequestBody RequestPruebaCreated pruebaNueva){
        return pruebaService.crearPrueba(pruebaNueva);
    }

    @GetMapping("/activas")
    public ResponseEntity<List<PruebaDTO>> getAllPruebasActivas(){
        return ResponseEntity.ok(pruebaService.listarPruebasActivas());
    }

    @PutMapping("/finalizar")
    public ResponseEntity<PruebaDTO> finalizarPrueba(@RequestBody RequestPruebaFinalized pruebaAFinalizar){
        return ResponseEntity.ok(pruebaService.finalizarPrueba(pruebaAFinalizar));

    }
}
