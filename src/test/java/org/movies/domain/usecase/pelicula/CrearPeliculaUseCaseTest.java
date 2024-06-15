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
import org.movies.domain.model.pelicula.gateways.GenerarComunicadoCartelera;
import org.movies.domain.model.pelicula.gateways.PeliculaRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
class CrearPeliculaUseCaseTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @Mock
    private CategoriaRepository categoriaRepository;

    @Mock
    private GenerarComunicadoCartelera generarComunicadoCartelera;

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
        pelicula.setIdCategoria(categoria.getId());
    }

    @Test
    public void crearPeliculaTestError(){
        Mockito.when(categoriaRepository.consultarCategoriaPorId(anyString())).thenReturn(Mono.empty());

        Mono<Pelicula> result = crearPeliculaUseCase.agregarpelicula(pelicula);

        StepVerifier.create(result)
                .expectErrorMessage("Categoria no existe!")
                .verify();

    }

    @Test
    public void testAgregarPelicula_CategoriaExiste() {

        Mockito.when(categoriaRepository.consultarCategoriaPorId("123")).thenReturn(Mono.just(categoria));
        Mockito.when(peliculaRepository.guardarPelicula(any(Pelicula.class))).thenReturn(Mono.just(pelicula));
        Mockito.when(generarComunicadoCartelera.publicarCartelera(any(Pelicula.class))).thenReturn(Mono.empty());

        Mono<Pelicula> result = crearPeliculaUseCase.agregarpelicula(pelicula);

        StepVerifier.create(result)
                .expectNext(pelicula)
                .verifyComplete();


        // verificamos que los métodos se hayan llamado durante la ejecución
        Mockito.verify(categoriaRepository).consultarCategoriaPorId("123");
        Mockito.verify(peliculaRepository).guardarPelicula(pelicula);
        Mockito.verify(generarComunicadoCartelera).publicarCartelera(pelicula);
    }

}