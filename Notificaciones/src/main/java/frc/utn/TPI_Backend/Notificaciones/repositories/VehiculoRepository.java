package frc.utn.TPI_Backend.Notificaciones.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import frc.utn.TPI_Backend.Notificaciones.models.Vehiculo;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {
    // Aquí puedes definir métodos personalizados si lo necesitas
}

