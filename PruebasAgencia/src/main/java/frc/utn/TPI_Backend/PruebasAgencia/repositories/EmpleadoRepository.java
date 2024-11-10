package frc.utn.TPI_Backend.PruebasAgencia.repositories;

import frc.utn.TPI_Backend.PruebasAgencia.models.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {

}
