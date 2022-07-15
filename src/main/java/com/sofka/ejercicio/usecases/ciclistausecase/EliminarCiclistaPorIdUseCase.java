package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarCiclistaPorIdUseCase implements Function<String, Mono<Void>> {

    private final CiclistaRepository ciclistaRepository;

    public EliminarCiclistaPorIdUseCase(CiclistaRepository ciclistaRepository) {
        this.ciclistaRepository = ciclistaRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id del ciclista es requerido");
        return ciclistaRepository.deleteById(Integer.parseInt(id));
    }
}
