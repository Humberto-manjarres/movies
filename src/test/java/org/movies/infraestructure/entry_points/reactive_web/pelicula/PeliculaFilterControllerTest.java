package org.movies.infraestructure.entry_points.reactive_web.pelicula;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.config.MongoTestConfig;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.usecase.pelicula.PeliculaFilterUseCase;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.dto.PeliculaDTO;
import org.movies.infraestructure.entry_points.reactive_web.pelicula.transformers.PeliculaTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

@SpringBootTest
@AutoConfigureWebTestClient
@Import(MongoTestConfig.class)
class PeliculaFilterControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private PeliculaFilterUseCase peliculaFilterUseCase;

    @MockBean
    private PeliculaTransformer transformer;

    //private Pelicula peliculaFiltro;

    private Pelicula pelicula1;

    private PeliculaDTO peliculaFiltro;

    @BeforeEach
    void setUp() {
        peliculaFiltro = new PeliculaDTO();
        peliculaFiltro.setPuntuacion(5);
        peliculaFiltro.setDuracion(50.0);
        peliculaFiltro.setIdCategoria("123ac");

        pelicula1 = new Pelicula();
        pelicula1.setPuntuacion(5);
        pelicula1.setTitulo("ABC");
        pelicula1.setDuracion(50.0);
        pelicula1.setIdCategoria("123ac");

    }

    @Test
    public void filtroPeliculasTest(){
        Mockito.when(transformer.peliculaDTOAPelicula(peliculaFiltro)).thenReturn(pelicula1);
        Mockito.when(peliculaFilterUseCase.filtroPeliculas(pelicula1)).thenReturn(Flux.just(pelicula1));
        Mockito.when(transformer.peliculaAPeliculaDTO(pelicula1)).thenReturn(peliculaFiltro);

        webTestClient.post()
                .uri("/api/filtro-peliculas")
                .bodyValue(peliculaFiltro)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(PeliculaDTO.class)
                .hasSize(1)
                .contains(peliculaFiltro);

        // Verifica si el método filtroPeliculas fue llamado con el objeto peliculaFiltro
        Mockito.verify(peliculaFilterUseCase).filtroPeliculas(pelicula1);

    }
}