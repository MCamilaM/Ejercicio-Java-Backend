package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@Validated
public class ObtenerEquipoPorIdUseCase implements Function<String, Mono<EquipoDTO>> {

    private final EquipoRepository equipoRepository;
    private final MapperEquipo mapperEquipo;

    public ObtenerEquipoPorIdUseCase(EquipoRepository equipoRepository, MapperEquipo mapperEquipo) {
        this.equipoRepository = equipoRepository;
        this.mapperEquipo = mapperEquipo;
    }

    @Override
    public Mono<EquipoDTO> apply(String id) {
        return equipoRepository
                .findById(Integer.parseInt(id))
                .map(equipo -> mapperEquipo.equipoAEquipoDTO().apply(equipo));
    }
}
