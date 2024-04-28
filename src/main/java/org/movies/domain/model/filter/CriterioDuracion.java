package org.movies.domain.model.filter;

import org.movies.domain.model.pelicula.Pelicula;

public class CriterioDuracion implements Criteria{

    private double duracion;

    public CriterioDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public boolean cumpleCriterio(Pelicula pelicula) {
        return pelicula.getDuracion() == duracion;
    }


}
