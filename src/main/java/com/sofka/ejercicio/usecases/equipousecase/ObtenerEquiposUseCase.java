package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import java.util.function.Supplier;

@Service
@Validated
public class ObtenerEquiposUseCase implements Supplier<Flux<EquipoDTO>> {

    private final EquipoRepository equipoRepository;
    private final MapperEquipo mapperEquipo;


    public ObtenerEquiposUseCase(EquipoRepository equipoRepository, MapperEquipo mapperEquipo) {
        this.equipoRepository = equipoRepository;
        this.mapperEquipo = mapperEquipo;
    }

    @Override
    public Flux<EquipoDTO> get() {
        return equipoRepository.findAll()
                .map(mapperEquipo.equipoAEquipoDTO());
    }

}
