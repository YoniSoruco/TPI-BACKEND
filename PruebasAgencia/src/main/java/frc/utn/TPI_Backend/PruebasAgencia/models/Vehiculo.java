package frc.utn.TPI_Backend.PruebasAgencia.models;

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
    private List<Prueba> prueba;

    @OneToMany(mappedBy = "vehiculo",fetch = FetchType.LAZY)
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

}
