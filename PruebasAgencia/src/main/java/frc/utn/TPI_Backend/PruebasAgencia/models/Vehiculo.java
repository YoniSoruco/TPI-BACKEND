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


    @OneToMany(mappedBy = "vehiculo")
    private List<Prueba> prueba;
}
