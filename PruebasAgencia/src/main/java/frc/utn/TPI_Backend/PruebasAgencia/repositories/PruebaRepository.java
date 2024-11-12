package frc.utn.TPI_Backend.PruebasAgencia.repositories;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.models.Vehiculo;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface PruebaRepository extends CrudRepository<Prueba,Integer> {
    Prueba findByVehiculoAndFechaHoraFinIsNull(Vehiculo vehiculo);

    List<Prueba> findByFechaHoraFin(String fechaHoraFin);
}
