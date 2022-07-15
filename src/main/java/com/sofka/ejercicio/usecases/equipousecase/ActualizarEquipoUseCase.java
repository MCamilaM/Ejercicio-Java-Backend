package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import com.sofka.ejercicio.repositories.GuardarEquipo;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class ActualizarEquipoUseCase implements GuardarEquipo {

    private final EquipoRepository equipoRepository;
    private final MapperEquipo mapperEquipo;

    public ActualizarEquipoUseCase(EquipoRepository equipoRepository, MapperEquipo mapperEquipo) {
        this.equipoRepository = equipoRepository;
        this.mapperEquipo = mapperEquipo;
    }

    @Override
    public Mono<EquipoDTO> apply(EquipoDTO equipoDTO) {
        Objects.requireNonNull(equipoDTO.getId(), "El id del equipo es requerido");
        return equipoRepository
                .save(mapperEquipo.EquipoDTOAEquipo()
                        .apply(equipoDTO))
                .thenReturn(equipoDTO);
    }
}
