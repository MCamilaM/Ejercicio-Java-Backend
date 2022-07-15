package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.models.PaisDTO;
import com.sofka.ejercicio.repositories.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ObtenerPaisesUseCaseTest {

    @Mock
    PaisRepository paisRepository;

    @Mock
    ObtenerPaisesUseCase obtenerPaisesUseCase;

    MapperPais mapperPais = new MapperPais();

    @BeforeEach
    public void setUp(){
        paisRepository = mock(PaisRepository.class);
        obtenerPaisesUseCase = new ObtenerPaisesUseCase(paisRepository, mapperPais);
    }

    @Test
    void obtenerPaisesTest(){
        Pais pais = new Pais();
        pais.setId(1);
        pais.setNombre("Colombia");
        pais.setCodigo("CO");

        PaisDTO paisDTO = mapperPais.paisAPaisDTO().apply(pais);

        when(paisRepository.findAll()).thenReturn(Flux.just(pais));

        StepVerifier.create(obtenerPaisesUseCase.get())
                .expectNextMatches(paisDTO1 -> {
                    assertEquals(paisDTO1.getId(), 1);
                    assertEquals(paisDTO1.getNombre(), "Colombia");
                    assertEquals(paisDTO1.getCodigo(), "CO");
                    return  true;
                }).verifyComplete();

        verify(paisRepository).findAll();


    }

}