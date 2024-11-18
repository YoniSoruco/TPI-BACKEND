package frc.utn.TPI_Backend.Pruebas.services;

import frc.utn.TPI_Backend.Pruebas.dtos.*;
import frc.utn.TPI_Backend.Pruebas.models.Empleado;
import frc.utn.TPI_Backend.Pruebas.models.Interesado;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.repositories.EmpleadoRepository;
import frc.utn.TPI_Backend.Pruebas.repositories.InteresadoRepository;
import frc.utn.TPI_Backend.Pruebas.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import frc.utn.TPI_Backend.Pruebas.exceptions.ResourceNotFoundException;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PruebaService {


    @Autowired
    private PruebaRepository pruebaRepository;

    @Autowired
    private InteresadoRepository interesadoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String VEHICULOS_API_URL_PROTEGIDO = "http://localhost:8085/protegido-vehiculos"; // Reemplaza con la URL de vehiculos


    public Iterable<Prueba> getAll() {
        return pruebaRepository.findAll();
    }

    public PruebaDTO getPruebaById(Authentication authentication, int id) {
        Prueba prueba = pruebaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la Prueba con ese id: " + id));

        return covertToDTO2(authentication, prueba);
    }

    public VehiculoDTO obtenerVehiculoPorId(Authentication authentication, int idVehiculo) {
        String url = VEHICULOS_API_URL_PROTEGIDO + "/" + idVehiculo;

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
        headers.setBearerAuth(token); // Método más seguro para establecer el token Bearer

        // Crear el HttpEntity con los encabezados
        HttpEntity<String> entity = new HttpEntity<>(headers);


        ResponseEntity<VehiculoDTO> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                VehiculoDTO.class
        );
        return response.getBody();

    }

    public VehiculoDTO obtenerVehiculoConPos(Authentication authentication, int idVehiculo, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        String fechaInicioStr = fechaInicio.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); // Por ejemplo: "2024-10-19T21:31:17"
        String fechaFinStr = (fechaFin != null) ? fechaFin.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : null;
        UriComponentsBuilder urlCom = UriComponentsBuilder.fromHttpUrl(VEHICULOS_API_URL_PROTEGIDO + "/posiciones")
                .queryParam("idVehiculo", idVehiculo)
                .queryParam("fechaInicio", fechaInicioStr);

        if (fechaFin != null) {
            urlCom.queryParam("fechaFin", fechaFinStr);
        }
        String url = urlCom.toUriString();

        // Extraer el token JWT correctamente
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


        /*System.out.println("URL: " + url);*/
        ParameterizedTypeReference<VehiculoDTO> type = new ParameterizedTypeReference<VehiculoDTO>() {
        };
        ResponseEntity<VehiculoDTO> response = restTemplate.exchange(url, HttpMethod.GET, entity, type);
        return response.getBody();
    }

    @Transactional
    public Prueba crearPrueba(RequestPruebaCreated pruebaNueva) {

        Interesado interesado = interesadoRepository.findById(pruebaNueva.getIdInteresado())
                .orElseThrow(() -> new ResourceNotFoundException("Interesado no encontrado"));

        if (interesado.getFechaVencLic().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("La licencia del Interesado está vencida");
        }

        if (interesado.isRestringido()) {
            throw new IllegalStateException("El interesado esta Restringido!! ");
        }

        Optional<Prueba> pruebaActiva = pruebaRepository.findActivePruebaByVehiculoId(pruebaNueva.getIdVehiculo());

        if (pruebaActiva.isPresent()) {
            throw new IllegalStateException("La prueba para ese vehiculo, todavia esta en marcha");
        }
        Empleado empleado = empleadoRepository.findById(pruebaNueva.getIdEmpleado())
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado"));

        Prueba prueba = new Prueba();
        prueba.setEmpleado(empleado);
        prueba.setInteresado(interesado);
        prueba.setFechaHoraInicio(LocalDateTime.now());
        prueba.setIdVehiculo(pruebaNueva.getIdVehiculo());

        return pruebaRepository.save(prueba);

    }

    public List<PruebaDTO> listarPruebasActivas(Authentication authentication) {
        try {

            return pruebaRepository.findAllActivePruebas()
                    .stream().map(p -> covertToDTO(authentication, p)).collect(Collectors.toList());
        } catch (Exception e) {

            throw new RuntimeException("Error al obtener pruebas activas", e);
        }

    }


    public InteresadoDTO convertInteresadoToDTO(Prueba prueba) {
        return new InteresadoDTO(
                prueba.getInteresado().getId(),
                prueba.getInteresado().getTipoDoc(),
                prueba.getInteresado().getDocumento(),
                prueba.getInteresado().getNombre(),
                prueba.getInteresado().getApellido()

        );
    }

    public EmpleadoDTO convertEmpleadoToDTO(Prueba prueba) {
        return new EmpleadoDTO(
                prueba.getEmpleado().getLegajo(),
                prueba.getEmpleado().getNombre(),
                prueba.getEmpleado().getApellido(),
                prueba.getEmpleado().getTelefono()
        );
    }

    public PruebaDTO covertToDTO(Authentication authentication, Prueba prueba) {

        VehiculoDTO vehiculoDTO = obtenerVehiculoPorId(authentication, prueba.getIdVehiculo());
        InteresadoDTO interesadoDTO = convertInteresadoToDTO(prueba);
        EmpleadoDTO empleadoDTO = convertEmpleadoToDTO(prueba);

        return new PruebaDTO(

                vehiculoDTO,
                interesadoDTO,
                empleadoDTO,
                prueba.getFechaHoraInicio(),
                prueba.getFechaHoraFin(),
                prueba.getComentario()
        );
    }

    //Se utiliza este segundo para el reporte
    public PruebaDTO covertToDTO2(Authentication authentication, Prueba prueba) {

        VehiculoDTO vehiculoDTO = obtenerVehiculoConPos(authentication, prueba.getIdVehiculo(), prueba.getFechaHoraInicio(), prueba.getFechaHoraFin());
        InteresadoDTO interesadoDTO = convertInteresadoToDTO(prueba);
        EmpleadoDTO empleadoDTO = convertEmpleadoToDTO(prueba);

        return new PruebaDTO(

                vehiculoDTO,
                interesadoDTO,
                empleadoDTO,
                prueba.getFechaHoraInicio(),
                prueba.getFechaHoraFin(),
                prueba.getComentario()
        );
    }

    public PruebaDTO finalizarPrueba(Authentication authentication, RequestPruebaFinalized requestPruebaFinalized) {
        Prueba pruebaAFinalizar = pruebaRepository.findById(requestPruebaFinalized.getIdPrueba())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la prueba con id: " + requestPruebaFinalized.getIdPrueba()));

        if (pruebaAFinalizar.getFechaHoraFin() != null) {
            throw new IllegalStateException("La prueba que se desea finalizar , ya se encuentra finalizada");
        }

        pruebaAFinalizar.setFechaHoraFin(LocalDateTime.now());
        pruebaAFinalizar.setComentario(requestPruebaFinalized.getComentario());

        pruebaRepository.save(pruebaAFinalizar);

        return covertToDTO(authentication, pruebaAFinalizar);
    }

    //esto es solo para el reporte de cant de pruebas
//pruebas.stream().map(this::covertToDTO2).toList();
    public List<PruebaDTO> pruebaXVehiculo(Authentication authentication, int idVehiculo) {
        List<Prueba> pruebas = this.pruebaRepository.findPruebasXVehiculos(idVehiculo);
        pruebas.forEach(System.out::println);
        return pruebas.stream().map(p -> covertToDTO(authentication, p)).toList();

    }


}
