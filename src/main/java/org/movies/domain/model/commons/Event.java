package org.movies.domain.model.commons;

public interface Event {
    String exchange();
    String routingKey();
    Object data();
}
