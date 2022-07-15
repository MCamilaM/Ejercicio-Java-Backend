package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.PaisDTO;

import com.sofka.ejercicio.usecases.paisusecase.ActualizarPaisUseCase;
import com.sofka.ejercicio.usecases.paisusecase.CrearPaisUseCase;
import com.sofka.ejercicio.usecases.paisusecase.EliminarPaisPorIdUseCase;
import com.sofka.ejercicio.usecases.paisusecase.ObtenerPaisesUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@RestController
@Configuration
public class PaisRouter {

    @Bean
    public RouterFunction<ServerResponse> crearPais(CrearPaisUseCase useCase){
        Function<PaisDTO, Mono<ServerResponse>> executor = paisDTO -> useCase.apply(paisDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/crearpais").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PaisDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> actualizarPais(ActualizarPaisUseCase useCase){
        Function<PaisDTO, Mono<ServerResponse>> executor = paisDTO -> useCase.apply(paisDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/actualizarpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(PaisDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> eliminarPaisPorId(EliminarPaisPorIdUseCase useCase){
        return route(
                DELETE("/eliminarpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerPaises(ObtenerPaisesUseCase useCase){
        return route(GET("/obtenerpaises").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), PaisDTO.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> obtenerPaisPorId(ObtenerPaisPorIdUseCase useCase){
        return route(GET("/obtenerpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), PaisDTO.class))
        );
    }
}
