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
import static org.mockito.Mockito.verify;

class ActualizarPaisUseCaseTest {

    @Mock
    PaisRepository paisRepository;

    @Mock
    ActualizarPaisUseCase actualizarPaisUseCase;

    MapperPais mapperPais = new MapperPais();

    @BeforeEach
    public void setUp(){
        paisRepository = mock(PaisRepository.class);
        actualizarPaisUseCase = new ActualizarPaisUseCase(paisRepository, mapperPais);

    }

    @Test
    void actualizarPaisTest(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Colombia");
        pais.setCodigo("CO");

        PaisDTO paisDTO = mapperPais.paisAPaisDTO().apply(pais);
        paisDTO.setNombre("Brasil");
        paisDTO.setCodigo("BR");

        when(paisRepository.save(any(Pais.class))).thenReturn(Mono.just(pais));

        StepVerifier.create(actualizarPaisUseCase.apply(paisDTO))
                .expectNextMatches(paisDTO1 -> {
                    assertEquals(paisDTO1.getId(), 1);
                    assertEquals(paisDTO1.getNombre(), "Brasil");
                    assertEquals(paisDTO1.getCodigo(), "BR");
                    return  true;
                }).verifyComplete();

        verify(paisRepository).save(any(Pais.class));

    }
}