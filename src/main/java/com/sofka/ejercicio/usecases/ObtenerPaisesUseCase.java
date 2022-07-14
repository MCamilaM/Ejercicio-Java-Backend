package com.sofka.ejercicio.usecases;

import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.PaisRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@Validated
public class ObtenerPaisesUseCase implements Supplier<Flux<PaisDTO>> {
    private final PaisRepository paisRepository;
    private final MapperPais mapperPais;

    public ObtenerPaisesUseCase(PaisRepository paisRepository, MapperPais mapperPais) {
        this.paisRepository = paisRepository;
        this.mapperPais = mapperPais;
    }

    @Override
    public Flux<PaisDTO> get() {
        return paisRepository.findAll()
                .map(mapperPais.paisAPaisDTO());
    }
}
