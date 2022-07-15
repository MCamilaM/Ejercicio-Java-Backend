package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.collections.Equipo;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipoRepository  extends ReactiveCrudRepository<Equipo, Integer> {
}
