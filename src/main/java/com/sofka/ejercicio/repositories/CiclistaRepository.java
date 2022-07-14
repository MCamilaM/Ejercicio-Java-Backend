package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.collections.Ciclista;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CiclistaRepository extends ReactiveCrudRepository<Ciclista, Integer> {
}
