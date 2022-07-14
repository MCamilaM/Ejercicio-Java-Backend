package com.sofka.ejercicio.usecases;

import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.GuardarPais;
import com.sofka.ejercicio.repositories.PaisRepository;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarPaisUseCase implements GuardarPais {

    private final PaisRepository paisRepository;
    private final MapperPais mapperPais;

    public ActualizarPaisUseCase(PaisRepository paisRepository, MapperPais mapperPais) {
        this.paisRepository = paisRepository;
        this.mapperPais = mapperPais;
    }

    @Override
    public Mono<PaisDTO> apply(PaisDTO paisDTO) {
        Objects.requireNonNull(paisDTO.getId(), "El id del pais es requerido");
        return paisRepository
                .save(mapperPais.paisDTOAPais()
                        .apply(paisDTO))
                .thenReturn(paisDTO);
    }
}
