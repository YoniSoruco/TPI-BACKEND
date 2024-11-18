package frc.utn.TPI_Backend.Pruebas.controllers;

import frc.utn.TPI_Backend.Pruebas.dtos.RequestPruebaCreated;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.services.PruebaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/protegido-empleados")
public class ProtegidoEmpleadoController {
    private PruebaService pruebaService;

    @Autowired
    public ProtegidoEmpleadoController(PruebaService pruebaService){
        this.pruebaService = pruebaService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prueba crearPrueba( @RequestBody RequestPruebaCreated pruebaNueva){
        return pruebaService.crearPrueba(pruebaNueva);
    }


}
