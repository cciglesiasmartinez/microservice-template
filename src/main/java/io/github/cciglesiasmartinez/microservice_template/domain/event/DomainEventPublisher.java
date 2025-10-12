package io.github.cciglesiasmartinez.microservice_template.domain.event;

/**
 * Interfaz para el publicador de eventos de dominio. En principio constará de un solo método.
 */
public interface DomainEventPublisher {

    void publish(Object event);

}
