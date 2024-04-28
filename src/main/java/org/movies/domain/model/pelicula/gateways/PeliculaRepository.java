package org.movies.domain.model.pelicula.gateways;

import org.movies.domain.model.pelicula.Pelicula;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PeliculaRepository {
    Mono<Pelicula> guardarPelicula(Pelicula pelicula);
    Flux<Pelicula> buscarPeliculaPorTtulo(String titulo);
    Flux<Pelicula> obtenerListaPeliculasPaginadas(int pageNumber, int pageSize);
    Flux<Pelicula> obtenerPeliculas();
}
