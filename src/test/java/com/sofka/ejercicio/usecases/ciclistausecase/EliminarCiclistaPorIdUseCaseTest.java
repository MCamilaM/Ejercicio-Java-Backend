package com.sofka.ejercicio.usecases.ciclistausecase;

import com.sofka.ejercicio.repositories.CiclistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class EliminarCiclistaPorIdUseCaseTest {
    @Mock
    CiclistaRepository ciclistaRepository;

    @Mock
    EliminarCiclistaPorIdUseCase eliminarCiclistaPorIdUseCase;


    @BeforeEach
    public void setUp(){
        ciclistaRepository = mock(CiclistaRepository.class);
        eliminarCiclistaPorIdUseCase = new EliminarCiclistaPorIdUseCase(ciclistaRepository);
    }

    @Test
    void eliminarCiclistaPorIdTest(){

         when(ciclistaRepository.deleteById(anyInt())).thenReturn(Mono.empty());

        StepVerifier.create(eliminarCiclistaPorIdUseCase.apply("1"))
                .verifyComplete();

        verify(ciclistaRepository).deleteById(anyInt());
    }

}