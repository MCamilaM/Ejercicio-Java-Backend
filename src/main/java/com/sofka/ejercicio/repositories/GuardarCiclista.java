package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.models.CiclistaDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface GuardarCiclista {
    Mono<CiclistaDTO> apply(@Valid CiclistaDTO ciclistaDTO);
}
