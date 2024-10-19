package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Posiciones")
public class Posicion {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @Column(name = "FECHA_HORA")
    private String fechaHora;

    @Column(name = "LATITUD")
    private double latitud;

    @Column(name = "LONGITUD")
    private int longitud;
}
