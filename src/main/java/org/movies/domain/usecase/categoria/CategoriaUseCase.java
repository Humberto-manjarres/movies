package org.movies.domain.usecase.categoria;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CategoriaUseCase {

    private final CategoriaRepository categoriaRepository;
    public Mono<Categoria> crearCategoria(Categoria categoria){
        return categoriaRepository.crearcategoria(categoria);
    }
}
