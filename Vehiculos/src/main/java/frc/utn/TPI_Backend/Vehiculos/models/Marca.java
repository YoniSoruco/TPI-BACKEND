package frc.utn.TPI_Backend.Vehiculos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Marcas")
public class Marca {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Marca{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
