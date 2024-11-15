package frc.utn.TPI_Backend.Vehiculos.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import frc.utn.TPI_Backend.Vehiculos.util.converter.TimestampConverter;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Table(name = "Posiciones")
@Data
public class Posicion {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO")
    @JsonManagedReference
    private Vehiculo vehiculo;

    @Convert(converter = TimestampConverter.class)
    @Column(name = "FECHA_HORA",columnDefinition = "TEXT")
    private LocalDateTime fechaHora;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private double longitud;


}
