package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.events;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.movies.domain.model.commons.Event;
import org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.sender.RabbitSender;
import reactor.core.publisher.Mono;

@ExtendWith(MockitoExtension.class)
class ReactiveEventsGatewayTest {

    @InjectMocks
    private ReactiveEventsGateway eventsGateway;

    @Mock
    private RabbitSender rabbitSender;

    @Test
    public void emitTest(){
        Mockito.when(rabbitSender.sendWithConfirm(Mockito.any(),Mockito.any(),Mockito.any())).thenReturn(Mono.empty());
        eventsGateway.emit(new MyEvent("1","1"));
        Mockito.verify(rabbitSender).sendWithConfirm(Mockito.any(),Mockito.any(),Mockito.any());
    }


    private static class MyEvent implements Event{
        private String data1;
        private String data2;

        public MyEvent(String data1, String data2) {
            this.data1 = data1;
            this.data2 = data2;
        }

        @Override
        public String exchange() {
            return null;
        }

        @Override
        public String routingKey() {
            return null;
        }

        @Override
        public Object data() {
            return null;
        }

    }

}