package frc.utn.TPI_Backend.PruebasAgencia.services;

import frc.utn.TPI_Backend.PruebasAgencia.models.Interesado;
import frc.utn.TPI_Backend.PruebasAgencia.repositories.InteresadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InteresadoService {

    @Autowired
    private InteresadoRepository repository;

    public Iterable<Interesado> getAll() {
        return repository.findAll();
    }

    // Método para crear un nuevo interesado
    public Interesado crearInteresado(Interesado interesado) {
        return repository.save(interesado); // Guardar el interesado en la base de datos
    }
}
