package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.models.Modelo;
import frc.utn.TPI_Backend.Vehiculos.services.interfaces.ModeloService;

import java.util.List;

public class ModeloServiceImpl extends ServiceImpl<Modelo,Integer> implements ModeloService {
    @Override
    public void create(Modelo entity) {

    }

    @Override
    public void update(Modelo entity) {

    }

    @Override
    public Modelo delete(Integer id) {
        return null;
    }

    @Override
    public Modelo findById(Integer id) {
        return null;
    }

    @Override
    public List<Modelo> findAll() {
        return List.of();
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
