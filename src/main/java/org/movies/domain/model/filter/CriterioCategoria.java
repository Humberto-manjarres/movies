package org.movies.domain.model.filter;

import org.movies.domain.model.pelicula.Pelicula;

public class CriterioCategoria implements Criteria{

    private String idCategoria;

    public CriterioCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public boolean cumpleCriterio(Pelicula pelicula) {
        return pelicula.getIdCategoria().equals(idCategoria);
    }
}
