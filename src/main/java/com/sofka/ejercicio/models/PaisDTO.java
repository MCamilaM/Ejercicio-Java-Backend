package com.sofka.ejercicio.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class PaisDTO {

    private Integer id;

    @NotBlank
    private String nombre;
    @NotBlank
    private String codigo;
}
