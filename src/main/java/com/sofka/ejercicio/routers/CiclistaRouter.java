package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.usecases.ciclistausecase.*;
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
public class CiclistaRouter {

    @Bean
    public RouterFunction<ServerResponse> crearCiclista(CrearCiclistaUseCase useCase){
        Function<CiclistaDTO, Mono<ServerResponse>> executor = ciclistaDTO -> useCase.apply(ciclistaDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                POST("/crearciclista").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CiclistaDTO.class).flatMap(executor)
        );
    }
    @Bean
    public RouterFunction<ServerResponse> actualizarCiclista(ActualizarCiclistaUseCase useCase){
        Function<CiclistaDTO, Mono<ServerResponse>> executor = ciclistaDTO -> useCase.apply(ciclistaDTO)
                .flatMap(result -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(result));

        return route(
                PUT("/actualizarciclista/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(CiclistaDTO.class).flatMap(executor)
        );
    }
    @Bean
    public RouterFunction<ServerResponse> eliminarCiclistaPorId(EliminarCiclistaPorIdUseCase useCase){
        return route(
                DELETE("/eliminarciclista/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistas(ObtenerCiclistasUseCase useCase){
        return route(GET("/obtenerciclistas").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), CiclistaDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistaPorId(ObtenerCiclistaPorIdUseCase useCase){
        return route(GET("/obtenerciclistaid/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistaPorNumeroCompetidor(ObtenerCiclistaPorNumeroCompetidorUseCase useCase){
        return route(GET("/obtenerciclistanumcompetidor/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdEquipo(ObtenerCiclistasPorIdEquipoUseCase useCase){
        return route(GET("/obtenerciclistasidequipo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdPais(ObtenerCiclistasPorIdPaisUseCase useCase){
        return route(GET("/obtenerciclistasidpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdPaisYIdEquipo(ObtenerCiclistasPorIdPaisYIdEquipoUseCase useCase){
        return route(GET("/obtenerciclistasidpais/{idpais}/idequipo/{idequipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                .apply(request.pathVariable("idpais"),
                                        request.pathVariable("idequipo")),
                                CiclistaDTO.class))
        );
    }

}
