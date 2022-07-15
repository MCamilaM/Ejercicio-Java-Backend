package com.sofka.ejercicio.usecases.equipousecase;

import com.sofka.ejercicio.repositories.EquipoRepository;
import com.sofka.ejercicio.usecases.ciclistausecase.EliminarCiclistaPorIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class EliminarEquipoPorIdUseCaseTest {

    @Mock
    EquipoRepository equipoRepository;

    @Mock
    EliminarEquipoPorIdUseCase eliminarEquipoPorIdUseCase;

    @BeforeEach
    public void setUp() {
        equipoRepository = mock(EquipoRepository.class);
        eliminarEquipoPorIdUseCase = new EliminarEquipoPorIdUseCase(equipoRepository);
    }

    @Test
    void eliminarEquipoPorIdTest(){

        when(equipoRepository.deleteById(anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(eliminarEquipoPorIdUseCase.apply("1"))
                .verifyComplete();

        verify(equipoRepository).deleteById(anyInt());
    }

}