package org.movies.domain.model.filter;

import org.movies.domain.model.pelicula.Pelicula;

public class CriterioPuntuacion implements Criteria{

    private int puntuacion;

    public CriterioPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    @Override
    public boolean cumpleCriterio(Pelicula pelicula) {
        return pelicula.getPuntuacion() == puntuacion;
    }
}
