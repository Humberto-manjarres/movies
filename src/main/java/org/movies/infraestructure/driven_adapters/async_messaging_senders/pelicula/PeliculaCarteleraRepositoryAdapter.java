package org.movies.infraestructure.driven_adapters.async_messaging_senders.pelicula;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.movies.domain.model.commons.EventsGateway;
import org.movies.domain.model.pelicula.Pelicula;
import org.movies.domain.model.pelicula.gateways.GenerarComunicadoCartelera;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@AllArgsConstructor
public class PeliculaCarteleraRepositoryAdapter implements GenerarComunicadoCartelera {

    /*@Value("${destinations.queues.cartelera.exchange}")
    private String queueName;

    @Value("${destinations.queues.cartelera.routing-key}")
    private String queueKey;*/

    private final EventsGateway eventsGateway;

    @Override
    public Mono<String> publicarCartelera(Pelicula pelicula) {
        return eventsGateway.emit(new PublicarPeliculaCartelera(pelicula))
                .flatMap(unused -> Mono.just("La solicitud se ha enviado a procesar"));
    }
}
