package frc.utn.TPI_Backend.Vehiculos.repositories;

import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends CrudRepository<Vehiculo,Integer> {

}
