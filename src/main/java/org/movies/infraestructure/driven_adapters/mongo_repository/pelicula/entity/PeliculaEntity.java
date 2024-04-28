package org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Document(collection = "peliculas")
public class PeliculaEntity {
    private String id;
    private String titulo;
    private String descripcion;
    private double duracion;
    private int puntuacion;
    private String idCategoria;
}
