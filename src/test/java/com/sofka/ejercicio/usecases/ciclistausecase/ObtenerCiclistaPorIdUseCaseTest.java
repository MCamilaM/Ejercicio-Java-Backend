package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.collections.Ciclista;
import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ObtenerCiclistaPorIdUseCaseTest {

    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    ObtenerCiclistaPorIdUseCase obtenerCiclistaPorIdUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        obtenerCiclistaPorIdUseCase = new ObtenerCiclistaPorIdUseCase(ciclistaRepository,mapperCiclista);
    }

    @Test
    void obtenerCiclistaPorIdTest(){

        Ciclista ciclista = new Ciclista();
        ciclista.setId(4);
        ciclista.setNombre("Sara Palomino Olaya");
        ciclista.setNumeroCompetidor(12);
        ciclista.setIdEquipo(5);
        ciclista.setIdPais(1);

        when(ciclistaRepository.findById(4)).thenReturn(Mono.just(ciclista));

        StepVerifier.create(obtenerCiclistaPorIdUseCase.apply("4"))
                .expectNextMatches(ciclistaDTO1 -> {
                    assertEquals(ciclistaDTO1.getId(),4);
                    assertEquals(ciclistaDTO1.getNombre(),"Sara Palomino Olaya");
                    assertEquals(ciclistaDTO1.getNumeroCompetidor(), 12);
                    assertEquals(ciclistaDTO1.getIdEquipo(), 5);
                    assertEquals(ciclistaDTO1.getIdPais(),1);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).findById(4);


    }

}