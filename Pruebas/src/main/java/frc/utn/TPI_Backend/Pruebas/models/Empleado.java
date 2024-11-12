package frc.utn.TPI_Backend.Pruebas.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Empleados")
@Data
public class Empleado {
    @Id
    @Column(name = "LEGAJO")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int legajo;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "TELEFONO_CONTACTO")
    private int telefono;


}
