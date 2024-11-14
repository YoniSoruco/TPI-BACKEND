package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.dto.VehiculoDTO;

import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.repositories.VehiculoRepository;
import frc.utn.TPI_Backend.Vehiculos.services.interfaces.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class VehiculoServiceImpl extends ServiceImpl<Vehiculo,Integer> implements VehiculoService {



    @Autowired
    private final VehiculoRepository vehiculoRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository){
        this.vehiculoRepository = vehiculoRepository;
    }

    RestTemplate restTemplate;



    public void agregarPrueba(Vehiculo nueva){
        vehiculoRepository.save(nueva);
    }


    public Iterable<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    public VehiculoDTO obtenerVehiculoDTO(int id) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));

/*
        // Obtener el modelo del vehículo
        Modelo modelo = modeloRepository.findById(vehiculo.getIdModelo())
                .orElseThrow(() -> new RuntimeException("Modelo no encontrado con ID: " + vehiculo.getIdModelo()));

        // Obtener la marca del modelo
        Marca marca = marcaRepository.findById(modelo.getIdMarca())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada con ID: " + modelo.getIdMarca()));
*/
        // Construir y retornar el DTO
        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getPatente(),
                vehiculo.getModelo().getDescripcion(),
                vehiculo.getModelo().getMarca().getNombre()

        );
    }

    @Override
    public void create(Vehiculo entity) {
        this.vehiculoRepository.save(entity);
    }

    @Override
    public void update(Vehiculo entity) {
        this.vehiculoRepository.save(entity);
    }

    @Override
    public Vehiculo delete(Integer id) {
        Vehiculo vehiculo = findById(id);
        this.vehiculoRepository.delete(vehiculo);
        return vehiculo;
    }

    @Override
    public Vehiculo findById(Integer id) {

        return this.vehiculoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Vehiculo> findAll() {
        return (List<Vehiculo>) this.vehiculoRepository.findAll();
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
