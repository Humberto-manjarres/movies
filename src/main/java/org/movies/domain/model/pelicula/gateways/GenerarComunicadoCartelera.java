package org.movies.domain.model.pelicula.gateways;

import org.movies.domain.model.pelicula.Pelicula;
import reactor.core.publisher.Mono;

public interface GenerarComunicadoCartelera {
    Mono<String> publicarCartelera(Pelicula pelicula);
}
