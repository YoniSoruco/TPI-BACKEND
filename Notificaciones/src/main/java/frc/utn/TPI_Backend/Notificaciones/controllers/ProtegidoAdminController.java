package frc.utn.TPI_Backend.Notificaciones.controllers;

import frc.utn.TPI_Backend.Notificaciones.dtos.PruebaDTO;
import frc.utn.TPI_Backend.Notificaciones.dtos.ResponseCantKmEnPrueba;
import frc.utn.TPI_Backend.Notificaciones.services.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/protegido-administradores")
public class ProtegidoAdminController {

    private NotificacionService notificacionService;
    @Autowired
    public ProtegidoAdminController(NotificacionService notificacionService){
        this.notificacionService = notificacionService;
    }

    @GetMapping("/reporte/incidencias")
    private List<PruebaDTO> mostrarReporteIncidencias(Authentication authentication){

        return notificacionService.mostrarReporteIncidencias(authentication);
    }

    @GetMapping("/reporte/incidencias/{id}")
    private List<PruebaDTO> mostrarReporteIncidenciasXEmpleado(Authentication authentication,@PathVariable int id){
        return notificacionService.mostrarReporteIncidenciasXEmpleado(authentication,id);
    }

    @GetMapping("/reporte/cant-en-prueba/{id}")
    private ResponseCantKmEnPrueba mostrarReporteCantKmEnPruebas(Authentication authentication,@PathVariable int id){
        return notificacionService.mostrarReporteCantKmEnPruebas(authentication,id);
    }

    @GetMapping("/reporte/pruebas/vehiculo/{id}")
    private List<PruebaDTO> mostrarReportePruebasRealizadas(Authentication authentication,@PathVariable int id){
        return notificacionService.mostrarReportePruebasRealizadas(authentication,id);
    }
}
