package frc.utn.TPI_Backend.PruebasAgencia.controllers;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.services.PruebaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/")
    public ResponseEntity<Iterable<Prueba>> getAllPruebas(){
        return ResponseEntity.ok(service.getAll());
    }

}
