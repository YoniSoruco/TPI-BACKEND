package frc.utn.TPI_Backend.Vehiculos.services.interfaces;

import java.util.List;

public interface Service<T,K> {
    void create(T entity);
    void update(T entity);
    T delete(K id);
    T findById(K id);
    List<T> findAll();
    boolean exists(K id);
}
