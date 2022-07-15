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

class ObtenerEquipoPorCodigoUseCaseTest {

    @Mock
    EquipoRepository equipoRepository;

    @Mock
    ObtenerEquipoPorCodigoUseCase obtenerEquipoPorCodigoUseCase;

    MapperEquipo mapperEquipo = new MapperEquipo();

    @BeforeEach
    public void setUp() {
        equipoRepository = mock(EquipoRepository.class);
        obtenerEquipoPorCodigoUseCase = new ObtenerEquipoPorCodigoUseCase(equipoRepository, mapperEquipo);
    }

    @Test
    void obtenerEquipoPorCodigoTest() {
        Equipo equipo = new Equipo();
        equipo.setId(1);
        equipo.setNombre("equipo1");
        equipo.setCodigo("LP1");
        Pais pais = new Pais(1, "Colombia", "CO");
        equipo.setPais(pais);


        when(equipoRepository.findEquipoByCodigo(anyString())).thenReturn(Mono.just(equipo));

        StepVerifier.create(obtenerEquipoPorCodigoUseCase.apply("LP1"))
                .expectNextMatches(equipoDTO1 -> {
                    assertEquals(equipoDTO1.getId(), 1);
                    assertEquals(equipoDTO1.getNombre(), "equipo1");
                    assertEquals(equipoDTO1.getCodigo(), "LP1");
                    assertEquals(equipoDTO1.getPais(), pais);
                    return true;
                }).verifyComplete();

        verify(equipoRepository).findEquipoByCodigo(anyString());
    }
}