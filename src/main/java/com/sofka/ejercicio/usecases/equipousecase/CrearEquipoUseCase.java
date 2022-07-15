package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import com.sofka.ejercicio.repositories.GuardarEquipo;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static com.sofka.ejercicio.collections.Equipo.ID_SEQUENCE_EQUIPO;

@Service
@Validated
public class CrearEquipoUseCase implements GuardarEquipo {

    private final EquipoRepository equipoRepository;
    private final MapperEquipo mapperEquipo;
    private final SequenceGeneratorService service;

    public CrearEquipoUseCase(EquipoRepository equipoRepository, MapperEquipo mapperEquipo, SequenceGeneratorService service) {
        this.equipoRepository = equipoRepository;
        this.mapperEquipo = mapperEquipo;
        this.service = service;
    }


    @Override
    public Mono<EquipoDTO> apply(EquipoDTO equipoDTO) {
        return service.getSequenceNumber(ID_SEQUENCE_EQUIPO).flatMap(id ->{
            equipoDTO.setId(id.intValue());
            return equipoRepository
                    .save(mapperEquipo.EquipoDTOAEquipo().apply(equipoDTO))
                    .thenReturn(equipoDTO);
        });
    }
}
