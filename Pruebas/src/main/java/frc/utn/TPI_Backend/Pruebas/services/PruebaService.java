package frc.utn.TPI_Backend.Pruebas.services;

import frc.utn.TPI_Backend.Pruebas.dto.PruebaDTO;
import frc.utn.TPI_Backend.Pruebas.dto.VehiculoDTO;
import frc.utn.TPI_Backend.Pruebas.models.Prueba;
import frc.utn.TPI_Backend.Pruebas.repositories.PruebaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import frc.utn.TPI_Backend.Pruebas.exceptions.ResourceNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PruebaService {

    @Autowired
    private PruebaRepository repository;


    @Autowired
    private RestTemplate restTemplate;

    private static final String VEHICULOS_API_URL = "http://localhost:8085/vehiculos"; // Reemplaza con la URL de vehiculos


    public void agregarPrueba(Prueba nueva) {
        repository.save(nueva);
    }


    public Iterable<Prueba> getAll() {
        return repository.findAll();
    }

    public PruebaDTO getPruebaById(int id){
        Prueba prueba = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No se encontro la Prueba con ese id: "+ id));



        VehiculoDTO vehiculoDTO = obtenerVehiculoPorId(id);

        return new PruebaDTO(
                prueba.getId(),
                vehiculoDTO,
                prueba.getInteresado(),
                prueba.getEmpleado(),
                prueba.getFechaHoraInicio(),
                prueba.getFechaHoraFin(),
                prueba.getComentario()
        );
    }

    public VehiculoDTO obtenerVehiculoPorId(int vehiculoId) {
        String url = VEHICULOS_API_URL + "/" + vehiculoId;
        return restTemplate.getForObject(url, VehiculoDTO.class);
    }

/*    public PruebaDTO crearPrueba(PruebaDTO pruebaDTO) {
        // Aquí se almacena la prueba en la base de datos de `pruebas`
        return pruebaDTO;
    }*/

}
