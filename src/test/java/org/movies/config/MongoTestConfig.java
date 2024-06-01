package org.movies.config;

import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;

@TestConfiguration
public class MongoTestConfig {

    @Value("${spring.data.mongodb.uri}")
    private String mongoUri;

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    @Bean
    public ReactiveMongoDatabaseFactory reactiveMongoDatabaseFactory() {
        //return new SimpleReactiveMongoDatabaseFactory(MongoClients.create(mongoUri), databaseName);
        return new SimpleReactiveMongoDatabaseFactory(MongoClients.create("mongodb://localhost:27017/movies"), "movies");
    }

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(reactiveMongoDatabaseFactory());
    }

}
