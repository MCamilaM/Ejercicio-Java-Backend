package com.sofka.ejercicio.mappers;

import com.sofka.ejercicio.collections.Pais;
import com.sofka.ejercicio.models.PaisDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperPais {

    public Function<PaisDTO, Pais> paisDTOAPais(){
        return paisDTO -> {
            Pais pais = new Pais();
            pais.setId(paisDTO.getId());
            pais.setNombre(paisDTO.getNombre());
            pais.setCodigo(paisDTO.getCodigo().toUpperCase());
            return pais;
        };
    }

    public Function<Pais, PaisDTO> paisAPaisDTO(){
        return pais -> new PaisDTO(
                pais.getId(),
                pais.getNombre(),
                pais.getCodigo().toUpperCase()
        );
    }
}
