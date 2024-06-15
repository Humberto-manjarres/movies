package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared.message;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.AMQP;
import org.reactivecommons.async.impl.RabbitMessage;
import org.reactivecommons.async.impl.communications.Message;
import org.reactivecommons.async.impl.exceptions.MessageConversionException;
import reactor.rabbitmq.OutboundMessage;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class MessageConfiguration {

    private ObjectMapper objectMapper;

    private static final String ENCODING = Charset.defaultCharset().name();
    private static final String CONTENT_TYPE = "application/json";

    public MessageConfiguration() {
        this.objectMapper= new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public <T> OutboundMessage toOutboundMessage(T object, String exchange, String routingKey) {
        final Message message = toMessage(object);
        final AMQP.BasicProperties props = buildMessageProperties(message,  Collections.emptyMap());
        return new OutboundMessage(exchange, routingKey, props, message.getBody());
    }

    protected Message toMessage(Object object) {
        byte[] bytes;
        try {
            String jsonString = this.objectMapper.writeValueAsString(object);
            bytes = jsonString.getBytes(ENCODING);
        }
        catch (IOException e) {
            throw new MessageConversionException("Failed to convert Message content", e);
        }
        RabbitMessage.RabbitMessageProperties props = new RabbitMessage.RabbitMessageProperties();
        props.setContentType(CONTENT_TYPE);
        props.setContentEncoding(ENCODING);
        props.setContentLength(bytes.length);
        return new RabbitMessage(bytes, props);
    }

    protected AMQP.BasicProperties buildMessageProperties(Message message, Map<String, Object> headers) {
        final Message.Properties properties = message.getProperties();
        final Map<String, Object> baseHeaders = new HashMap<>(properties.getHeaders());
        baseHeaders.putAll(headers);
        return new AMQP.BasicProperties.Builder()
                .contentType(properties.getContentType())
                .contentEncoding(properties.getContentEncoding())
                .deliveryMode(1)
                .timestamp(new Date())
                .messageId(UUID.randomUUID().toString())
                .headers(baseHeaders).build();
    }

}
