package com.sofka.ejercicio.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "ciclista")
public class Ciclista {

    @Transient
    public static final String ID_SEQUENCE_CICLISTA = "id_sequence_ciclista";

    @Id
    private Integer id;
    private String nombre;
    private Integer numeroCompetidor;
    private Integer idEquipo;
    private Integer idPais;


}
