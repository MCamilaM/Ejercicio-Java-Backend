package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.collections.Equipo;
import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.mappers.MapperEquipo;
import com.sofka.ejercicio.repositories.EquipoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerEquipoPorIdUseCaseTest {

    @Mock
    EquipoRepository equipoRepository;

    @Mock
    ObtenerEquipoPorIdUseCase obtenerEquipoPorIdUseCase;

    MapperEquipo mapperEquipo = new MapperEquipo();

    @BeforeEach
    public void setUp() {
        equipoRepository = mock(EquipoRepository.class);
        obtenerEquipoPorIdUseCase = new ObtenerEquipoPorIdUseCase(equipoRepository, mapperEquipo);
    }

    @Test
    void obtenerEquipoPorIdTest() {
        Equipo equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("equipo1");
        equipo.setCodigo("LP1");
        Pais pais = new Pais(1, "Colombia", "CO");
        equipo.setPais(pais);


        when(equipoRepository.findById(anyInt())).thenReturn(Mono.just(equipo));

        StepVerifier.create(obtenerEquipoPorIdUseCase.apply("1"))
                .expectNextMatches(equipoDTO1 -> {
                    assertEquals(equipoDTO1.getId(), 1);
                    assertEquals(equipoDTO1.getNombre(), "equipo1");
                    assertEquals(equipoDTO1.getCodigo(), "LP1");
                    assertEquals(equipoDTO1.getPais(), pais);
                    return true;
                }).verifyComplete();

        verify(equipoRepository).findById(anyInt());
    }

}