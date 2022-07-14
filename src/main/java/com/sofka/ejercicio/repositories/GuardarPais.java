package com.sofka.ejercicio.repositories;

import com.sofka.ejercicio.models.PaisDTO;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface GuardarPais {
    Mono<PaisDTO> apply (@Valid PaisDTO paisDTO);
}
