package org.movies.infraestructure.driven_adapters.mongo_repository.categoria;

import org.movies.infraestructure.driven_adapters.mongo_repository.categoria.entity.CategoriaEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoriaDBRepository extends ReactiveMongoRepository<CategoriaEntity,String> {
}
