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

import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class ConsultarPorTituloPeliculaUseCaseTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private ConsultarPorTituloPeliculaUseCase peliculaUseCase;

    private Pelicula pelicula1;

    private Pelicula pelicula2;

    List<Pelicula> listaDePeliculas = new ArrayList<>();

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
    public void deberiaConsultarPeliculasPorTitulo(){
        Mockito.when(peliculaRepository.buscarPeliculaPorTtulo(anyString())).thenReturn(Flux.fromIterable(listaDePeliculas));
        Flux<Pelicula> peliculasBD = peliculaUseCase.consultarPeliculaPorTitulo(anyString());
        StepVerifier.create(peliculasBD)
                .expectNext(pelicula1)
                .expectNext(pelicula2)
                .verifyComplete();
    }
}