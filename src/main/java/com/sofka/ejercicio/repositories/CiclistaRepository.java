package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.collections.Ciclista;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CiclistaRepository extends ReactiveCrudRepository<Ciclista, Integer> {
    Mono<Ciclista> findCiclistaByNumeroCompetidor(Integer numeroCompetidor);

    Flux<Ciclista> findCiclistasByIdEquipo(Integer idEquipo);

    Flux<Ciclista> findCiclistasByIdPais(Integer idPais);

    Flux<Ciclista> findCiclistasByIdPaisAndIdEquipo(Integer idPais, Integer idEquipo);

    Mono<Long> countByIdEquipo(Integer idEquipo);
}
