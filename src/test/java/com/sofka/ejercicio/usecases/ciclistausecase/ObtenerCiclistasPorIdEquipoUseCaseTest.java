package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.collections.Ciclista;
import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerCiclistasPorIdEquipoUseCaseTest {
    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    ObtenerCiclistasPorIdEquipoUseCase obtenerCiclistasPorIdEquipoUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        obtenerCiclistasPorIdEquipoUseCase = new ObtenerCiclistasPorIdEquipoUseCase(ciclistaRepository,mapperCiclista);
    }

    @Test
    void obtenerCiclistaPorIdEquipoTest(){

        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Fernanda Suarez Gutierrez");
        ciclista.setNumeroCompetidor(33);
        ciclista.setIdEquipo(4);
        ciclista.setIdPais(10);


        when(ciclistaRepository.findCiclistasByIdEquipo(4)).thenReturn(Flux.just(ciclista));

        StepVerifier.create(obtenerCiclistasPorIdEquipoUseCase.apply("4"))
                .expectNextMatches(ciclistaDTO2 -> {
                    assertEquals(ciclistaDTO2.getId(),1);
                    assertEquals(ciclistaDTO2.getNombre(),"Fernanda Suarez Gutierrez");
                    assertEquals(ciclistaDTO2.getNumeroCompetidor(), 33);
                    assertEquals(ciclistaDTO2.getIdEquipo(), 4);
                    assertEquals(ciclistaDTO2.getIdPais(),10);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).findCiclistasByIdEquipo(4);

    }

}