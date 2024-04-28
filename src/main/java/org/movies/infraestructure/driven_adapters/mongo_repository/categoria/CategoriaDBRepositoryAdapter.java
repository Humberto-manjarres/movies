package org.movies.infraestructure.driven_adapters.mongo_repository.categoria;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import org.movies.infraestructure.driven_adapters.mongo_repository.categoria.transformers.CategoriaDBTransformer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CategoriaDBRepositoryAdapter implements CategoriaRepository {

    private final CategoriaDBRepository dbRepository;

    private final CategoriaDBTransformer transformer;

    @Override
    public Mono<Categoria> crearcategoria(Categoria categoria) {
        return dbRepository.save(transformer.categoriaACategoriaEntity(categoria))
                .map(transformer::categoriaEntityACategoria)
                .onErrorResume(throwable -> Mono.error(new RuntimeException("Error al intentar crear categoría en BD!" + throwable.getMessage())));
    }

    @Override
    public Mono<Categoria> consultarCategoriaPorId(String idCategoria) {
        return dbRepository.findById(idCategoria)
                .map(transformer::categoriaEntityACategoria)
                .onErrorResume(throwable -> Mono.error(new RuntimeException("Error al intentar consultar categoría en BD!" + throwable.getMessage())));
    }


}
