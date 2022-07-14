package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.usecases.equipousecase.ActualizarEquipoUseCase;
import com.sofka.ejercicio.usecases.equipousecase.CrearEquipoUseCase;
import com.sofka.ejercicio.usecases.equipousecase.EliminarEquipoPorIdUseCase;
import com.sofka.ejercicio.usecases.equipousecase.ObtenerEquiposUseCase;
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
public class EquipoRouter {

    @Bean
    public RouterFunction<ServerResponse> crearEquipo(CrearEquipoUseCase useCase){
        Function<EquipoDTO, Mono<ServerResponse>> executor = equipoDTO -> useCase.apply(equipoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/crearequipo").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(EquipoDTO.class).flatMap(executor)
        );
    }

    @Bean
    public RouterFunction<ServerResponse> actualizarEquipo(ActualizarEquipoUseCase useCase){
        Function<EquipoDTO, Mono<ServerResponse>> executor = equipoDTO -> useCase.apply(equipoDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/actualizarequipo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(EquipoDTO.class).flatMap(executor)
        );
    }
    @Bean
    public RouterFunction<ServerResponse> eliminarEquipoPorId(EliminarEquipoPorIdUseCase useCase){
        return route(
                DELETE("/eliminarequipo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> obtenerEquipos(ObtenerEquiposUseCase useCase){
        return route(GET("/obtenerequipos").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), EquipoDTO.class))
        );
    }
}
