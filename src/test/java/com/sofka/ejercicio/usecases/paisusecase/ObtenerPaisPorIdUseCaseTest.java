package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.repositories.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerPaisPorIdUseCaseTest {

    @Mock
    PaisRepository paisRepository;

    @Mock
    ObtenerPaisPorIdUseCase obtenerPaisPorIdUseCase;

    MapperPais mapperPais = new MapperPais();

    @BeforeEach
    public void setUp(){
        paisRepository = mock(PaisRepository.class);
        obtenerPaisPorIdUseCase = new ObtenerPaisPorIdUseCase(paisRepository, mapperPais);
    }

    @Test
    void obtenerPaisPorIdTest(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Colombia");
        pais.setCodigo("CO");


        when(paisRepository.findById(anyInt())).thenReturn(Mono.just(pais));

        StepVerifier.create(obtenerPaisPorIdUseCase.apply("1"))
                .expectNextMatches(paisDTO1 -> {
                    assertEquals(paisDTO1.getId(), 1);
                    assertEquals(paisDTO1.getNombre(), "Colombia");
                    assertEquals(paisDTO1.getCodigo(), "CO");
                    return  true;
                }).verifyComplete();

        verify(paisRepository).findById(anyInt());


    }

}