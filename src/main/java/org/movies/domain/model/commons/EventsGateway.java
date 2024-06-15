package org.movies.domain.model.commons;

import reactor.core.publisher.Mono;

public interface EventsGateway {
    Mono<Void> emit(Event event);
}
