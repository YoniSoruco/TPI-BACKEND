package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Vehiculos")
public class Vehiculo {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "PATENTE")
    private String patente;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MODELO",referencedColumnName = "ID")
    private Modelo modelo;

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
    private List<Prueba> prueba;

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
    private List<Posicion> posiciones;
}
