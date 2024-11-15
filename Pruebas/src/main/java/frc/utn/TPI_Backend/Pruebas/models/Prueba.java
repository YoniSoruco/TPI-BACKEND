package frc.utn.TPI_Backend.Pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import frc.utn.TPI_Backend.Pruebas.dto.VehiculoDTO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;
import util.converter.TimestampConverter;

import java.sql.Timestamp;
import java.time.LocalDateTime;


@Entity
@Table(name = "Pruebas")
@Data
public class Prueba {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name = "ID_VEHICULO")
    private int idVehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;


    @Convert(converter = TimestampConverter.class)
    @Column(name = "FECHA_HORA_INICIO" ,columnDefinition = "TEXT")
    private LocalDateTime fechaHoraInicio;


    @Convert(converter = TimestampConverter.class)
    @Column(name = "FECHA_HORA_FIN", columnDefinition = "TEXT")
    private LocalDateTime fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentario;

}
