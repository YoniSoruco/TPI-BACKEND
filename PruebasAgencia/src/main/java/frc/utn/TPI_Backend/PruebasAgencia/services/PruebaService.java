package frc.utn.TPI_Backend.PruebasAgencia.services;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.models.Interesado;
import frc.utn.TPI_Backend.PruebasAgencia.models.Vehiculo;
import frc.utn.TPI_Backend.PruebasAgencia.models.Empleado;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.PruebaRepository;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.InteresadoRepository;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.VehiculoRepository;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;



@Service
public class PruebaService {

    @Autowired
    private PruebaRepository pruebaRepository;

    @Autowired
    private InteresadoRepository interesadoRepository;

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository; // Asegúrate de tener este repositorio

    public boolean crearPrueba(int idInteresado, int idVehiculo, int idEmpleado) {
        // Obtener el interesado por su ID
        Interesado interesado = interesadoRepository.findById(idInteresado).orElse(null);
        if (interesado == null) {
            throw new IllegalArgumentException("Interesado no encontrado");
        }

        // Validar licencia vigente
        LocalDate fechaVencimiento = LocalDate.parse(interesado.getFechaVencimientoLicencia(), DateTimeFormatter.ISO_LOCAL_DATE);
        if (fechaVencimiento.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("El cliente tiene la licencia vencida.");
        }

        // Validar que no esté restringido
        if (interesado.getRestringido() == 1) {
            throw new IllegalArgumentException("El cliente está restringido para probar vehículos.");
        }

        // Obtener el vehículo por su ID
        Vehiculo vehiculo = vehiculoRepository.findById(idVehiculo).orElse(null);
        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehículo no encontrado");
        }

        // Obtener el empleado por su ID
        Empleado empleado = empleadoRepository.findById(idEmpleado).orElse(null);
        if (empleado == null) {
            throw new IllegalArgumentException("Empleado no encontrado");
        }

        // Crear la nueva prueba con los parámetros
        Prueba nuevaPrueba = new Prueba();
        nuevaPrueba.setInteresado(interesado);
        nuevaPrueba.setVehiculo(vehiculo);
        nuevaPrueba.setEmpleado(empleado); // Asignamos el empleado a la prueba

        // Asignamos la fecha y hora actual al campo 'fechaHoraInicio'
        nuevaPrueba.setFechaHoraInicio(LocalDateTime.now().toString());

        // Asignar "ACTIVA" a FECHA_HORA_FIN
        nuevaPrueba.setFechaHoraFin("ACTIVA");

        // Guardar la nueva prueba en el repositorio
        pruebaRepository.save(nuevaPrueba);

        return true;
    }

    public List<Prueba> obtenerPruebasActivas() {
        return pruebaRepository.findByFechaHoraFin("ACTIVA");
    }

    public boolean finalizarPrueba(int idPrueba, String comentario) {
        Optional<Prueba> pruebaOpt = pruebaRepository.findById(idPrueba);
        if (pruebaOpt.isPresent()) {
            Prueba prueba = pruebaOpt.get();
            prueba.setFechaHoraFin(LocalDateTime.now().toString()); // Fecha actual
            prueba.setComentario(comentario);
            pruebaRepository.save(prueba); // Guardamos los cambios
            return true;
        } else {
            return false; // No se encontró la prueba con el ID proporcionado
        }
    }

    public Iterable<Prueba> getAll() {
        return pruebaRepository.findAll();
    }

    public Prueba obtenerPruebaPorId(int id) {
        return pruebaRepository.findById(id).orElse(null);  // Llamada a findById de la instancia del repositorio
    }
}