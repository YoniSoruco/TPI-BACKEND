package frc.utn.TPI_Backend.Notificaciones.services;

import frc.utn.TPI_Backend.Notificaciones.models.Vehiculo;
import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import frc.utn.TPI_Backend.Notificaciones.models.NotificacionDTO;
import frc.utn.TPI_Backend.Notificaciones.repositories.NotificacionRepository;
import frc.utn.TPI_Backend.Notificaciones.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;


@Service
public class NotificacionService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    private static final String VEHICULOS_API_URL = "http://localhost:8083/api/vehiculos/"; // URL del microservicio de PruebasAgencia

    public Vehiculo getVehiculoById(int id) {
        // Hacemos la llamada al microservicio de Vehículos
        return restTemplate.getForObject(VEHICULOS_API_URL + id, Vehiculo.class);
    }

    @Autowired
    private NotificacionRepository notificacionRepository;

    // Método para obtener todas las notificaciones
    public List<Notificacion> obtenerNotificaciones() {
        return notificacionRepository.findAll();
    }

    // Método para obtener una notificación por id
    public Notificacion obtenerNotificacionPorId(int id) {
        return notificacionRepository.findById(id).orElse(null);

    }

    @Transactional
    public Notificacion saveNotificacion(NotificacionDTO notificacionDTO) {
        Notificacion notificacion = new Notificacion();
        Vehiculo vehiculo = vehiculoRepository.findById(notificacionDTO.getVehiculoId())
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado"));
        notificacion.setVehiculo(vehiculo);
        notificacion.setMensaje(notificacionDTO.getMensaje());
        notificacion.setFechaHora(notificacionDTO.getFechaHora() == null ? LocalDateTime.now() : notificacionDTO.getFechaHora());
        notificacion.setTipo(notificacionDTO.getTipo());

        return notificacionRepository.save(notificacion);
    }

}