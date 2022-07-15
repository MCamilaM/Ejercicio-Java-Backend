package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.PaisRepository;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrearPaisUseCaseTest {

    @Mock
    PaisRepository paisRepository;

    @Mock
    SequenceGeneratorService service;

    @Mock
    CrearPaisUseCase crearPaisUseCase;

    MapperPais mapperPais = new MapperPais();

    @BeforeEach
    public void setUp(){
        paisRepository = mock(PaisRepository.class);
        service = mock(SequenceGeneratorService.class);
        crearPaisUseCase = new CrearPaisUseCase(paisRepository, mapperPais,service);

    }

    @Test
    void crearPaisTest(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Colombia");
        pais.setCodigo("CO");

        PaisDTO paisDTO = mapperPais.paisAPaisDTO().apply(pais);

        when(service.getSequenceNumber("id_sequence_pais")).thenReturn(Mono.just(1));
        when(paisRepository.save(any(Pais.class))).thenReturn(Mono.just(pais));

        StepVerifier.create(crearPaisUseCase.apply(paisDTO))
                .expectNextMatches(paisDTO1 -> {
                    assertEquals(paisDTO1.getId(), 1);
                    assertEquals(paisDTO1.getNombre(), "Colombia");
                    assertEquals(paisDTO1.getCodigo(), "CO");
                    return  true;
                }).verifyComplete();

        verify(service). getSequenceNumber("id_sequence_pais");
        verify(paisRepository).save(any(Pais.class));


    }

}