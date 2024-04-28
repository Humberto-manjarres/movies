package org.movies.application;

import org.mapstruct.factory.Mappers;
import org.movies.infraestructure.driven_adapters.mongo_repository.categoria.transformers.CategoriaDBTransformer;
import org.movies.infraestructure.driven_adapters.mongo_repository.pelicula.transformers.PeliculaDBTransformer;
import org.movies.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransformerConfig {

    @Bean
    public CategoriaDBTransformer categoriaDBTransformer(){
        return Mappers.getMapper(CategoriaDBTransformer.class);
    }

    @Bean
    public PeliculaDBTransformer peliculaDBTransformer(){
        return Mappers.getMapper(PeliculaDBTransformer.class);
    }

    @Bean
    public CategoriaTransformer categoriaTransformer(){
        return Mappers.getMapper(CategoriaTransformer.class);
    }

    @Bean
    public PeliculaTransformer peliculaTransformer(){
        return Mappers.getMapper(PeliculaTransformer.class);
    }

}
