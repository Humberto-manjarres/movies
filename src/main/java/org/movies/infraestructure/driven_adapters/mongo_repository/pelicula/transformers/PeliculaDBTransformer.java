package org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.transformers;

import org.mapstruct.Mapper;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.entity.PeliculaEntity;

@Mapper
public interface PeliculaDBTransformer {

    PeliculaEntity peliculaAPeliculaEntity(Pelicula pelicula);
    Pelicula peliculaEntityAPelicula(PeliculaEntity peliculaEntity);
}
