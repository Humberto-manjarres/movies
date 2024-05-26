package org.movies.infraestructure.entry_points.reactive_web.categoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.movies.config.MongoTestConfig;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.usecase.categoria.CategoriaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import org.movies.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import org.movies.infraestructure.entry_points.reactive_web.utils.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebTestClient
@Import(MongoTestConfig.class)
class CrearCategoriaControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CategoriaUseCase categoriaUseCase;

    @MockBean
    private CategoriaTransformer categoriaTransformer;

    private CategoriaDTO categoriaDTO;

    private Categoria categoria;

    private ResponseDTO<CategoriaDTO> dtoResponseDTO;

    @BeforeEach
    void setUp() {
        categoriaDTO = CategoriaDTO.builder().nombre("Terror").build();
        categoria = new Categoria();

        dtoResponseDTO = new ResponseDTO<>();
        dtoResponseDTO.setStatus(200);
        dtoResponseDTO.setMessage("Respuesta ok");
    }

    @Test
    public void crearCategoriaTest(){
        Mockito.when(categoriaTransformer.categoriaDTOACategoria(categoriaDTO)).thenReturn(categoria);
        Mockito.when(categoriaUseCase.crearCategoria(categoria)).thenReturn(Mono.just(categoria));
        Mockito.when(categoriaTransformer.categoriaACategoriaDTO(categoria)).thenReturn(categoriaDTO);

        WebTestClient.ResponseSpec exchange = webTestClient.post()
                .uri("/api/crear-categoria")
                .bodyValue(categoriaDTO)
                .exchange();

        exchange.expectBody(ResponseDTO.class)
                .consumeWith(categoriaBody ->{
                    assertNotNull(categoriaBody.getResponseBody().getData());
                    assertEquals(dtoResponseDTO.getStatus(),categoriaBody.getResponseBody().getStatus());
                    assertEquals(dtoResponseDTO.getMessage(),categoriaBody.getResponseBody().getMessage());
                });

    }


}