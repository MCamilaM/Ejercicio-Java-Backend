package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.EquipoDTO;
import com.sofka.ejercicio.usecases.equipousecase.*;
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
public class EquipoRouter {

    @RouterOperation(beanClass = CrearEquipoUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "crearEquipo", summary = "Crear equipo", tags = {"Equipo"},
                    responses = {@ApiResponse(responseCode = "200", description = "Created"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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

    @RouterOperation(beanClass = ActualizarEquipoUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "actualizarEquipo", summary = "Actualizar equipo", tags = {"Equipo"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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
    @RouterOperation(beanClass = EliminarEquipoPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "eliminarEquipo", summary = "Eliminar equipo", tags = {"Equipo"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "202", description = "Accepted"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> eliminarEquipoPorId(EliminarEquipoPorIdUseCase useCase){
        return route(
                DELETE("/eliminarequipo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }

    @RouterOperation(beanClass = ObtenerEquiposUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "obtenerEquipos", summary = "Obtener equipos", tags = {"Equipo"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerEquipos(ObtenerEquiposUseCase useCase){
        return route(GET("/obtenerequipos").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), EquipoDTO.class))
        );
    }
    @RouterOperation(beanClass = ObtenerEquipoPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerEquipoPorId", summary = "Obtener equipo por id", tags = {"Equipo"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerEquipoPorId(ObtenerEquipoPorIdUseCase useCase){
        return route(GET("/obtenerequipoid/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), EquipoDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerEquipoPorCodigoUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerEquipoPorCodigo", summary = "Obtener equipo por codigo", tags = {"Equipo"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "codigo", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerEquipoPorCodigo(ObtenerEquipoPorCodigoUseCase useCase){
        return route(GET("/obtenerequipocodigo/{codigo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("codigo")), EquipoDTO.class))
        );
    }
}
