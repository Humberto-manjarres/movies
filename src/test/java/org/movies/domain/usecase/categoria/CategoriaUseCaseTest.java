package org.movies.domain.usecase.categoria;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.model.categoria.gateways.CategoriaRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CategoriaUseCaseTest {

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private CategoriaUseCase categoriaUseCase;

    private Categoria categoria;

    @BeforeEach
    void setUp() {
        categoria = new Categoria();
        categoria.setNombre("Terror");
    }

    @Test
    public void crearCategoria(){
        Mockito.when(categoriaRepository.crearcategoria(categoria)).thenReturn(Mono.just(categoria));
        Mono<Categoria> categoriaResponse = categoriaUseCase.crearCategoria(categoria);
        StepVerifier.create(categoriaResponse)
                .expectNext(categoria).verifyComplete();
    }
}