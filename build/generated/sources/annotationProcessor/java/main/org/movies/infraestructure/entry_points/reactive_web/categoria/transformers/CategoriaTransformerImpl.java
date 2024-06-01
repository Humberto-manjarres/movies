package org.movies.infraestructure.entry_points.reactive_web.categoria.transformers;

import javax.annotation.processing.Generated;
import org.movies.domain.model.categoria.Categoria;
import org.movies.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-01T12:35:57-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
public class CategoriaTransformerImpl implements CategoriaTransformer {

    @Override
    public Categoria categoriaDTOACategoria(CategoriaDTO categoriaDTO) {
        if ( categoriaDTO == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaDTO.getId() );
        categoria.setNombre( categoriaDTO.getNombre() );

        return categoria;
    }

    @Override
    public CategoriaDTO categoriaACategoriaDTO(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaDTO.CategoriaDTOBuilder categoriaDTO = CategoriaDTO.builder();

        categoriaDTO.id( categoria.getId() );
        categoriaDTO.nombre( categoria.getNombre() );

        return categoriaDTO.build();
    }
}
