package frc.utn.TPI_Backend.Vehiculos.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Marcas")
@Data
public class Marca {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;


}
