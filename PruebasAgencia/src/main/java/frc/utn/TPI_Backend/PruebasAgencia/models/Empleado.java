package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Empleados")
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
