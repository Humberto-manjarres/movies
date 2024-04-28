package org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers;

import org.mapstruct.Mapper;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;

@Mapper
public interface PeliculaTransformer {
    Pelicula peliculaDTOAPelicula(PeliculaDTO peliculaDTO);
    PeliculaDTO peliculaAPeliculaDTO(Pelicula pelicula);
}
