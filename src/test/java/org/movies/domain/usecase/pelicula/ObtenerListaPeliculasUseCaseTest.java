package org.movies.domain.usecase.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
class ObtenerListaPeliculasUseCaseTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    private Pelicula pelicula1;

    private Pelicula pelicula2;

    List<Pelicula> listaDePeliculas = new ArrayList<>();

    @InjectMocks
    private ObtenerListaPeliculasUseCase listaPeliculasUseCase;

    @BeforeEach
    void setUp() {
        pelicula1 = new Pelicula();
        pelicula1.setTitulo("ABC");

        pelicula2 = new Pelicula();
        pelicula2.setTitulo("ABC");

        listaDePeliculas.add(pelicula1);
        listaDePeliculas.add(pelicula2);
    }

    @Test
    public void obtenerListaPeliculasPaginadaTest(){
        Mockito.when(peliculaRepository.obtenerListaPeliculasPaginadas(anyInt(),anyInt())).thenReturn(Flux.fromIterable(listaDePeliculas));
        Flux<Pelicula> peliculasBD = listaPeliculasUseCase.obtenerListaPeliculasPaginada(anyInt(), anyInt());
        StepVerifier.create(peliculasBD)
                .expectNext(pelicula1)
                .expectNext(pelicula2)
                .verifyComplete();
    }

}