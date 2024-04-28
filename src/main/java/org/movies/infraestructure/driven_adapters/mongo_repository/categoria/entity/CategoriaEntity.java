package org.movies.infraestructure.driven_adapters.mongo_repository.categoria.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "categorias")
public class CategoriaEntity {
    private String id;
    private String nombre;
}
