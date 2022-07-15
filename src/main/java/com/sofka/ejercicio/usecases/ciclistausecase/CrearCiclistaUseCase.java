package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import com.sofka.ejercicio.repositories.GuardarCiclista;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import static com.sofka.ejercicio.collections.Ciclista.ID_SEQUENCE_CICLISTA;

@Service
@Validated
public class CrearCiclistaUseCase implements GuardarCiclista {

    private final CiclistaRepository ciclistaRepository;
    private final MapperCiclista mapperCiclista;
    private final SequenceGeneratorService service;

    public CrearCiclistaUseCase(CiclistaRepository ciclistaRepository, MapperCiclista mapperCiclista, SequenceGeneratorService service) {
        this.ciclistaRepository = ciclistaRepository;
        this.mapperCiclista = mapperCiclista;
        this.service = service;
    }


    @Override
    public Mono<CiclistaDTO> apply(CiclistaDTO ciclistaDTO) {

        var numeroIntegrantesEquipo = ciclistaRepository.countByIdEquipo(ciclistaDTO.getIdEquipo());

        return numeroIntegrantesEquipo.flatMap(cantidad -> {
            if (cantidad < 8) {

                return service.getSequenceNumber(ID_SEQUENCE_CICLISTA).flatMap(id -> {
                    ciclistaDTO.setId(id.intValue());

                    return ciclistaRepository
                            .save(mapperCiclista.ciclistaDTOACliciclista().apply(ciclistaDTO))
                            .thenReturn(ciclistaDTO);
                });
            }

            return Mono.error(new Exception("El equipo ha alcanzo su maximo de integrantes (8)"));

        });
    }
}
