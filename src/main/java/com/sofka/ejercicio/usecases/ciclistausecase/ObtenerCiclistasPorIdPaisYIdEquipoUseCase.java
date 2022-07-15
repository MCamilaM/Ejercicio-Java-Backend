package com.sofka.ejercicio.usecases.ciclistausecase;


import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.BiFunction;

@Service
@Validated
public class ObtenerCiclistasPorIdPaisYIdEquipoUseCase implements BiFunction<String, String, Flux<CiclistaDTO>> {

    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;

    public ObtenerCiclistasPorIdPaisYIdEquipoUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
    }

    @Override
    public Flux<CiclistaDTO> apply(String idPais, String idEquipo) {
        Integer idPais1 = Integer.parseInt(idPais);
        Integer idEquipo1 = Integer.parseInt(idEquipo);
        return ciclistaRepository
                .findCiclistasByIdPaisAndIdEquipo(idPais1, idEquipo1)
                .map(ciclista -> mapperCiclista.ciclistaACiclistaDTO().apply(ciclista));
    }
}
