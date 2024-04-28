package org.movies.domain.model.filter;

import org.movies.domain.model.pelicula.Pelicula;
import reactor.core.publisher.Flux;

public class Filtro {
    public Flux<Pelicula> filtrar(Flux<Pelicula> personas, Criteria criteria) {
        return personas.filter(persona -> criteria.cumpleCriterio(persona));
    }
}
