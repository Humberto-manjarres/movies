package org.movies.infraestructure.entry_points.reactive_web.exceptions;

import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Data
public class ApiError {
    private String timestamp;
    private String message;
    private String id;


    /**
     *
     * @param message representa el mensaje que se loggeará en la aplicación y que podrá contener información técnica
     */
    public ApiError(String message) {
        this.timestamp = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
        this.message = message;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString(){
        String errorMessage = "\n==================== Error de aplicación detectado Inicio ==================== \n";
        errorMessage = errorMessage.concat("Fecha: " + timestamp + " \n");
        errorMessage = errorMessage.concat("Mensaje: " + message + " \n");
        errorMessage = errorMessage.concat("Id: " + id + " \n");
        errorMessage = errorMessage.concat("==================== Error de aplicaci\u00F3n detectado Final  ==================== \n");
        return errorMessage;
    }

}
