package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class VehiculoService {


    @Autowired
    private VehiculoRepository repository;

    public void agregarPrueba(Vehiculo nueva){
        repository.save(nueva);
    }


    public Iterable<Vehiculo> getAll() {
        return repository.findAll();
    }
}
