package com.sofka.ejercicio.mappers;

import com.sofka.ejercicio.collections.Equipo;
import com.sofka.ejercicio.models.EquipoDTO;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class MapperEquipo {

    public Function<EquipoDTO, Equipo> EquipoDTOAEquipo(){
        return equipoDTO -> {
            Equipo equipo = new Equipo();
            equipo.setId(equipoDTO.getId());
            equipo.setNombre(equipoDTO.getNombre());
            equipo.setCodigo(equipoDTO.getCodigo());
            equipo.setPais(equipoDTO.getPais());
            return equipo;
        };
    }

    public Function<Equipo, EquipoDTO> equipoAEquipoDTO(){
        return equipo -> new EquipoDTO(
                equipo.getId(),
                equipo.getNombre(),
                equipo.getCodigo(),
                equipo.getPais()
        );
    }

}
