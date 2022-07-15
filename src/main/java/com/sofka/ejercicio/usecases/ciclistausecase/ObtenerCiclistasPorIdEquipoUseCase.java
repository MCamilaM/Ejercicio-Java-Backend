package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Function;

@Service
@Validated
public class ObtenerCiclistasPorIdEquipoUseCase implements Function<String, Flux<CiclistaDTO>> {

    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;

    public ObtenerCiclistasPorIdEquipoUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
    }

    @Override
    public Flux<CiclistaDTO> apply(String id) {
        return ciclistaRepository
                .findCiclistasByIdEquipo(Integer.parseInt(id))
                .map(ciclista -> mapperCiclista.ciclistaACiclistaDTO().apply(ciclista));
    }
}
