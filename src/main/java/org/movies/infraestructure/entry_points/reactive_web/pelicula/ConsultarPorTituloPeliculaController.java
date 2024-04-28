package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.usecase.pelicula.ConsultarPorTituloPeliculaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ConsultarPorTituloPeliculaController {

    private final ConsultarPorTituloPeliculaUseCase consultarPorTituloPeliculaUseCase;

    private final PeliculaTransformer transformer;

    @GetMapping("/consultar-pelicula-titulo/{titulo}")
    public Flux<PeliculaDTO> consultarPelicula(@PathVariable String titulo){
        return consultarPorTituloPeliculaUseCase.consultarPeliculaPorTitulo(titulo)
                .map(transformer::peliculaAPeliculaDTO);
    }

}
