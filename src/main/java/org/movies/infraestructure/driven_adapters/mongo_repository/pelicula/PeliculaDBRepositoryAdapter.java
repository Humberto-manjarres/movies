package org.movies.infraestructure.driven_adapters.mongo_repository.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.entity.PeliculaEntity;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.transformers.PeliculaDBTransformer;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PeliculaDBRepositoryAdapter implements PeliculaRepository {

    private final PeliculaDBRepository dbRepository;

    private final PeliculaDBTransformer transformer;

    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<Pelicula> guardarPelicula(Pelicula pelicula) {
        return dbRepository.save(transformer.peliculaAPeliculaEntity(pelicula))
                .map(transformer::peliculaEntityAPelicula);
    }

    @Override
    public Flux<Pelicula> buscarPeliculaPorTtulo(String titulo) {
        return dbRepository.findByTituloLike(titulo);
    }

    @Override
    public Flux<Pelicula> obtenerListaPeliculasPaginadas(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Query query = new Query().with(pageable);
        return reactiveMongoTemplate.find(query, PeliculaEntity.class)
                .map(transformer::peliculaEntityAPelicula);
    }

    @Override
    public Flux<Pelicula> obtenerPeliculas() {
        return dbRepository.findAll().map(transformer::peliculaEntityAPelicula);
    }
}
