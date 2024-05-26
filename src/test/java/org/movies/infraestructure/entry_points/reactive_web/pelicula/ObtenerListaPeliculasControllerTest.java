package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.config.MongoTestConfig;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.ObtenerListaPeliculasUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@AutoConfigureWebTestClient
@Import(MongoTestConfig.class)
class ObtenerListaPeliculasControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ObtenerListaPeliculasUseCase listaPeliculasUseCase;

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
    public void obtenerPeliculasPaginadasTest(){
        Mockito.when(listaPeliculasUseCase.obtenerListaPeliculasPaginada(anyInt(),anyInt())).thenReturn(Flux.fromIterable(listaDePeliculas));
        Mockito.when(transformer.peliculaAPeliculaDTO(pelicula)).thenReturn(peliculaDTO);

        webTestClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/peliculas")
                        .queryParam("pageNumber", 0)
                        .queryParam("pageSize", 10)
                        .build())
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PeliculaDTO.class)
                .hasSize(1);
                //.isEqualTo(Arrays.asList(peliculaDTO1, peliculaDTO2));

    }

}