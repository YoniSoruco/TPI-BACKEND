package frc.utn.TPI_Backend.Notificaciones.repositories;

import frc.utn.TPI_Backend.Notificaciones.models.Notificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<Notificacion, Integer> {
}
