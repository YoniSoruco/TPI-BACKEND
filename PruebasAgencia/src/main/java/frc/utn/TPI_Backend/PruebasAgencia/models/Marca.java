package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Marcas")
public class Marca {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;
}
