package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.sender;

import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.AutonoUsrRabbitConfig;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.rabbitmq.OutboundMessage;
import reactor.rabbitmq.OutboundMessageResult;
import reactor.rabbitmq.Sender;
import reactor.test.StepVerifier;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class RabbitSenderTest {


    @Mock
    private AutonoUsrRabbitConfig rabbitConfig;

    @Mock
    private Sender sender;

    @InjectMocks
    private RabbitSender rabbitSender;

    @Mock
    private ConnectionFactory connectionFactory;

    @Mock
    private OutboundMessageResult outboundMessageResult;

    @Mock
    private OutboundMessage outboundMessage;

    @BeforeEach
    public void beforeMethod() {
        // Configurar comportamiento de rabbitConfig
        Mockito.when(rabbitConfig.buildSender(any())).thenReturn(sender);
        // Configurar comportamiento de sender.sendWithPublishConfirms
        Mockito.when(sender.sendWithPublishConfirms(any()))
                .thenReturn(Flux.just(outboundMessageResult));
    }



    @Test
    public void testSendWithConfirm() {

        outboundMessageResult = new OutboundMessageResult(outboundMessage, true);
        Mockito.when(rabbitConfig.buildSender(connectionFactory).sendWithPublishConfirms(any()))
                .thenReturn(Flux.fromIterable(Arrays.asList(outboundMessageResult)));

        Mono<Void> mensaje = rabbitSender.sendWithConfirm("Mensaje",
                "seguros.porchat.documentos",
                "seguros.porchat.documentos.exportado");

        StepVerifier.create(mensaje).verifyComplete();
    }


    @Test
    public void testSendWithConfirmError() {

        outboundMessageResult = new OutboundMessageResult(outboundMessage, false);
        Mockito.when(rabbitConfig.buildSender(connectionFactory).sendWithPublishConfirms(any())).thenReturn(Flux.just(outboundMessageResult));

        Mono<Void> mensaje = rabbitSender.sendWithConfirm("Mensaje",
                "seguros.porchat.documentos",
                "seguros.porchat.documentos.exportado");

        StepVerifier.create(mensaje).verifyError();
    }


}