package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.repositories.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class EliminarEquipoPorIdUseCase implements Function<String, Mono<Void>> {

    private final EquipoRepository equipoRepository;

    public EliminarEquipoPorIdUseCase(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "El id del equipo es requerido");
        return equipoRepository.deleteById(Integer.parseInt(id));
    }
}
