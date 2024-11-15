package frc.utn.TPI_Backend.Pruebas.repositories;

import frc.utn.TPI_Backend.Pruebas.models.Empleado;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends CrudRepository<Empleado,Integer> {
}
