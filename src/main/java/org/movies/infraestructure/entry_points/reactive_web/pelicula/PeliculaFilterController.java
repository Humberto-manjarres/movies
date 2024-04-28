package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.PeliculaFilterUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PeliculaFilterController {

    private final PeliculaFilterUseCase peliculaFilterUseCase;

    @GetMapping("/filtro-peliculas")
    public Flux<Pelicula> filtroPeliculas(@RequestBody Map<String, Object> filtros){
        return peliculaFilterUseCase.filtroPeliculas(filtros);
    }

}
