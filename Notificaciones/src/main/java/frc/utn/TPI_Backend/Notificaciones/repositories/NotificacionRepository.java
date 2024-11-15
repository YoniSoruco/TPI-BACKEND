package frc.utn.TPI_Backend.Notificaciones.repositories;

import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion,Integer> {
}
