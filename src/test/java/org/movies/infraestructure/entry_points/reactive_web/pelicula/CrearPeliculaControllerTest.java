package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.CrearPeliculaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureWebTestClient
class CrearPeliculaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CrearPeliculaUseCase crearPeliculaUseCase;

    @MockBean
    private PeliculaTransformer peliculaTransformer;

    private PeliculaDTO peliculaDTO;

    private Pelicula pelicula;

    @BeforeEach
    void setUp() {
        peliculaDTO = PeliculaDTO.builder()
                .titulo("ABC")
                .build();
        pelicula = new Pelicula();
    }

    @Test
    public void crearPelicula(){
        Mockito.when(peliculaTransformer.peliculaDTOAPelicula(peliculaDTO)).thenReturn(pelicula);
        Mockito.when(crearPeliculaUseCase.agregarpelicula(pelicula)).thenReturn(Mono.just(pelicula));
        Mockito.when(peliculaTransformer.peliculaAPeliculaDTO(pelicula)).thenReturn(peliculaDTO);

        WebTestClient.ResponseSpec exchange = webTestClient.post()
                .uri("/api/crear-pelicula")
                .bodyValue(peliculaDTO)
                .exchange();

        exchange.expectBody(PeliculaDTO.class)
                .consumeWith(peli -> {
                    assertEquals(peliculaDTO.getTitulo(),peli.getResponseBody().getTitulo());
                });

    }
}