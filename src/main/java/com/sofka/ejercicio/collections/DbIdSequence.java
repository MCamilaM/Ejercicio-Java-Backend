package com.sofka.ejercicio.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Document(collection = "db_id_sequence")
public class DbIdSequence {
    @Id
    private String  id;
    private Integer sequence;
}
