package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import com.sofka.ejercicio.repositories.GuardarCiclista;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarCiclistaUseCase implements GuardarCiclista {

    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;

    public ActualizarCiclistaUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
    }

    @Override
    public Mono<CiclistaDTO> apply(CiclistaDTO ciclistaDTO) {
        Objects.requireNonNull(ciclistaDTO.getId(), "El id del ciclista es requerido");
        return ciclistaRepository
                .save(mapperCiclista.ciclistaDTOACliciclista()
                        .apply(ciclistaDTO))
                .thenReturn(ciclistaDTO);
    }
}
