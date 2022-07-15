package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.collections.Ciclista;
import com.sofka.ejercicio.mappers.MapperCiclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.repositories.CiclistaRepository;
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

class ActualizarCiclistaUseCaseTest {

    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    SequenceGeneratorService service;

    @Mock
    ActualizarCiclistaUseCase actualizarCiclistaUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        service = mock(SequenceGeneratorService.class);
        actualizarCiclistaUseCase = new ActualizarCiclistaUseCase(ciclistaRepository,mapperCiclista);
    }
    @Test
    void actualizarCiclistaTest(){
        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Ramiro Fernandez Olaya");
        ciclista.setNumeroCompetidor(45);
        ciclista.setIdEquipo(1);
        ciclista.setIdPais(2);

        CiclistaDTO ciclistaDTO = mapperCiclista.ciclistaACiclistaDTO().apply(ciclista);
        ciclistaDTO.setNumeroCompetidor(99);  //actualizacion

        when(ciclistaRepository.save(any(Ciclista.class))).thenReturn(Mono.just(ciclista));

        StepVerifier.create(actualizarCiclistaUseCase.apply(ciclistaDTO))
                .expectNextMatches(ciclistaDTO1 -> {
                    assertEquals(ciclistaDTO1.getId(),1);
                    assertEquals(ciclistaDTO1.getNombre(),"Ramiro Fernandez Olaya");
                    assertEquals(ciclistaDTO1.getNumeroCompetidor(), 99);
                    assertEquals(ciclistaDTO1.getIdEquipo(), 1);
                    assertEquals(ciclistaDTO1.getIdPais(),2);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).save(any(Ciclista.class));

    }
}