package org.movies.domain.usecase.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class CrearPeliculaUseCase {

    private final PeliculaRepository peliculaRepository;

    private final CategoriaRepository categoriaRepository;
    public Mono<Pelicula> agregarpelicula(Pelicula pelicula){
        return categoriaRepository.consultarCategoriaPorId(pelicula.getIdCategoria())
                .switchIfEmpty(Mono.error(new RuntimeException("Categoria no existe!")))
                .flatMap(categoria -> peliculaRepository.guardarPelicula(pelicula));
    }

}
