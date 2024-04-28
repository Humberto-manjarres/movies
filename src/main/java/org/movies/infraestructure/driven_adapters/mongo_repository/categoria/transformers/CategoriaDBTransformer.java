package org.movies.infraestructure.driven_adapters.mongo_repository.categoria.transformers;

import org.mapstruct.Mapper;
import org.movies.domain.model.categoria.Categoria;
import org.movies.infraestructure.driven_adapters.mongo_repository.categoria.entity.CategoriaEntity;

@Mapper
public interface CategoriaDBTransformer {
    CategoriaEntity categoriaACategoriaEntity(Categoria categoria);
    Categoria categoriaEntityACategoria(CategoriaEntity categoriaEntity);
}
