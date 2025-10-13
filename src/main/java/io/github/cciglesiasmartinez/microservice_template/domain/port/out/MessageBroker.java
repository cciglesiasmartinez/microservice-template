package io.github.cciglesiasmartinez.microservice_template.domain.port.out;

/**
 * Esta será la interfaz que emplearemos para comunicarnos con nuestro bróker de mensajes entre microservicios.
 * <p>
 * Para nuestro caso, esta interfaz tendrá una implementación con Apache Kafka.
 * <p>
 * Para mantener la arquitectura hexágonal (puertos y adaptadores), creamos esta interfaz que implementaremos en
 * infraestructura.
 */
public interface MessageBroker {

    void sendMessage(String message);

}
