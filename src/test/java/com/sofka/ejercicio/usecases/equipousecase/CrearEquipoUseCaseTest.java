package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.collections.Equipo;
import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import com.sofka.ejercicio.service.SequenceGeneratorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrearEquipoUseCaseTest {

    @Mock
    EquipoRepository equipoRepository;

    @Mock
    SequenceGeneratorService service;

    @Mock
    CrearEquipoUseCase crearEquipoUseCase;

    MapperEquipo mapperEquipo = new MapperEquipo();

    @BeforeEach
    public void setUp(){
        equipoRepository = mock(EquipoRepository.class);
        service = mock(SequenceGeneratorService.class);
        crearEquipoUseCase = new CrearEquipoUseCase(equipoRepository,mapperEquipo,service);
    }

    @Test
    void crearEquipoTest(){
        Equipo equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("equipo1");
        equipo.setCodigo("LP1");
        Pais pais = new Pais(1, "Colombia", "CO");
        equipo.setPais(pais);

        EquipoDTO equipoDTO = mapperEquipo.equipoAEquipoDTO().apply(equipo);

        when(service.getSequenceNumber("id_sequence_equipo")).thenReturn(Mono.just(1));
        when(equipoRepository.save(any(Equipo.class))).thenReturn(Mono.just(equipo));

        StepVerifier.create(crearEquipoUseCase.apply(equipoDTO))
                .expectNextMatches(equipoDTO1 -> {
                    assertEquals(equipoDTO1.getId(),1);
                    assertEquals(equipoDTO1.getNombre(), "equipo1");
                    assertEquals(equipoDTO1.getCodigo(),"LP1");
                    assertEquals(equipoDTO1.getPais(), pais);
                    return true;
                }).verifyComplete();

        verify(service).getSequenceNumber("id_sequence_equipo");
        verify(equipoRepository).save(any(Equipo.class));

    }





















}