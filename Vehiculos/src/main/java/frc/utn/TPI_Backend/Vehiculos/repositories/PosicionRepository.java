package frc.utn.TPI_Backend.Vehiculos.repositories;

import frc.utn.TPI_Backend.Vehiculos.models.Posicion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PosicionRepository extends CrudRepository<Posicion,Integer> {

    @Query(value = "SELECT * FROM Posiciones p WHERE p.ID_VEHICULO = :idVehiculo ORDER BY p.FECHA_HORA DESC LIMIT 1", nativeQuery = true)
    Posicion findUltimaPosicionVehiculo(@Param("idVehiculo") int idVehiculo);


}
