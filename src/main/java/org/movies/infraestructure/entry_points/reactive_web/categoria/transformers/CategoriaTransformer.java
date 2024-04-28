package org.movies.infraestructure.entry_points.reactive_web.categoria.transformers;

import org.mapstruct.Mapper;
import org.movies.domain.model.categoria.Categoria;
import org.movies.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;

@Mapper
public interface CategoriaTransformer {
    Categoria categoriaDTOACategoria(CategoriaDTO categoriaDTO);
    CategoriaDTO categoriaACategoriaDTO(Categoria categoria);
}
