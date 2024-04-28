package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.PeliculaFilterUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;

@SpringBootTest
@AutoConfigureWebTestClient
class PeliculaFilterControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PeliculaFilterUseCase peliculaFilterUseCase;

    private Pelicula pelicula1;
    private Pelicula pelicula2;
    List<Pelicula> listaPeliculas = new ArrayList<>();
    @BeforeEach
    void setUp() {
        pelicula1 = new Pelicula();
        pelicula1.setTitulo("ABC");

        pelicula2 = new Pelicula();
        pelicula2.setTitulo("CDE");

        listaPeliculas.add(pelicula1);
        listaPeliculas.add(pelicula2);
    }

    @Test
    public void filtroPeliculasTest(){

        Map<String, Object> filtros = new HashMap<>();
        // Convertir el mapa de filtros a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String filtrosJson = null;
        try {
            filtrosJson = objectMapper.writeValueAsString(filtros);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Mockito.when(peliculaFilterUseCase.filtroPeliculas(any())).thenReturn(Flux.fromIterable(listaPeliculas));

        webTestClient.get()
                .uri("/api/filtro-peliculas")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Pelicula.class)
                .hasSize(2);
    }
}