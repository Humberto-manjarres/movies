package org.movies.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import org.movies.domain.model.pelicula.gateways.GenerarComunicadoCartelera;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import org.movies.domain.usecase.categoria.CategoriaUseCase;
import org.movies.domain.usecase.pelicula.ConsultarPorTituloPeliculaUseCase;
import org.movies.domain.usecase.pelicula.CrearPeliculaUseCase;
import org.movies.domain.usecase.pelicula.ObtenerListaPeliculasUseCase;
import org.movies.domain.usecase.pelicula.PeliculaFilterUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigUseCase {

    @Bean
    public CategoriaUseCase categoriaUseCase(CategoriaRepository categoriaRepository){
        return new CategoriaUseCase(categoriaRepository);
    }

    @Bean
    public CrearPeliculaUseCase peliculaUseCase(PeliculaRepository peliculaRepository, CategoriaRepository categoriaRepository, GenerarComunicadoCartelera generarComunicadoCartelera){
        return new CrearPeliculaUseCase(peliculaRepository,categoriaRepository,generarComunicadoCartelera);
    }

    @Bean
    public ConsultarPorTituloPeliculaUseCase consultarPorTituloPeliculaUseCase(PeliculaRepository peliculaRepository){
        return new ConsultarPorTituloPeliculaUseCase(peliculaRepository);
    }

    @Bean
    public ObtenerListaPeliculasUseCase obtenerListaPeliculasUseCase(PeliculaRepository peliculaRepository){
        return new ObtenerListaPeliculasUseCase(peliculaRepository);
    }

    @Bean
    public PeliculaFilterUseCase peliculaFilterUseCase(PeliculaRepository peliculaRepository){
        return new PeliculaFilterUseCase(peliculaRepository);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
