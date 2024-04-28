package org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers;

import javax.annotation.processing.Generated;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T12:03:11-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
public class PeliculaTransformerImpl implements PeliculaTransformer {

    @Override
    public Pelicula peliculaDTOAPelicula(PeliculaDTO peliculaDTO) {
        if ( peliculaDTO == null ) {
            return null;
        }

        Pelicula pelicula = new Pelicula();

        pelicula.setId( peliculaDTO.getId() );
        pelicula.setTitulo( peliculaDTO.getTitulo() );
        pelicula.setDescripcion( peliculaDTO.getDescripcion() );
        pelicula.setDuracion( peliculaDTO.getDuracion() );
        pelicula.setPuntuacion( peliculaDTO.getPuntuacion() );
        pelicula.setIdCategoria( peliculaDTO.getIdCategoria() );

        return pelicula;
    }

    @Override
    public PeliculaDTO peliculaAPeliculaDTO(Pelicula pelicula) {
        if ( pelicula == null ) {
            return null;
        }

        PeliculaDTO.PeliculaDTOBuilder peliculaDTO = PeliculaDTO.builder();

        peliculaDTO.id( pelicula.getId() );
        peliculaDTO.titulo( pelicula.getTitulo() );
        peliculaDTO.descripcion( pelicula.getDescripcion() );
        peliculaDTO.duracion( pelicula.getDuracion() );
        peliculaDTO.puntuacion( pelicula.getPuntuacion() );
        peliculaDTO.idCategoria( pelicula.getIdCategoria() );

        return peliculaDTO.build();
    }
}
