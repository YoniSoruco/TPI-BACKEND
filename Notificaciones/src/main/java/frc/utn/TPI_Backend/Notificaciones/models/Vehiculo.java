package frc.utn.TPI_Backend.Notificaciones.models;

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

    @Column(name = "ID_MODELO")
    private int modelo;


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

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public Vehiculo(int id) {
        this.id = id;
    }

    public Vehiculo() {}
}
