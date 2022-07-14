package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import java.util.function.Supplier;

@Service
@Validated
public class ObtenerCiclistasUseCase implements Supplier<Flux<CiclistaDTO>> {

    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;

    public ObtenerCiclistasUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
    }

    @Override
    public Flux<CiclistaDTO> get() {
        return ciclistaRepository.findAll()
                .map(mapperCiclista.ciclistaACiclistaDTO());
    }
}
