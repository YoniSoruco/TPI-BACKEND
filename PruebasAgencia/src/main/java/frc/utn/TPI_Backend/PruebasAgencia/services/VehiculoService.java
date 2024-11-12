package frc.utn.TPI_Backend.PruebasAgencia.services;

import frc.utn.TPI_Backend.PruebasAgencia.models.Vehiculo;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public Vehiculo getVehiculoById(int id) {
        Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
        return vehiculo.orElse(null); // Si no se encuentra, devuelve null
    }
}
