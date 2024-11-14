package frc.utn.TPI_Backend.Vehiculos.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "FECHA_HORA",columnDefinition = "TIMESTAMP" )
    private LocalDateTime fechaHora;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private double longitud;


}
