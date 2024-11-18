package frc.utn.TPI_Backend.Notificaciones.services;

import frc.utn.TPI_Backend.Notificaciones.dtos.*;
import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import frc.utn.TPI_Backend.Notificaciones.repositories.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificacionService {


    private NotificacionRepository notificacionRepository;

    private static final String URL_API_ZONAS_RESTRINGIDAS = "https://labsys.frc.utn.edu.ar/apps-disponibilizadas/backend/api/v1/configuracion/";
    private static final String PRUEBAS_API_URL_PROTEGIDO_EMPLEADO = "http://localhost:8083/api/pruebas/protegido-empleados";
    private static final String PRUEBAS_API_URL_PUBLICO = "http://localhost:8083/api/pruebas/publico";


    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WhatsappService whatsappService;

    @Autowired
    public NotificacionService(NotificacionRepository notificacionRepository) {
        this.notificacionRepository = notificacionRepository;
    }

    public AgenciaDTO obtenerDatosAgencia() {
        return restTemplate.getForObject(URL_API_ZONAS_RESTRINGIDAS, AgenciaDTO.class);
    }

    public List<PruebaDTO> obtenerPruebasActivas(Authentication authentication) {
        try {
            // Construir la URL de la otra API
            String url = PRUEBAS_API_URL_PUBLICO + "/activas";

            // Extraer el token JWT correctamente
            String token = null;
            if (authentication.getCredentials() instanceof Jwt) {
                Jwt jwt = (Jwt) authentication.getCredentials();
                token = jwt.getTokenValue();
            } else {
                throw new RuntimeException("No se pudo obtener el token JWT");
            }

            // Crear el encabezado con el token
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);

            // Crear la entidad HTTP con los encabezados
            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Realizar la solicitud a la otra API
            ParameterizedTypeReference<List<PruebaDTO>> type = new ParameterizedTypeReference<List<PruebaDTO>>() {};
            ResponseEntity<List<PruebaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity, type);

            return response.getBody();

        } catch (HttpServerErrorException.InternalServerError e) {
            // Manejar el error 500
            System.out.println("Error 500 - Internal Server Error: " + e.getResponseBodyAsString());
            throw e;
        } catch (Exception e) {
            // Manejar otros errores
            System.out.println("Error al llamar a la API externa: " + e.getMessage());
            throw e;
        }
    }

    public PruebaDTO obtenerPrueba(Authentication authentication,int idPrueba) {
        String url = PRUEBAS_API_URL_PUBLICO + "/"+idPrueba;

        String token = null;
        if (authentication.getCredentials() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getCredentials();
            token = jwt.getTokenValue();
        } else {
            throw new RuntimeException("No se pudo obtener el token JWT");
        }


        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<PruebaDTO> type = new ParameterizedTypeReference<PruebaDTO>() {};
        ResponseEntity<PruebaDTO> response = restTemplate.exchange(url,HttpMethod.GET,entity,type);
        return response.getBody();
    }
    public List<PruebaDTO> obtenerPruebasXVehiculo(Authentication authentication,int idVehiculo) {
        String url = PRUEBAS_API_URL_PUBLICO + "/vehiculo/"+idVehiculo;

        String token = null;
        if (authentication.getCredentials() instanceof Jwt) {
            Jwt jwt = (Jwt) authentication.getCredentials();
            token = jwt.getTokenValue();
        } else {
            throw new RuntimeException("No se pudo obtener el token JWT");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<PruebaDTO>> type = new ParameterizedTypeReference<List<PruebaDTO>>() {};
        ResponseEntity<List<PruebaDTO>> response = restTemplate.exchange(url, HttpMethod.GET, entity, type);
        return response.getBody();
    }
    public List<NotificacionDTO> notificar(Authentication authentication) {
        AgenciaDTO agencia = obtenerDatosAgencia();
        List<PruebaDTO> activas = obtenerPruebasActivas(authentication);

        List<NotificacionDTO> notificacionDTOS = activas.stream()
                .filter(prueba ->
                        tieneIncidente(agencia,prueba.getVehiculoDTO()))
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

    public boolean tieneIncidente(AgenciaDTO agencia,VehiculoDTO vehiculo){
        return (estaEnZonaRestringida(agencia.getZonasRestringidas(), vehiculo)
                || estaFueraDelRadio(agencia,vehiculo));

    }

    public boolean estaEnZonaRestringida(List<ZonaRestringida> zonaRestringidas, VehiculoDTO vehiculo) {
        return zonaRestringidas.stream().anyMatch(zona -> {
            double latitudVehiculo = vehiculo.getUltimaPos().getCoordenadas().getLat();
            double longitudVehiculo = vehiculo.getUltimaPos().getCoordenadas().getLon();

            return latitudVehiculo <= zona.getNoroeste().getLat() &&
                    latitudVehiculo >= zona.getSureste().getLat() &&
                    longitudVehiculo >= zona.getNoroeste().getLon() &&
                    longitudVehiculo <= zona.getSureste().getLon();
        });
    }

    public boolean estaFueraDelRadio(AgenciaDTO agencia, VehiculoDTO vehiculo) {
        double distance = calcularDistancia(agencia.getCoordenadasAgencia(), vehiculo.getUltimaPos().getCoordenadas());

        return distance > agencia.getRadioAdmitidoKm();
    }


    public double calcularDistancia(Coordenadas coordenadasInicio, Coordenadas coordenadasFinal) {
        double EARTH_RADIUS_KM = 6371.0;
        double lat1 = coordenadasInicio.getLat();
        double lon1 = coordenadasInicio.getLon();
        double lat2 = coordenadasFinal.getLat();
        double lon2 = coordenadasFinal.getLon();

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(latDistance / 2) , 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.pow(Math.sin(lonDistance / 2),2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double distance = EARTH_RADIUS_KM * c;

        return distance;
    }

    public List<PruebaDTO> mostrarReporteIncidencias(Authentication authentication) {
        AgenciaDTO agencia = obtenerDatosAgencia();
        List<PruebaDTO> activas = obtenerPruebasActivas(authentication);

        return activas.stream().filter(prueba ->
                tieneIncidente(agencia,prueba.getVehiculoDTO())).toList();
    }

    public List<PruebaDTO> mostrarReporteIncidenciasXEmpleado(Authentication authentication,int idEmpleado) {
        return mostrarReporteIncidencias(authentication).stream()
                .filter(prueba -> idEmpleado == prueba.getEmpleado().getLegajo())
                .toList();
    }

    public ResponseCantKmEnPrueba mostrarReporteCantKmEnPruebas(Authentication authentication, int idPrueba){
        PruebaDTO pruebaDTO = obtenerPrueba(authentication,idPrueba);
        List<Coordenadas> coordenadas = new ArrayList<>();

        AgenciaDTO agenciaDTO = obtenerDatosAgencia();
        coordenadas.add(agenciaDTO.getCoordenadasAgencia());

        List<PosicionDTO> posicionDTOS = pruebaDTO.getVehiculoDTO().getPosicionDTOS();
        posicionDTOS.forEach(p -> coordenadas.add(p.getCoordenadas()));
        double cantKmEnPrueba = coordenadas.stream().reduce(0.0,
                (acumulador, actual) -> {
                    int index = coordenadas.indexOf(actual);
                    if (index > 0) {
                        Coordenadas anterior = coordenadas.get(index - 1);
                        return acumulador + calcularDistancia(anterior, actual);
                    }
                    return acumulador;
                },
                Double::sum);

        ResponseCantKmEnPrueba responseCantKmEnPrueba = new ResponseCantKmEnPrueba();
        responseCantKmEnPrueba.setVehiculoDTO(pruebaDTO.getVehiculoDTO());
        responseCantKmEnPrueba.setFechaInicio(pruebaDTO.getFechaHoraInicio());
        responseCantKmEnPrueba.setFechaFin(pruebaDTO.getFechaHoraFin());
        responseCantKmEnPrueba.setKilometrosRecorridos(cantKmEnPrueba);

        return responseCantKmEnPrueba;


    }


    public List<PruebaDTO> mostrarReportePruebasRealizadas(Authentication authentication, int id) {
        return obtenerPruebasXVehiculo(authentication,id);

    }


}
