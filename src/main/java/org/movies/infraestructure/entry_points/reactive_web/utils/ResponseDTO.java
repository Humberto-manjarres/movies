package org.movies.infraestructure.entry_points.reactive_web.utils;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ResponseDTO<T> {
    private int status;
    private String message;
    private T data;

}
