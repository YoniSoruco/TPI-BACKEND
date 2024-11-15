package frc.utn.TPI_Backend.Notificaciones.services;

import frc.utn.TPI_Backend.Notificaciones.dtos.*;
import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import frc.utn.TPI_Backend.Notificaciones.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificacionService {


    private NotificacionRepository notificacionRepository;

    private static final String URL_API_ZONAS_RESTRINGIDAS = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";
    private static final String PRUEBAS_API_URL = "http://localhost:8083/api/pruebas";

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WhatsappService whatsappService;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public AgenciaDTO obtenerDatosAgencia() {
        AgenciaDTO response = restTemplate.getForObject(URL_API_ZONAS_RESTRINGIDAS, AgenciaDTO.class);
        return response;
    }

    public List<PruebaDTO> obtenerPruebasActivas() {
        String url = PRUEBAS_API_URL + "/activas";
        ParameterizedTypeReference<List<PruebaDTO>> type = new ParameterizedTypeReference<List<PruebaDTO>>() {};
        ResponseEntity<List<PruebaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, null, type);
        return response.getBody();
    }

    public List<NotificacionDTO> notificar() {
        AgenciaDTO agencia = obtenerDatosAgencia();
        List<PruebaDTO> activas = obtenerPruebasActivas();
        activas.forEach(System.out::println);
        List<NotificacionDTO> notificacionDTOS = activas.stream()
                .filter(prueba ->
                        estaEnZonaRestringida(agencia.getZonasRestringidas(), prueba.getVehiculoDTO())
                                || estaFueraDelRadio(agencia, prueba.getVehiculoDTO()))
                .map(prueba -> {
                    NotificacionDTO notificacionDTO = new NotificacionDTO();
                    notificacionDTO.setVehiculoDTO(prueba.getVehiculoDTO());
                    notificacionDTO.setInteresadoDTO(prueba.getInteresado());
                    notificacionDTO.setEmpleadoDTO(prueba.getEmpleado());
                    notificacionDTO.setFechaNotificacion(LocalDateTime.now());

                    return notificacionDTO;
                }).toList();

        List<Notificacion> notificaciones = notificacionDTOS.stream().map(this::converToEntity).toList();
        this.notificacionRepository.saveAll(notificaciones);

//        notificacionDTOS.forEach(notificacionDTO ->
//                whatsappService.enviarNotificacionWhatsApp(
//                        notificacionDTO.getEmpleadoDTO().getTelefono(),
//                        obtenerMensaje(notificacionDTO)
//                )
//        );


        return notificacionDTOS;

    }

    public Notificacion converToEntity(NotificacionDTO notificacionDTO) {
        Notificacion notificacion = new Notificacion();
        notificacion.setIdEmpleado(notificacionDTO.getEmpleadoDTO().getLegajo());
        notificacion.setIdVehiculo(notificacionDTO.getVehiculoDTO().getId());
        notificacion.setIdInteresado(notificacionDTO.getInteresadoDTO().getId());
        notificacion.setFechaNotificacion(notificacionDTO.getFechaNotificacion());
        notificacion.setMensaje(obtenerMensaje(notificacionDTO));
        return notificacion;
    }

    public String obtenerMensaje(NotificacionDTO notificacionDTO) {
        return ("Se detecto que a las " + notificacionDTO.getFechaNotificacion() +
                "\nEl Interesado: " +
                notificacionDTO.getInteresadoDTO().getNombre() + "," +
                notificacionDTO.getInteresadoDTO().getApellido() +
                "Esta infrigiendo las reglas de la prueba con el vehiculo: " +
                notificacionDTO.getVehiculoDTO()
        );


    }

    public boolean estaEnZonaRestringida(List<ZonaRestringida> zonaRestringidas, VehiculoDTO vehiculo) {
        return zonaRestringidas.stream().anyMatch(zona -> {
            double latitudVehiculo = vehiculo.getUltimaPos().getLatitud();
            double longitudVehiculo = vehiculo.getUltimaPos().getLongitud();

//            System.out.println("LATIDUD");
//            System.out.println(vehiculo.getUltimaPos().getLatitud());
//            System.out.println(zona.getNoroeste().getLat());
//            System.out.println(zona.getSureste().getLat());
//
//            System.out.println("LONGITUD");
//            System.out.println(vehiculo.getUltimaPos().getLongitud());
//            System.out.println(zona.getNoroeste().getLon());
//            System.out.println(zona.getSureste().getLon());

            return latitudVehiculo <= zona.getNoroeste().getLat() &&
                    latitudVehiculo >= zona.getSureste().getLat() &&
                    longitudVehiculo >= zona.getNoroeste().getLon() &&
                    longitudVehiculo <= zona.getSureste().getLon();
        });
    }

    public boolean estaFueraDelRadio(AgenciaDTO agencia, VehiculoDTO vehiculo) {
        double distance = calcularDistancia(agencia.getCoordenadasAgencia(), vehiculo.getUltimaPos());
        System.out.println(vehiculo);
        System.out.println(distance);
        return distance > agencia.getRadioAdmitidoKm();
    }


    public double calcularDistancia(Coordenadas coordenadasAgencia, PosicionDTO posicionVehiculo) {
        double EARTH_RADIUS_KM = 6371.0;
        double lat1 = coordenadasAgencia.getLat();
        double lon1 = coordenadasAgencia.getLon();
        double lat2 = posicionVehiculo.getLatitud();
        double lon2 = posicionVehiculo.getLongitud();

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(latDistance / 2) , 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(lonDistance / 2),2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }
}
