package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.AutonoUsrRabbitConfig;
import org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.message.MessageConfiguration;
import org.reactivecommons.async.impl.exceptions.SendFailureNoAckException;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Log
@Component
@RequiredArgsConstructor
public class RabbitSender {

    private final AutonoUsrRabbitConfig rabbitConfig;

    public <T> Mono<Void> sendWithConfirm(T message, String exchange, String routingKey) {

        MessageConfiguration messageConfiguration = new MessageConfiguration();

        return rabbitConfig.buildSender(rabbitConfig.buildConnection())
                .sendWithPublishConfirms(Mono.just(messageConfiguration.toOutboundMessage(message, exchange, routingKey)))
                .flatMap(result -> result.isAck() ?
                Mono.empty() :
                Mono.error(new SendFailureNoAckException("Event no ACK in communications"))
        ).then();
    }

}
