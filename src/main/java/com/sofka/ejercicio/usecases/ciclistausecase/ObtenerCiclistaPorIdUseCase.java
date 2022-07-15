package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ObtenerCiclistaPorIdUseCase implements Function<String, Mono<CiclistaDTO>> {


    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;

    public ObtenerCiclistaPorIdUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
    }

    @Override
    public Mono<CiclistaDTO> apply(String id) {
        return ciclistaRepository
                .findById(Integer.parseInt(id))
                .map(ciclista -> mapperCiclista.ciclistaACiclistaDTO().apply(ciclista));
    }
}
