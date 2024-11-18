package frc.utn.TPI_Backend.Vehiculos.repositories;

import frc.utn.TPI_Backend.Vehiculos.models.Posicion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


@Repository
public interface PosicionRepository extends CrudRepository<Posicion,Integer> {

    @Query(value = "SELECT * FROM Posiciones p WHERE p.ID_VEHICULO = :idVehiculo ORDER BY p.FECHA_HORA DESC LIMIT 1", nativeQuery = true)
    Posicion findUltimaPosicionVehiculo(@Param("idVehiculo") int idVehiculo);

    @Query(value = "SELECT * FROM Posiciones p WHERE p.ID_VEHICULO = :idVehiculo " +
            "AND p.FECHA_HORA >= :fechaInicio " +
            "AND (:fechaFin IS NULL OR p.FECHA_HORA <= :fechaFin) " +
            "ORDER BY p.FECHA_HORA DESC", nativeQuery = true)
    List<Posicion> findTodasPosiciones(@Param("fechaInicio")LocalDateTime fechaInicio,@Param("fechaFin")LocalDateTime fechaFin,@Param("idVehiculo")int idVehiculo);

}
