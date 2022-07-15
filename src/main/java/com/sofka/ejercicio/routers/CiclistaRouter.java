package com.sofka.ejercicio.routers;

import com.sofka.ejercicio.models.CiclistaDTO;
import com.sofka.ejercicio.usecases.ciclistausecase.*;
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
public class CiclistaRouter {

    @RouterOperation(beanClass = CrearCiclistaUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "crearCiclista", summary = "Crear ciclista", tags = {"Ciclista"},
                    responses = {@ApiResponse(responseCode = "200", description = "Created"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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
    @RouterOperation(beanClass = ActualizarCiclistaUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "actualizarCiclista", summary = "Actualizar ciclista", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
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
    @RouterOperation(beanClass = EliminarCiclistaPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "eliminarCiclista", summary = "Eliminar ciclista", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "202", description = "Accepted"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> eliminarCiclistaPorId(EliminarCiclistaPorIdUseCase useCase){
        return route(
                DELETE("/eliminarciclista/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.accepted()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), void.class))
        );
    }
    @RouterOperation(beanClass = ObtenerCiclistasUseCase.class, beanMethod = "get",
            operation = @Operation(operationId = "obtenerCiclistas", summary = "Obtener ciclistas", tags = {"Ciclista"},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistas(ObtenerCiclistasUseCase useCase){
        return route(GET("/obtenerciclistas").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.get(), CiclistaDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerCiclistaPorIdUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerCiclistaPorId", summary = "Obtener ciclista por id", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistaPorId(ObtenerCiclistaPorIdUseCase useCase){
        return route(GET("/obtenerciclistaid/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerCiclistaPorNumeroCompetidorUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerCiclistaPorNumero", summary = "Obtener ciclista por numero competidor", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "numeroCompetidor", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistaPorNumeroCompetidor(ObtenerCiclistaPorNumeroCompetidorUseCase useCase){
        return route(GET("/obtenerciclistanumcompetidor/{numeroCompetidor}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("numeroCompetidor")), CiclistaDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerCiclistasPorIdEquipoUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerCiclistaPorIdEquipo", summary = "Obtener ciclista por id equipo", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdEquipo(ObtenerCiclistasPorIdEquipoUseCase useCase){
        return route(GET("/obtenerciclistasidequipo/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerCiclistasPorIdPaisUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerCiclistaPorIdPais", summary = "Obtener ciclista por id pais", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "id", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdPais(ObtenerCiclistasPorIdPaisUseCase useCase){
        return route(GET("/obtenerciclistasidpais/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase.apply(request.pathVariable("id")), CiclistaDTO.class))
        );
    }

    @RouterOperation(beanClass = ObtenerCiclistasPorIdPaisYIdEquipoUseCase.class, beanMethod = "apply",
            operation = @Operation(operationId = "obtenerCiclistaPorIdPaisIdEquipo", summary = "Obtener ciclista por id pais y id equipo", tags = {"Ciclista"},
                    parameters = {@Parameter(in = ParameterIn.PATH, name = "idPais", description = "Integer"),
                            @Parameter(in = ParameterIn.PATH, name = "idEquipo", description = "Integer")},
                    responses = {@ApiResponse(responseCode = "200", description = "OK"),
                            @ApiResponse(responseCode = "401", description = "Unauthorized", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "403", description = "Forbidden", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "404", description = "Not found", content = { @Content(examples = { @ExampleObject(value = "")})}),
                            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = { @Content(examples = { @ExampleObject(value = "")})}),}))
    @Bean
    public RouterFunction<ServerResponse> obtenerCiclistasPorIdPaisYIdEquipo(ObtenerCiclistasPorIdPaisYIdEquipoUseCase useCase){
        return route(GET("/obtenerciclistasidpais/{idPais}/idequipo/{idEquipo}").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(useCase
                                .apply(request.pathVariable("idPais"),
                                        request.pathVariable("idEquipo")),
                                CiclistaDTO.class))
        );
    }

}
