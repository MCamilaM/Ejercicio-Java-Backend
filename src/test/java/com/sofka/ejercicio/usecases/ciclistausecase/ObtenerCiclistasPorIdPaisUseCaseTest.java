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

class ObtenerCiclistasPorIdPaisUseCaseTest {

    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    ObtenerCiclistasPorIdPaisUseCase obtenerCiclistasPorIdPaisUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        obtenerCiclistasPorIdPaisUseCase = new ObtenerCiclistasPorIdPaisUseCase(ciclistaRepository,mapperCiclista);
    }

    @Test
    void obtenerCiclistasPorIdPaisTest(){

        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Fernanda Suarez Gutierrez");
        ciclista.setNumeroCompetidor(33);
        ciclista.setIdEquipo(4);
        ciclista.setIdPais(10);

        when(ciclistaRepository.findCiclistasByIdPais(10)).thenReturn(Flux.just(ciclista));

        StepVerifier.create(obtenerCiclistasPorIdPaisUseCase.apply("10"))
                .expectNextMatches(ciclistaDTO1 -> {
                    assertEquals(ciclistaDTO1.getId(),1);
                    assertEquals(ciclistaDTO1.getNombre(),"Fernanda Suarez Gutierrez");
                    assertEquals(ciclistaDTO1.getNumeroCompetidor(), 33);
                    assertEquals(ciclistaDTO1.getIdEquipo(), 4);
                    assertEquals(ciclistaDTO1.getIdPais(),10);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).findCiclistasByIdPais(10);

    }

}