package org.movies.infraestructure.driven_adapters.mongo_repository.categoria.transformers;

import javax.annotation.processing.Generated;
import org.movies.domain.model.categoria.Categoria;
import org.movies.infraestructure.driven_adapters.mongo_repository.categoria.entity.CategoriaEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-28T12:03:11-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.jar, environment: Java 11.0.11 (Oracle Corporation)"
)
public class CategoriaDBTransformerImpl implements CategoriaDBTransformer {

    @Override
    public CategoriaEntity categoriaACategoriaEntity(Categoria categoria) {
        if ( categoria == null ) {
            return null;
        }

        CategoriaEntity.CategoriaEntityBuilder categoriaEntity = CategoriaEntity.builder();

        categoriaEntity.id( categoria.getId() );
        categoriaEntity.nombre( categoria.getNombre() );

        return categoriaEntity.build();
    }

    @Override
    public Categoria categoriaEntityACategoria(CategoriaEntity categoriaEntity) {
        if ( categoriaEntity == null ) {
            return null;
        }

        Categoria categoria = new Categoria();

        categoria.setId( categoriaEntity.getId() );
        categoria.setNombre( categoriaEntity.getNombre() );

        return categoria;
    }
}
