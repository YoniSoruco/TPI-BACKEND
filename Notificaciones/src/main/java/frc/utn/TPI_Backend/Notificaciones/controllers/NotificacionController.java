package frc.utn.TPI_Backend.Notificaciones.controllers;

import frc.utn.TPI_Backend.Notificaciones.services.NotificacionService;
import frc.utn.TPI_Backend.Notificaciones.models.Vehiculo;
import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import frc.utn.TPI_Backend.Notificaciones.models.NotificacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.List;

@RestController
@RequestMapping("api/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService notificacionService;

    // Endpoint para obtener todas las notificaciones
    @GetMapping
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionService.obtenerNotificaciones();
    }

    // Endpoint para obtener una notificación por ID
    @GetMapping("/{id}")
    public Notificacion obtenerNotificacionPorId(@PathVariable int id) {
        return notificacionService.obtenerNotificacionPorId(id);
    }

    @PostMapping
    public ResponseEntity<Notificacion> createNotificacion(@RequestBody NotificacionDTO notificacionDTO) {
        Notificacion notificacion = notificacionService.saveNotificacion(notificacionDTO);
        return new ResponseEntity<>(notificacion, HttpStatus.CREATED);
    }
}
