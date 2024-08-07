package org.movies.infraestructure.entry_points.reactive_web.categoria.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CategoriaDTO implements Serializable {
    private String id;
    private String nombre;
}
