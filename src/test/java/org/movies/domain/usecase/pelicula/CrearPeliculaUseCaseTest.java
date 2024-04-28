package org.movies.domain.usecase.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CrearPeliculaUseCaseTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CrearPeliculaUseCase crearPeliculaUseCase;

    private Categoria categoria;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setId("123");
        categoria.setNombre("Terror");

        pelicula = new Pelicula();
        pelicula.setTitulo("ABC");
        pelicula.setIdCategoria("123");
    }

    @Test
    public void crearPeliculaTest(){
        Mockito.when(categoriaRepository.consultarCategoriaPorId(anyString())).thenReturn(Mono.just(categoria));
        //Mockito.when(peliculaRepository.guardarPelicula(pelicula)).thenReturn(Mono.just(pelicula));

        Mono<Pelicula> agregarpeliculaResponse = crearPeliculaUseCase.agregarpelicula(pelicula);

        StepVerifier.create(agregarpeliculaResponse)
                .consumeNextWith(pelicula1 -> {
                assertEquals(pelicula.getTitulo(),pelicula1.getTitulo());
        });

    }
}