package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Interesados")
public class Interesado {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

}
