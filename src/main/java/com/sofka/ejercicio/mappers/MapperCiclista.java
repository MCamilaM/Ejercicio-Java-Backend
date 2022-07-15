package com.sofka.ejercicio.mappers;

import com.sofka.ejercicio.collections.Ciclista;
import com.sofka.ejercicio.models.CiclistaDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperCiclista {

    public Function<CiclistaDTO, Ciclista> ciclistaDTOACliciclista(){
        return ciclistaDTO -> {
            Ciclista ciclista = new Ciclista();
            ciclista.setId(ciclistaDTO.getId());
            ciclista.setNombre(ciclistaDTO.getNombre());
            ciclista.setNumeroCompetidor(ciclistaDTO.getNumeroCompetidor());
            ciclista.setIdEquipo(ciclistaDTO.getIdEquipo());
            ciclista.setIdPais(ciclistaDTO.getIdPais());
            return ciclista;
        };
    }

    public Function<Ciclista, CiclistaDTO> ciclistaACiclistaDTO(){
        return ciclista -> new CiclistaDTO(
                ciclista.getId(),
                ciclista.getNombre(),
                ciclista.getNumeroCompetidor(),
                ciclista.getIdEquipo(),
                ciclista.getIdPais()
        );
    }
}
