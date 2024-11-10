package frc.utn.TPI_Backend.PruebasAgencia.controllers;

import frc.utn.TPI_Backend.PruebasAgencia.models.Interesado;
import frc.utn.TPI_Backend.PruebasAgencia.services.InteresadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

//@Slf4j
@RestController
@RequestMapping("/api/clientes")
public class InteresadoController {

    private InteresadoService service;

    @Autowired
    public InteresadoController(InteresadoService service){
        this.service=service;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Interesado>> getAllPruebas(){
        return ResponseEntity.ok(service.getAll());
    }

    // Endpoint para crear un nuevo interesado
    @PostMapping("")
    public ResponseEntity<String> crearInteresado(@RequestBody Interesado nuevoInteresado) {
        try {
            Interesado creado = service.crearInteresado(nuevoInteresado);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Interesado creado exitosamente con ID: " + creado.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear el interesado: " + e.getMessage());
        }
    }

}

