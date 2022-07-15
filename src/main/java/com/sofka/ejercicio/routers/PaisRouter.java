package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.PaisDTO;

import com.sofka.ejercicio.usecases.paisusecase.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;



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

    @RouterOperation(beanClass = CrearPaisUseCase.class, beanMethod = "apply",
                    operation = @Operation(operationId = "crearPais", summary = "Crear país", tags = {"País"},
    responses = {@ApiResponse(responseCode = "200", description = "Created"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
        @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
        @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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

    @RouterOperation(beanClass = ActualizarPaisUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "actualizarPais", summary = "Actualizar país", tags = {"País"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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

    @RouterOperation(beanClass = EliminarPaisPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "eliminarPais", summary = "Eliminar país", tags = {"País"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "202", description = "Accepted"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> eliminarPaisPorId(EliminarPaisPorIdUseCase useCase){
        return route(
                DELETE("/eliminarpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }

    @RouterOperation(beanClass = ObtenerPaisesUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "obtenerPaises", summary = "Obtener paises", tags = {"País"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerPaises(ObtenerPaisesUseCase useCase){
        return route(GET("/obtenerpaises").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), PaisDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerPaisPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerPaisPorId", summary = "Obtener país por id", tags = {"País"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerPaisPorId(ObtenerPaisPorIdUseCase useCase){
        return route(GET("/obtenerpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), PaisDTO.class))
        );
    }
}
