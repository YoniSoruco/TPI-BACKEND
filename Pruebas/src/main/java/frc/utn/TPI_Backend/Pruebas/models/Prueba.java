package frc.utn.TPI_Backend.Pruebas.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import frc.utn.TPI_Backend.Pruebas.dto.VehiculoDTO;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Pruebas")
@Data
public class Prueba {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;


    @Column(name = "ID_VEHICULO")
    private int idVehiculo;

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
