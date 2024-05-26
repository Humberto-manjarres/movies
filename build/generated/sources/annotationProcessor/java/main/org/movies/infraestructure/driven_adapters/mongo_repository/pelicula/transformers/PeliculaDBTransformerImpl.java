package org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.transformers;

import javax.annotation.processing.Generated;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.entity.PeliculaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-26T10:47:07-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
public class PeliculaDBTransformerImpl implements PeliculaDBTransformer {

    @Override
    public PeliculaEntity peliculaAPeliculaEntity(Pelicula pelicula) {
        if ( pelicula == null ) {
            return null;
        }

        PeliculaEntity.PeliculaEntityBuilder peliculaEntity = PeliculaEntity.builder();

        peliculaEntity.id( pelicula.getId() );
        peliculaEntity.titulo( pelicula.getTitulo() );
        peliculaEntity.descripcion( pelicula.getDescripcion() );
        peliculaEntity.duracion( pelicula.getDuracion() );
        peliculaEntity.puntuacion( pelicula.getPuntuacion() );
        peliculaEntity.idCategoria( pelicula.getIdCategoria() );

        return peliculaEntity.build();
    }

    @Override
    public Pelicula peliculaEntityAPelicula(PeliculaEntity peliculaEntity) {
        if ( peliculaEntity == null ) {
            return null;
        }

        Pelicula pelicula = new Pelicula();

        pelicula.setId( peliculaEntity.getId() );
        pelicula.setTitulo( peliculaEntity.getTitulo() );
        pelicula.setDescripcion( peliculaEntity.getDescripcion() );
        pelicula.setDuracion( peliculaEntity.getDuracion() );
        pelicula.setPuntuacion( peliculaEntity.getPuntuacion() );
        pelicula.setIdCategoria( peliculaEntity.getIdCategoria() );

        return pelicula;
    }
}
