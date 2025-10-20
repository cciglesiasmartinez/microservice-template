package io.github.cciglesiasmartinez.microservice_template.domain.event;

import lombok.Getter;
import org.slf4j.MDC;

/**
 * Esto queda como un "placeholder" para los eventos de dominio. Será interesante debatir en el futuro qué puede
 * incluirse en esta interfaz con el objeto de enriquecer los eventos de alguna manera estándar.
 */
@Getter
public abstract class DomainEvent {

    private String requestId;
    private String eventType;

    public DomainEvent(String eventType) {
        this.requestId = MDC.get("requestId");
        this.eventType = eventType;
    }

}
