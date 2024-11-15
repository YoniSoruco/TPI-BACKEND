package frc.utn.TPI_Backend.Pruebas.models;

import jakarta.persistence.*;
import lombok.Data;
import util.converter.TimestampConverter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Interesados")
@Data
public class Interesado {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDoc;

    @Column(name = "DOCUMENTO")
    private String documento;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "RESTRINGIDO")
    private boolean restringido;

    @Column(name = "NRO_LICENCIA")
    private int nroLicencia;

    @Convert(converter = TimestampConverter.class)
    @Column(name = "FECHA_VENCIMIENTO_LICENCIA", columnDefinition = "TEXT")
    private LocalDateTime fechaVencLic;

}
