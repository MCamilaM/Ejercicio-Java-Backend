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

class CrearCiclistaUseCaseTest {

    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    SequenceGeneratorService service;

    @Mock
    CrearCiclistaUseCase crearCiclistaUseCase;

    MapperCiclista mapperCiclista = new MapperCiclista();

    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        service = mock(SequenceGeneratorService.class);
        crearCiclistaUseCase = new CrearCiclistaUseCase(ciclistaRepository,mapperCiclista,service);
    }
    @Test
    void crearCiclistaTest(){
        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Ramiro Fernandez Olaya");
        ciclista.setNumeroCompetidor(45);
        ciclista.setIdEquipo(1);
        ciclista.setIdPais(2);

        CiclistaDTO ciclistaDTO = mapperCiclista.ciclistaACiclistaDTO().apply(ciclista);

        when(ciclistaRepository.countByIdEquipo(anyInt())).thenReturn(Mono.just(1L));
        when(service.getSequenceNumber("id_sequence_ciclista")).thenReturn(Mono.just(1));
        when(ciclistaRepository.save(any(Ciclista.class))).thenReturn(Mono.just(ciclista));

        StepVerifier.create(crearCiclistaUseCase.apply(ciclistaDTO))
                .expectNextMatches(ciclistaDTO1 -> {
                    assertEquals(ciclistaDTO1.getId(),1);
                    return true;
                }).verifyComplete();

        verify(ciclistaRepository).countByIdEquipo(anyInt());
        verify(service).getSequenceNumber("id_sequence_ciclista");
        verify(ciclistaRepository).save(any(Ciclista.class));

    }

    @Test
    void crearCiclistaFailTest(){
        Ciclista ciclista = new Ciclista();
        ciclista.setId(1);
        ciclista.setNombre("Ramiro Fernandez Olaya");
        ciclista.setNumeroCompetidor(45);
        ciclista.setIdEquipo(1);
        ciclista.setIdPais(2);

        CiclistaDTO ciclistaDTO = mapperCiclista.ciclistaACiclistaDTO().apply(ciclista);

        when(ciclistaRepository.countByIdEquipo(anyInt())).thenReturn(Mono.just(8L));


        StepVerifier.create(crearCiclistaUseCase.apply(ciclistaDTO))
                .expectErrorMessage("El equipo ha alcanzo su maximo de integrantes (8)")
                .verify();

        verify(ciclistaRepository).countByIdEquipo(anyInt());

    }


}