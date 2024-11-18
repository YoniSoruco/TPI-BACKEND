package frc.utn.TPI_Backend.Pruebas.dtos;

import lombok.Data;

@Data
public class RequestPruebaFinalized {
    private int idPrueba;
    private String comentario;

    public RequestPruebaFinalized(int idPrueba,String comentario){
        this.idPrueba = idPrueba;
        this.comentario = comentario;
    }

    public RequestPruebaFinalized(int idPrueba){
        this(idPrueba,"");
    }

}
