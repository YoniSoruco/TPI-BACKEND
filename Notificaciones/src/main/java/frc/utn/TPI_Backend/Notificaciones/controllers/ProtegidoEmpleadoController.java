package frc.utn.TPI_Backend.Notificaciones.controllers;

import frc.utn.TPI_Backend.Notificaciones.dtos.AgenciaDTO;
import frc.utn.TPI_Backend.Notificaciones.dtos.NotificacionDTO;
import frc.utn.TPI_Backend.Notificaciones.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones/protegido-empleados")
public class ProtegidoEmpleadoController {
    private NotificacionService notificacionService;

    @Autowired
    public ProtegidoEmpleadoController(NotificacionService notificacionService){
        this.notificacionService = notificacionService;
    }

    //este endpoint de prueba
    @GetMapping("/agencia")
    private ResponseEntity<AgenciaDTO> obtenerDatos(){
        return ResponseEntity.ok( notificacionService.obtenerDatosAgencia());
    }


    @GetMapping("/notificar")
    private List<NotificacionDTO> notificar(Authentication authentication){
        return notificacionService.notificar(authentication);
    }
}