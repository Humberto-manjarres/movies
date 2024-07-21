package org.movies.infraestructure.entry_points.reactive_web.pelicula.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PeliculaDTO implements Serializable {
    private String id;

    @NotBlank(message = "El título no puede estar vacío")
    private String titulo;
    private String descripcion;
    private double duracion;
    private int puntuacion;
    private String idCategoria;
}
