package frc.utn.TPI_Backend.Vehiculos.repositories;

import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;

public interface VehiculoRepository extends CrudRepository<Vehiculo,Integer> {
}
