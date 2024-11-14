package frc.utn.TPI_Backend.Pruebas.services;

import frc.utn.TPI_Backend.Pruebas.dto.PruebaDTO;
import frc.utn.TPI_Backend.Pruebas.dto.RequestPruebaCreated;
import frc.utn.TPI_Backend.Pruebas.dto.RequestPruebaFinalized;
import frc.utn.TPI_Backend.Pruebas.dto.VehiculoDTO;
import frc.utn.TPI_Backend.Pruebas.models.Empleado;
import frc.utn.TPI_Backend.Pruebas.models.Interesado;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.repositories.EmpleadoRepository;
import frc.utn.TPI_Backend.Pruebas.repositories.InteresadoRepository;
import frc.utn.TPI_Backend.Pruebas.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import frc.utn.TPI_Backend.Pruebas.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
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

    private static final String VEHICULOS_API_URL = "http://localhost:8085/vehiculos"; // Reemplaza con la URL de vehiculos


    public void agregarPrueba(Prueba nueva) {
        pruebaRepository.save(nueva);
    }


    public Iterable<Prueba> getAll() {
        return pruebaRepository.findAll();
    }

    public PruebaDTO getPruebaById(int id){
        Prueba prueba = pruebaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la Prueba con ese id: "+ id));



        VehiculoDTO vehiculoDTO = obtenerVehiculoPorId(id);

        return new PruebaDTO(

                vehiculoDTO,
                prueba.getInteresado(),
                prueba.getEmpleado(),
                prueba.getFechaHoraInicio().toString(),
                prueba.getFechaHoraFin().toString(),
                prueba.getComentario()
        );
    }

    public VehiculoDTO obtenerVehiculoPorId(int vehiculoId) {
        String url = VEHICULOS_API_URL + "/" + vehiculoId;
        return restTemplate.getForObject(url, VehiculoDTO.class);
    }

    @Transactional
    public Prueba crearPrueba(RequestPruebaCreated pruebaNueva) {

        Interesado interesado = interesadoRepository.findById(pruebaNueva.getIdInteresado())
                .orElseThrow(() -> new ResourceNotFoundException("Interesado no encontrado"));

        if(interesado.getFechaVencLic().isBefore(LocalDateTime.now())){
            throw new IllegalStateException("La licencia del Interesado está vencida");
        }

        if(interesado.isRestringido()){
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
        pruebaRepository.save(prueba);

        //deberia retornar el dto ?  - como obtengo el id? - sqlite_seq?
        return prueba;

    }

    public List<PruebaDTO> listarPruebasActivas(){


        return pruebaRepository.findAllActivePruebas()
                .stream().map(this::covertToDTO).collect(Collectors.toList());
    }

    public PruebaDTO covertToDTO(Prueba prueba){
        VehiculoDTO vehiculoDTO = obtenerVehiculoPorId(prueba.getId());

        return new PruebaDTO(

                vehiculoDTO,
                prueba.getInteresado(),
                prueba.getEmpleado(),
                prueba.getFechaHoraInicio().toString(),
                prueba.getFechaHoraFin().toString(),
                prueba.getComentario()
        );
    }

    public PruebaDTO finalizarPrueba(RequestPruebaFinalized pruebaFinalized){
        Prueba pruebaAFinalizar = pruebaRepository.findById(pruebaFinalized.getIdPrueba())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontro la prueba con id: " + pruebaFinalized.getIdPrueba()));

        if (pruebaAFinalizar.getFechaHoraFin() != null) {
            throw new IllegalStateException("La prueba que se desea finalizar ");
        }

        pruebaAFinalizar.setFechaHoraFin(LocalDateTime.now());
        pruebaAFinalizar.setComentario(pruebaAFinalizar.getComentario());

        pruebaRepository.save(pruebaAFinalizar);

        return covertToDTO(pruebaAFinalizar);
    }


}
