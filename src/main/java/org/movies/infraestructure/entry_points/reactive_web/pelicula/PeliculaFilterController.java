package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.PeliculaFilterUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PeliculaFilterController {

    private final PeliculaFilterUseCase peliculaFilterUseCase;

    private final PeliculaTransformer transformer;

    @PostMapping("/filtro-peliculas")
    public Flux<PeliculaDTO> filtroPeliculas(@RequestBody PeliculaDTO peliculaDTO){
        return peliculaFilterUseCase.filtroPeliculas(transformer.peliculaDTOAPelicula(peliculaDTO))
                .map(transformer::peliculaAPeliculaDTO);
    }

}
