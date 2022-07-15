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

class ObtenerCiclistasPorIdPaisYIdEquipoUseCaseTest {

    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    ObtenerCiclistasPorIdPaisYIdEquipoUseCase obtenerCiclistasPorIdPaisYIdEquipoUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        obtenerCiclistasPorIdPaisYIdEquipoUseCase = new ObtenerCiclistasPorIdPaisYIdEquipoUseCase(ciclistaRepository,mapperCiclista);
    }

    @Test
    void obtenerCiclistasPorIdPaisYIdEquipoTest(){

        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Fernanda Suarez Gutierrez");
        ciclista.setNumeroCompetidor(33);
        ciclista.setIdEquipo(2);
        ciclista.setIdPais(1);


        when(ciclistaRepository.findCiclistasByIdPaisAndIdEquipo(1,2)).thenReturn(Flux.just(ciclista));

        StepVerifier.create(obtenerCiclistasPorIdPaisYIdEquipoUseCase.apply("1", "2"))
                .expectNextMatches(ciclistaDTO2 -> {
                    assertEquals(ciclistaDTO2.getId(),1);
                    assertEquals(ciclistaDTO2.getNombre(),"Fernanda Suarez Gutierrez");
                    assertEquals(ciclistaDTO2.getNumeroCompetidor(), 33);
                    assertEquals(ciclistaDTO2.getIdEquipo(), 2);
                    assertEquals(ciclistaDTO2.getIdPais(),1);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).findCiclistasByIdPaisAndIdEquipo(1,2);

    }

}