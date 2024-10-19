package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;



@Entity
@Table(name = "Pruebas")
public class Prueba {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;
}
