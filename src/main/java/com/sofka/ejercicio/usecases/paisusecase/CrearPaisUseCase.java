package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.GuardarPais;
import com.sofka.ejercicio.repositories.PaisRepository;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
import static com.sofka.ejercicio.collections.Pais.ID_SEQUENCE_PAIS;

@Service
@Validated
public class CrearPaisUseCase implements GuardarPais {

    private final PaisRepository paisRepository;
    private final MapperPais mapperPais;
    private final SequenceGeneratorService service;

    public CrearPaisUseCase(PaisRepository paisRepository, MapperPais mapperPais, SequenceGeneratorService service) {
        this.paisRepository = paisRepository;
        this.mapperPais = mapperPais;
        this.service = service;
    }


    @Override
    public Mono<PaisDTO> apply(PaisDTO paisDTO) {
        return service.getSequenceNumber(ID_SEQUENCE_PAIS).flatMap(id -> {
            paisDTO.setId(id.intValue());
            return paisRepository
                    .save(mapperPais.paisDTOAPais().apply(paisDTO))
                    .thenReturn(paisDTO);
        });
    }
}
