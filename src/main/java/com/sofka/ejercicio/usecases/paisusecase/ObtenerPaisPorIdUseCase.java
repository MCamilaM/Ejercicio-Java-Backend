package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ObtenerPaisPorIdUseCase implements Function<String, Mono<PaisDTO>> {

    private final PaisRepository paisRepository;
    private final MapperPais mapperPais;

    public ObtenerPaisPorIdUseCase(PaisRepository paisRepository, MapperPais mapperPais) {
        this.paisRepository = paisRepository;
        this.mapperPais = mapperPais;
    }

    @Override
    public Mono<PaisDTO> apply(String id) {
        return paisRepository
                .findById(Integer.parseInt(id))
                .map(pais -> mapperPais.paisAPaisDTO().apply(pais));
    }
}
