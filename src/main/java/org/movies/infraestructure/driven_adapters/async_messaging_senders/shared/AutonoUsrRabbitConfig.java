package org.movies.infraestructure.driven_adapters.async_messaging_senders.shared;

import com.rabbitmq.client.Address;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.scheduler.Schedulers;
import reactor.rabbitmq.*;

@Configuration
public class AutonoUsrRabbitConfig {

    @Value("${msgbroker.host}")
    private String host;
    @Value("${msgbroker.port}")
    private String port;
    @Value("${msgbroker.username}")
    private String username;
    @Value("${msgbroker.password}")
    private String password;
    @Value("${msgbroker.virtualHost}")
    private String virtualHost;

    private static final String CLIENT_PROVIDED_NAME = "receiver";
    private static final String CLIENT_SENDER_NAME = "sender";

    @Bean
    public ConnectionFactory buildConnection() {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.useNio();
        connectionFactory.setHost(this.host);
        connectionFactory.setUsername(this.username);
        connectionFactory.setPassword(this.password);
        connectionFactory.setVirtualHost(this.virtualHost);

        return connectionFactory;
    }

    @Bean
    public Receiver buildReceiver(ConnectionFactory connectionFactory) {

        Address[] addresses = {new Address(this.host)};

        ReceiverOptions receiverOptions = new ReceiverOptions()
                .connectionFactory(connectionFactory)
                .connectionSupplier(cf -> cf.newConnection(addresses, CLIENT_PROVIDED_NAME))
                .connectionSubscriptionScheduler(Schedulers.elastic());

        return RabbitFlux.createReceiver(receiverOptions);
    }

    @Bean
    public Sender buildSender(ConnectionFactory connectionFactory) {

        Address[] addresses = {new Address(this.host)};

        SenderOptions receiverOptions = new SenderOptions()
                .connectionFactory(connectionFactory)
                .connectionSupplier(cf -> cf.newConnection(addresses, CLIENT_SENDER_NAME))
                .connectionSubscriptionScheduler(Schedulers.boundedElastic());

        return RabbitFlux.createSender(receiverOptions);
    }

}
