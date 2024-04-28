package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.ConsultarPorTituloPeliculaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
@AutoConfigureWebTestClient
class ConsultarPorTituloPeliculaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ConsultarPorTituloPeliculaUseCase peliculaUseCase;

    @MockBean
    private PeliculaTransformer transformer;

    private Pelicula pelicula;

    private PeliculaDTO peliculaDTO;

    List<Pelicula> listaDePeliculas = new ArrayList<>();

    @BeforeEach
    void setUp() {
        pelicula = new Pelicula();
        pelicula.setTitulo("ABC");
        listaDePeliculas.add(pelicula);

        peliculaDTO = PeliculaDTO.builder().titulo("ABC").build();
    }

    @Test
    public void consultarPorTituloTest(){
        String titulo = "A";
        Mockito.when(peliculaUseCase.consultarPeliculaPorTitulo(anyString()))
                .thenReturn(Flux.fromIterable(listaDePeliculas));
        Mockito.when(transformer.peliculaAPeliculaDTO(pelicula)).thenReturn(peliculaDTO);

        webTestClient.get()
                .uri("/api/consultar-pelicula-titulo/{titulo}", titulo)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PeliculaDTO.class)
                .hasSize(1); // Ajusta este tamaño según lo que esperas obtener
    }
}