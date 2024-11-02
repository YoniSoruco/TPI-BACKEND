package frc.utn.TPI_Backend.Vehiculos.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Vehiculos")
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

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Prueba> prueba;

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Posicion> posiciones;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public List<Prueba> getPrueba() {
        return prueba;
    }

    public void setPrueba(List<Prueba> prueba) {
        this.prueba = prueba;
    }

    public List<Posicion> getPosiciones() {
        return posiciones;
    }

    public void setPosiciones(List<Posicion> posiciones) {
        this.posiciones = posiciones;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + id +
                ", patente='" + patente + '\'' +
                ", modelo=" + modelo +
                '}';
    }
}
