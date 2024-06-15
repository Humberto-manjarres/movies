package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.message;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.reactivecommons.async.impl.communications.Message;
import reactor.rabbitmq.OutboundMessage;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class MessageConfigurationTest {

    @InjectMocks
    private MessageConfiguration messageConfiguration;

    @Test
    public void testToOutboundMessage(){
        String message = "{\"id\": \"084\", \"nombre\": \"Terror\"}";
        OutboundMessage outboundMessage = messageConfiguration.toOutboundMessage(message, "test.exchange", "test.routingKey");
        assertThat(outboundMessage).isNotNull();
    }

    @Test
    public void testToMessage(){
        String message = "{\"id\": \"084\", \"nombre\": \"Terror\"}";
        Message messageResult = messageConfiguration.toMessage(message);
        assertThat(messageResult).isNotNull();
    }

}