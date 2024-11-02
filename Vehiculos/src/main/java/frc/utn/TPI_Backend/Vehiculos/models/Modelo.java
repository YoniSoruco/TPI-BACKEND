package frc.utn.TPI_Backend.Vehiculos.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Modelos")
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "marca=" + marca +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
