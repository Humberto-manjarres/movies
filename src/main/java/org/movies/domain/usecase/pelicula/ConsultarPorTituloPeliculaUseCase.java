package org.movies.domain.usecase.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ConsultarPorTituloPeliculaUseCase {

    private final PeliculaRepository peliculaRepository;

    public Flux<Pelicula> consultarPeliculaPorTitulo(String titulo){
        return peliculaRepository.buscarPeliculaPorTtulo(titulo);
    }

}
