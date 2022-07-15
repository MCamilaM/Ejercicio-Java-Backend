package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.collections.Equipo;
import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.repositories.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

class ActualizarEquipoUseCaseTest {

    @Mock
    EquipoRepository equipoRepository;

    @Mock
    ActualizarEquipoUseCase actualizarEquipoUseCase;

    MapperEquipo mapperEquipo = new MapperEquipo();

    @BeforeEach
    public void setUp() {
        equipoRepository = mock(EquipoRepository.class);
        actualizarEquipoUseCase = new ActualizarEquipoUseCase(equipoRepository, mapperEquipo);
    }

    @Test
    void actualizarEquipoTest() {
        Equipo equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("equipo1");
        equipo.setCodigo("LP1");
        Pais pais = new Pais(1, "Colombia", "CO");
        equipo.setPais(pais);

        EquipoDTO equipoDTO = mapperEquipo.equipoAEquipoDTO().apply(equipo);
        equipoDTO.setNombre("Equipo1 actualizado");

        when(equipoRepository.save(any(Equipo.class))).thenReturn(Mono.just(equipo));

        StepVerifier.create(actualizarEquipoUseCase.apply(equipoDTO))
                .expectNextMatches(equipoDTO1 -> {
                    assertEquals(equipoDTO1.getId(), 1);
                    assertEquals(equipoDTO1.getNombre(), "Equipo1 actualizado");
                    assertEquals(equipoDTO1.getCodigo(), "LP1");
                    assertEquals(equipoDTO1.getPais(), pais);
                    return true;
                }).verifyComplete();

        verify(equipoRepository).save(any(Equipo.class));

    }
}