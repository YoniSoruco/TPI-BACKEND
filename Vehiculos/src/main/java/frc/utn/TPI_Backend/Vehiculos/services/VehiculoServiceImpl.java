package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.dtos.Coordenadas;
import frc.utn.TPI_Backend.Vehiculos.dtos.PosicionDTO;
import frc.utn.TPI_Backend.Vehiculos.dtos.VehiculoDTO;

import frc.utn.TPI_Backend.Vehiculos.models.Posicion;
import frc.utn.TPI_Backend.Vehiculos.models.Vehiculo;
import frc.utn.TPI_Backend.Vehiculos.repositories.PosicionRepository;
import frc.utn.TPI_Backend.Vehiculos.repositories.VehiculoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public Iterable<Vehiculo> getAll() {
        return vehiculoRepository.findAll();
    }

    public VehiculoDTO obtenerVehiculoDTO(int id) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado con ID: " + id));

        Posicion ultimaPosicion = this.posicionRepository.findUltimaPosicionVehiculo(vehiculo.getId());

        PosicionDTO ultimaPosicionDTO = converPosicionToDTO(ultimaPosicion);

        VehiculoDTO vehiculoDTO = convertVehiculoToDTO(vehiculo);
        vehiculoDTO.setUltimaPos(ultimaPosicionDTO);

        System.out.println(vehiculo);

        return vehiculoDTO;
    }

    public PosicionDTO converPosicionToDTO(Posicion posicion){
        Coordenadas coordenadas = new Coordenadas(posicion.getLatitud(),
                posicion.getLongitud());
        return new PosicionDTO(
                posicion.getFechaHora(),
                coordenadas
        );
    }

    public VehiculoDTO convertVehiculoToDTO(Vehiculo vehiculo){
        VehiculoDTO vehiculoDTO =  new VehiculoDTO();
        vehiculoDTO.setId(vehiculo.getId());
        vehiculoDTO.setPatente(vehiculo.getPatente());
        vehiculoDTO.setModelo(vehiculo.getModelo().getDescripcion());
        vehiculoDTO.setMarca(vehiculo.getModelo().getMarca().getNombre());
        return vehiculoDTO;
    }

    public VehiculoDTO obtenerVehiculoConPos(int idVehiculo, LocalDateTime fechaInicio,LocalDateTime fechaFin) {
        Vehiculo vehiculo = this.vehiculoRepository.findById(idVehiculo).orElseThrow(()-> new IllegalArgumentException("no se encontro vehiculo"));

        VehiculoDTO vehiculoDTO = convertVehiculoToDTO(vehiculo);

        List<Posicion> posicions = this.posicionRepository.findTodasPosiciones(fechaInicio,fechaFin,idVehiculo);
        posicions.forEach(System.out::println);
        List<PosicionDTO> posicionDTOS = posicions.stream().map(this::converPosicionToDTO).toList();
        if (!posicionDTOS.isEmpty())
            vehiculoDTO.setUltimaPos(posicionDTOS.getLast());

        vehiculoDTO.setPosicionDTOS(posicionDTOS);

        return vehiculoDTO;
    }
}
