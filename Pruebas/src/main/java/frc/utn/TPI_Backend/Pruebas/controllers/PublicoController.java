package frc.utn.TPI_Backend.Pruebas.controllers;

import frc.utn.TPI_Backend.Pruebas.dtos.PruebaDTO;
import frc.utn.TPI_Backend.Pruebas.dtos.RequestPruebaFinalized;
import frc.utn.TPI_Backend.Pruebas.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/pruebas/publico")
public class PublicoController {

    private PruebaService pruebaService;

    @Autowired
    public PublicoController(PruebaService pruebaService){
        this.pruebaService = pruebaService;
    }

   /* @GetMapping("")
    public ResponseEntity<Iterable<Prueba>> getAllPruebas(){
        return ResponseEntity.ok(pruebaService.getAll());
    }*/

    @GetMapping("/{id}")
    public ResponseEntity<PruebaDTO> getPruebaById(Authentication authentication, @PathVariable int id) {
        PruebaDTO pruebaDTO = pruebaService.getPruebaById(authentication,id);
        return ResponseEntity.ok(pruebaDTO);
    }


    @GetMapping("/activas")
    public ResponseEntity<List<PruebaDTO>> getAllPruebasActivas(Authentication authentication){

        return ResponseEntity.ok(pruebaService.listarPruebasActivas(authentication));
    }

    //deberia esar dentro de protegido empleado ?
    @PutMapping("/finalizar")
    public ResponseEntity<PruebaDTO> finalizarPrueba(Authentication authentication,@RequestBody RequestPruebaFinalized requestPruebaFinalized){
        return ResponseEntity.ok(pruebaService.finalizarPrueba(authentication,requestPruebaFinalized));

    }

    @GetMapping("/vehiculo/{id}")
    public ResponseEntity<List<PruebaDTO>> getAllPruebasXVehiculos(Authentication authentication,@PathVariable int id){
        return ResponseEntity.ok(pruebaService.pruebaXVehiculo(authentication,id));
    }
}
