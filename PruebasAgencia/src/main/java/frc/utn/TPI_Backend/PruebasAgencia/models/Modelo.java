package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Modelos")
public class Modelo {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_MARCA")
    private Marca marca;

    @Column(name = "DESCRIPCION")
    private String descripcion;


}
