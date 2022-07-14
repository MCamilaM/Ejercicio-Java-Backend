package com.sofka.ejercicio.usecases;

import com.sofka.ejercicio.repositories.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarPaisPorIdUseCase implements Function<String, Mono<Void>> {

    private final PaisRepository paisRepository;

    public EliminarPaisPorIdUseCase(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id del proyecto es requerido");
        return paisRepository.deleteById(Integer.parseInt(id));
    }
}
