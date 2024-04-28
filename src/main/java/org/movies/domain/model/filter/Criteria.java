package org.movies.domain.model.filter;

import org.movies.domain.model.pelicula.Pelicula;

public interface Criteria {
    boolean cumpleCriterio(Pelicula pelicula);

    default Criteria and(Criteria other) {
        return (pelicula) -> cumpleCriterio(pelicula) && other.cumpleCriterio(pelicula);
    }

    /*default Criteria or(Criteria other) {
        return (pelicula) -> cumpleCriterio(pelicula) || other.cumpleCriterio(pelicula);
    }*/

}
