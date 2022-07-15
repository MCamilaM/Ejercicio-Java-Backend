package com.sofka.ejercicio.models;

import com.sofka.ejercicio.collections.Pais;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EquipoDTO {

    private Integer id;

    @NotBlank(message = "El nombre del equipo es requerido")
    @Length(max = 30, message = "La longitud del nombre debe ser maximo de 30 caracteres")
    private String nombre;

    @NotBlank(message = "El codigo del equipo es requerido")
    @Length(max = 3, message = "La longitud del codigo del equipo debe ser maximo de 3 caracteres")
    private String codigo;

    @NotNull
    private Pais pais;

}
