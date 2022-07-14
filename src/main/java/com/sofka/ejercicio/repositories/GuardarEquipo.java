package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.models.EquipoDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface GuardarEquipo {
    Mono<EquipoDTO> apply(@Valid EquipoDTO equipoDTO);
}
