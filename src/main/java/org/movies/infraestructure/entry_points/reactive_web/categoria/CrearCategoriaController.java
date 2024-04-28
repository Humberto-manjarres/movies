package org.movies.infraestructure.entry_points.reactive_web.categoria;

import lombok.RequiredArgsConstructor;
import org.movies.domain.model.categoria.Categoria;
import org.movies.domain.usecase.categoria.CategoriaUseCase;
import org.movies.infraestructure.entry_points.reactive_web.categoria.dto.CategoriaDTO;
import org.movies.infraestructure.entry_points.reactive_web.categoria.transformers.CategoriaTransformer;
import org.movies.infraestructure.entry_points.reactive_web.utils.Constanst;
import org.movies.infraestructure.entry_points.reactive_web.utils.ConverterResponseDTO;
import org.movies.infraestructure.entry_points.reactive_web.utils.ResponseDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CrearCategoriaController {

    private final CategoriaUseCase categoriaUseCase;

    private final CategoriaTransformer transformer;

    @PostMapping("/crear-categoria")
    public Mono<ResponseDTO<CategoriaDTO>> agregarCategoria(@RequestBody CategoriaDTO categoriaDTO){
        return categoriaUseCase.crearCategoria(transformer.categoriaDTOACategoria(categoriaDTO))
                .map(transformer::categoriaACategoriaDTO)
                .flatMap(categoriaDTOBD -> Mono.just(ConverterResponseDTO.getResponseDTO(categoriaDTOBD, Constanst.OK,Constanst.RESPUESTA_OK)));
    }

}
