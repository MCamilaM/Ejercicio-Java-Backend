package com.sofka.ejercicio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CiclistaDTO {

    private Integer id;

    @NotBlank(message =  "El nombre del ciclista es requerido")
    private String nombre;

    @NotNull
    private Integer numeroCompetidor;
    @NotNull
    private Integer idEquipo;
    @NotNull
    private Integer idPais;


}
