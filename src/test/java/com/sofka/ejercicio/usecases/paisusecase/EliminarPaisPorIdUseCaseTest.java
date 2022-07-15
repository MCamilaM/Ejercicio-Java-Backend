package com.sofka.ejercicio.usecases.paisusecase;

import com.sofka.ejercicio.mappers.MapperPais;
import com.sofka.ejercicio.repositories.PaisRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class EliminarPaisPorIdUseCaseTest {

    @Mock
    PaisRepository paisRepository;

    @Mock
    EliminarPaisPorIdUseCase eliminarPaisPorIdUseCase;


    @BeforeEach
    public void setUp(){
        paisRepository = mock(PaisRepository.class);
        eliminarPaisPorIdUseCase = new EliminarPaisPorIdUseCase(paisRepository);

    }

    @Test
    void eliminarPaisPorIdTest(){

        when(paisRepository.deleteById(anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(eliminarPaisPorIdUseCase.apply("1"))
                .verifyComplete();

        verify(paisRepository).deleteById(anyInt());
    }


}