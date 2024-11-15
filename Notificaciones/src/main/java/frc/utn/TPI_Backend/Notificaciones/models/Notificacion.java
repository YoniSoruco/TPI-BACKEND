package frc.utn.TPI_Backend.Notificaciones.models;

import frc.utn.TPI_Backend.Notificaciones.util.converter.TimestampConverter;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Notificaciones")
@Data
public class Notificacion {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Convert(converter = TimestampConverter.class)
    @Column(name = "FECHA_NOTIFICACION" , columnDefinition = "TEXT")
    private LocalDateTime fechaNotificacion;

    @Column(name = "ID_VEHICULO")
    private int idVehiculo;

    @Column(name = "ID_INTERESADO")
    private int idInteresado;

    @Column(name = "LEGAJO_EMPLEADO")
    private int idEmpleado;

    @Column(name = "MENSAJE")
    private String mensaje;



}
