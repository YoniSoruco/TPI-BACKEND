package frc.utn.TPI_Backend.Pruebas.models;

import jakarta.persistence.*;
import lombok.Data;
import util.converter.TimestampConverter;

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
