package frc.utn.TPI_Backend.Vehiculos.services;

import frc.utn.TPI_Backend.Vehiculos.models.Marca;
import frc.utn.TPI_Backend.Vehiculos.services.interfaces.MarcaService;

import java.util.List;

public class MarcaServiceImpl extends ServiceImpl<Marca,Integer> implements MarcaService {
    @Override
    public void create(Marca entity) {

    }

    @Override
    public void update(Marca entity) {

    }

    @Override
    public Marca delete(Integer id) {
        return null;
    }

    @Override
    public Marca findById(Integer id) {
        return null;
    }

    @Override
    public List<Marca> findAll() {
        return List.of();
    }

    @Override
    public boolean exists(Integer id) {
        return false;
    }
}
