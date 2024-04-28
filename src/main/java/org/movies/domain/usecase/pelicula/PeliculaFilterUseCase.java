package org.movies.domain.usecase.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.filter.Criteria;
import org.movies.domain.model.filter.CriterioCategoria;
import org.movies.domain.model.filter.CriterioDuracion;
import org.movies.domain.model.filter.CriterioPuntuacion;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Flux;

import java.util.Map;

@RequiredArgsConstructor
public class PeliculaFilterUseCase {

    private final PeliculaRepository peliculaRepository;

    public Flux<Pelicula> filtroPeliculas(Map<String, Object> filtros){
        Criteria criteria = construirCriterioDesdeFiltros(filtros);
        /*return peliculaRepository.obtenerPeliculas()
                .filter(pelicula -> criteria.cumpleCriterio(pelicula));*/
        return peliculaRepository.obtenerPeliculas()
                .filter(pelicula -> pelicula.getPuntuacion() == (int) filtros.get("puntuacion")
                        && pelicula.getDuracion() == (double) filtros.get("duracion")
                && pelicula.getIdCategoria().equals(filtros.get("idCategoria")));
    }

    private Criteria construirCriterioDesdeFiltros(Map<String, Object> filtros) {
        // Construye los criterios de filtrado dinámicamente basados en los filtros proporcionados
        Criteria criteria = null;

        //filtro por puntuación
        if (filtros.containsKey("puntuacion")) {
            int puntuacion = (int) filtros.get("puntuacion");
            criteria = new CriterioPuntuacion(puntuacion);
        }

        // Construye el criterio de filtrado por duración si está presente en los filtros
        if (filtros.containsKey("duracion")) {
            double duracion = (double) filtros.get("duracion");
            Criteria criterioDuracion = new CriterioDuracion(duracion);
            criteria = (criteria != null) ? criteria.and(criterioDuracion) : criterioDuracion;
        }

        // Construye el criterio de filtrado por idCategoria si está presente en los filtros
        if (filtros.containsKey("idCategoria")) {
            Criteria criterioCategoria = new CriterioCategoria((String) filtros.get("idCategoria"));
            criteria = (criteria != null) ? criteria.and(criterioCategoria) : criterioCategoria;
        }

        return criteria;
    }

}
