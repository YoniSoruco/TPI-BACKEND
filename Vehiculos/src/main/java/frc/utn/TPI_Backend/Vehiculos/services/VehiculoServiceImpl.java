package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.dto.PosicionDTO;
import frc.utn.TPI_Backend.Vehiculos.dto.VehiculoDTO;

import frc.utn.TPI_Backend.Vehiculos.models.Posicion;
import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.repositories.PosicionRepository;
import frc.utn.TPI_Backend.Vehiculos.repositories.VehiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehiculoServiceImpl {

    @Autowired
    private final VehiculoRepository vehiculoRepository;

    @Autowired
    private final PosicionRepository posicionRepository;

    public VehiculoServiceImpl(VehiculoRepository vehiculoRepository, PosicionRepository posicionRepository){
        this.vehiculoRepository = vehiculoRepository;
        this.posicionRepository = posicionRepository;
    }

    public void agregarVehiculo(Vehiculo nueva){
        this.vehiculoRepository.save(nueva);
    }


    public Iterable<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    public VehiculoDTO obtenerVehiculoDTO(int id) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));

        Posicion ultimaPosicion = this.posicionRepository.findUltimaPosicionVehiculo(vehiculo.getId());

        PosicionDTO ultimaPosicionDTO = new PosicionDTO(
                ultimaPosicion.getFechaHora(),
                ultimaPosicion.getLatitud(),
                ultimaPosicion.getLongitud()
                );

        return new VehiculoDTO(
                vehiculo.getId(),
                vehiculo.getPatente(),
                vehiculo.getModelo().getDescripcion(),
                vehiculo.getModelo().getMarca().getNombre(),
                ultimaPosicionDTO

        );
    }



}
