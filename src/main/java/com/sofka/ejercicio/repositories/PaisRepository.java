package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.collections.Pais;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends ReactiveCrudRepository<Pais, Integer> {
}
