package org.movies.domain.usecase.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.filter.Criteria;
import org.movies.domain.model.filter.CriterioCategoria;
import org.movies.domain.model.filter.CriterioDuracion;
import org.movies.domain.model.filter.CriterioPuntuacion;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Flux;

import java.util.Objects;

@RequiredArgsConstructor
public class PeliculaFilterUseCase {

    private final PeliculaRepository peliculaRepository;

    public Flux<Pelicula> filtroPeliculas(Pelicula peliculaFiltro){
        Criteria criteria = construirCriterioDesdeFiltros(peliculaFiltro);
        return peliculaRepository.obtenerPeliculas()
                .filter(pelicula -> criteria.cumpleCriterio(pelicula));
    }

    private Criteria construirCriterioDesdeFiltros(Pelicula peliculaFiltro) {
        // Construye los criterios de filtrado dinámicamente basados en los filtros proporcionados
        Criteria criteria = null;

        if (peliculaFiltro.getPuntuacion() > 0){
            criteria = new CriterioPuntuacion(peliculaFiltro.getPuntuacion());
        }

        if (peliculaFiltro.getDuracion()> 0.0){
            Criteria criterioDuracion = new CriterioDuracion(peliculaFiltro.getDuracion());
            criteria = (criteria != null) ? criteria.and(criterioDuracion) : criterioDuracion;
        }

        if (Objects.nonNull(peliculaFiltro.getIdCategoria())){
            Criteria criterioCategoria = new CriterioCategoria(peliculaFiltro.getIdCategoria());
            criteria = (criteria != null) ? criteria.and(criterioCategoria) : criterioCategoria;
        }

        return criteria;
    }

}
