package org.movies.domain.usecase.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ObtenerListaPeliculasUseCase {

    private final PeliculaRepository peliculaRepository;

    public Flux<Pelicula> obtenerListaPeliculasPaginada(int pageNumber, int pageSize){
        return peliculaRepository.obtenerListaPeliculasPaginadas(pageNumber,pageSize);
    }

}
