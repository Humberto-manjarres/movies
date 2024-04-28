package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import lombok.RequiredArgsConstructor;
import org.movies.domain.usecase.pelicula.CrearPeliculaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CrearPeliculaController {

    private final CrearPeliculaUseCase crearPeliculaUseCase;

    private final PeliculaTransformer transformer;

    @PostMapping("/crear-pelicula")
    public Mono<PeliculaDTO> agregarPelicula(@RequestBody @Valid PeliculaDTO peliculaDTO){
        return crearPeliculaUseCase.agregarpelicula(transformer.peliculaDTOAPelicula(peliculaDTO))
                .map(transformer::peliculaAPeliculaDTO);
    }

}
