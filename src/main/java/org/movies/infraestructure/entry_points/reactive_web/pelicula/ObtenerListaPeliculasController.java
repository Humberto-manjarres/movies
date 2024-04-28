package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.usecase.pelicula.ObtenerListaPeliculasUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ObtenerListaPeliculasController {

    private final ObtenerListaPeliculasUseCase listaPeliculasUseCase;

    private final PeliculaTransformer transformer;

    @GetMapping("/peliculas")
    public Flux<PeliculaDTO> obtenerPeliculasPaginadas(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int pageSize) {
        return listaPeliculasUseCase.obtenerListaPeliculasPaginada(pageNumber, pageSize)
                .map(transformer::peliculaAPeliculaDTO);
    }
}
