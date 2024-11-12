package frc.utn.TPI_Backend.Vehiculos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import frc.utn.TPI_Backend.Vehiculos.dto.PruebaDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Vehiculos")
@Data
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


    private List<PruebaDTO> prueba;

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Posicion> posiciones;


}
