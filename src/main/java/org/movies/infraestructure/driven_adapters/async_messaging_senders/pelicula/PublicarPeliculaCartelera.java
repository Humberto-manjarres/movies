package org.movies.infraestructure.driven_adapters.async_messaging_senders.pelicula;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.movies.domain.model.commons.Event;
import org.movies.domain.model.pelicula.Pelicula;


@Data
@RequiredArgsConstructor
public class PublicarPeliculaCartelera implements Event {

    private static final String EXCHANGE = "movies.cartelera.pelicula.ex";

    private static final String ROUTINGKEY = "movies.cartelera.pelicula";

    private final Pelicula pelicula;

    @Override
    public String exchange() {
        return EXCHANGE;
    }

    @Override
    public String routingKey() {
        return ROUTINGKEY;
    }

    @Override
    public Object data() {
        return pelicula;
    }
}
