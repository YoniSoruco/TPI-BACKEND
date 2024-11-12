package frc.utn.TPI_Backend.Vehiculos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Modelos")
@Data
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
