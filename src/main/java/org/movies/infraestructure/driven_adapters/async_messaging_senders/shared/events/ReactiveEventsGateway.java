package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.events;

import org.movies.domain.model.commons.Event;
import org.movies.domain.model.commons.EventsGateway;
import org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.sender.RabbitSender;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class ReactiveEventsGateway implements EventsGateway {

    private final RabbitSender rabbitSender;

    public ReactiveEventsGateway(RabbitSender rabbitSender) {
        this.rabbitSender = rabbitSender;
    }

    @Override
    public Mono<Void> emit(Event event) {
        return rabbitSender.sendWithConfirm(event.data(), event.exchange(), event.routingKey());
    }
}
