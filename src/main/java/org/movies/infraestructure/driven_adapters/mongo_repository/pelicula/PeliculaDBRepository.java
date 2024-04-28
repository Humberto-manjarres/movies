package org.movies.infraestructure.driven_adapters.mongo_repository.pelicula;

import org.movies.domain.model.pelicula.Pelicula;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.entity.PeliculaEntity;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PeliculaDBRepository extends ReactiveMongoRepository<PeliculaEntity,String> {

    @Query("{'titulo': {$regex: ?0, $options: 'i'}}")
    Flux<Pelicula> findByTituloLike(String titulo);

}
