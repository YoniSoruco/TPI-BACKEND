package frc.utn.TPI_Backend.Pruebas.models;

import jakarta.persistence.*;

@Entity
@Table(name = "Interesados")
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

    @Column(name = "FECHA_VENCIMIENTO_LICENCIA")
    private String fechaVencLic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isRestringido() {
        return restringido;
    }

    public void setRestringido(boolean restringido) {
        this.restringido = restringido;
    }

    public int getNroLicencia() {
        return nroLicencia;
    }

    public void setNroLicencia(int nroLicencia) {
        this.nroLicencia = nroLicencia;
    }

    public String getFechaVencLic() {
        return fechaVencLic;
    }

    public void setFechaVencLic(String fechaVencLic) {
        this.fechaVencLic = fechaVencLic;
    }

    @Override
    public String toString() {
        return "Interesado{" +
                "tipoDoc='" + tipoDoc + '\'' +
                ", documento='" + documento + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", restringido=" + restringido +
                ", nroLicencia=" + nroLicencia +
                ", fechaVencLic='" + fechaVencLic + '\'' +
                '}';
    }
}
