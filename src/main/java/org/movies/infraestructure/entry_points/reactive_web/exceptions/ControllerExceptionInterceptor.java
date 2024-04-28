package org.movies.infraestructure.entry_points.reactive_web.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerExceptionInterceptor {
    /**
     * El método handleRuntimeException no se invoca directamente en el código.
     * En su lugar, es invocado por Spring cuando se lanza una excepción del tipo RuntimeException.
     * La configuración de Spring detectará automáticamente este método anotado con @ExceptionHandler y lo utilizará para manejar las excepciones correspondientes.
     * */

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
        ApiError apiError = new ApiError(exception.getMessage());
        log.error(apiError.toString());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
