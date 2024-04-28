package org.movies.domain.model.categoria.gateways;

import org.movies.domain.model.categoria.Categoria;
import reactor.core.publisher.Mono;

public interface CategoriaRepository {
    Mono<Categoria> crearcategoria(Categoria categoria);

    Mono<Categoria> consultarCategoriaPorId(String idCategoria);
}
