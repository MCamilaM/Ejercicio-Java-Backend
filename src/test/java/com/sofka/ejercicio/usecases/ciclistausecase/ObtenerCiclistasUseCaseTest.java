package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.collections.Ciclista;
import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerCiclistasUseCaseTest {
    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    ObtenerCiclistasUseCase obtenerCiclistasUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        obtenerCiclistasUseCase = new ObtenerCiclistasUseCase(ciclistaRepository,mapperCiclista);
    }

    @Test
    void obtenerCiclistasTest(){

        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Fernanda Suarez Gutierrez");
        ciclista.setNumeroCompetidor(33);
        ciclista.setIdEquipo(4);
        ciclista.setIdPais(10);


        when(ciclistaRepository.findAll()).thenReturn(Flux.just(ciclista));

        StepVerifier.create(obtenerCiclistasUseCase.get())
                .expectNextMatches(ciclistaDTO2 -> {
                    assertEquals(ciclistaDTO2.getId(),1);
                    assertEquals(ciclistaDTO2.getNombre(),"Fernanda Suarez Gutierrez");
                    assertEquals(ciclistaDTO2.getNumeroCompetidor(), 33);
                    assertEquals(ciclistaDTO2.getIdEquipo(), 4);
                    assertEquals(ciclistaDTO2.getIdPais(),10);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).findAll();

    }

}