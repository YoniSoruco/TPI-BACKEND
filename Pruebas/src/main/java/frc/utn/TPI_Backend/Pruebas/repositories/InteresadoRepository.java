package frc.utn.TPI_Backend.Pruebas.repositories;

import frc.utn.TPI_Backend.Pruebas.models.Interesado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InteresadoRepository extends CrudRepository<Interesado,Integer> {
}
