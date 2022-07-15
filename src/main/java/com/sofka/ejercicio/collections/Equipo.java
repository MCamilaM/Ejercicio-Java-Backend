package com.sofka.ejercicio.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "equipo")
public class Equipo {

    @Transient
    public static final String ID_SEQUENCE_EQUIPO = "id_sequence_equipo";

    @Id
    private Integer id;
    private String nombre;
    @Indexed(unique = true)
    private String codigo;
    private Pais pais;
}
