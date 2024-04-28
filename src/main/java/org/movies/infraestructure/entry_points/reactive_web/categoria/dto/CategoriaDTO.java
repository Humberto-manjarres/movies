package org.movies.infraestructure.entry_points.reactive_web.categoria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoriaDTO {
    private String id;
    private String nombre;
}
