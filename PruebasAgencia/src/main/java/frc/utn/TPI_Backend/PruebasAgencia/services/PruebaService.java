package frc.utn.TPI_Backend.PruebasAgencia.services;

import frc.utn.TPI_Backend.PruebasAgencia.models.Prueba;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository repository;

    public void agregarPrueba(Prueba nueva){
        repository.save(nueva);
    }


    public Iterable<Prueba> getAll() {
        return repository.findAll();
    }
}
