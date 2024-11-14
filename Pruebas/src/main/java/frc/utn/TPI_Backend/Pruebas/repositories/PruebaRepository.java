package frc.utn.TPI_Backend.Pruebas.repositories;

import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PruebaRepository extends CrudRepository<Prueba,Integer> {

    @Query("SELECT p FROM Prueba p WHERE p.idVehiculo = :idVehiculo AND p.fechaHoraFin IS NULL")
    Optional<Prueba> findActivePruebaByVehiculoId(@Param("idVehiculo") int idVehiculo);

    @Query("SELECT p FROM Prueba p WHERE p.fechaHoraFin IS NULL")
    List<Prueba> findAllActivePruebas();
}
