package frc.utn.TPI_Backend.PruebasAgencia.models;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Pruebas")
public class Prueba {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ID_VEHICULO")
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "ID_INTERESADO")
    private Interesado interesado;

    @ManyToOne
    @JoinColumn(name = "ID_EMPLEADO")
    private Empleado empleado;

    @Column(name = "FECHA_HORA_INICIO")
    private String fechaHoraInicio;

    @Column(name = "FECHA_HORA_FIN")
    private String fechaHoraFin;

    @Column(name = "COMENTARIOS")
    private String comentario;

}
