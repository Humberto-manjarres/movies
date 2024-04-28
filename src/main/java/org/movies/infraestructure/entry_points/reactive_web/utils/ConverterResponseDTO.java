package org.movies.infraestructure.entry_points.reactive_web.utils;

public class ConverterResponseDTO {

    public static <T> ResponseDTO<T> getResponseDTO(T t, int status,String message){
        return ResponseDTO.<T>builder()
                .status(status)
                .message(message)
                .data(t)
                .build();
    }
}
