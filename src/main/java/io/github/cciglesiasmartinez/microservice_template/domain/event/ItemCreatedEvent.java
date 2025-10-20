package io.github.cciglesiasmartinez.microservice_template.domain.event;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Esta clase representa un evento en el sistema, principalmente a nivel interno.
 * <p>
 * Será interesante discutir si un evento a nivel interno deberá coincidir en forma y tipo con el evento externo.
 * Es decir, ¿debe un evento propagado por Kafka tener el mismo formato y datos que el evento propagado internamente
 * en la aplicación? Esto será un buen punto de consideración futura. Por el momento lo haremos así, ya habrá ocasión
 * de complicar más las cosas.
 * <p>
 * En este caso particular recogemos el itemId del Item, pues será el único dato que propagaremos. No obstante, en
 * otros casos podrá ser interesante propagar más datos.
 * <p>
 * Además, podría ser interesante también propagar otros eventos, como cuando un Item se actualice o se elimine.
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Setter
@Getter
public class ItemCreatedEvent extends DomainEvent {

    private final ItemId itemId;
    private final LocalDateTime occurredOn;

    public ItemCreatedEvent(ItemId itemId) {
        super("ItemCreatedEvent");
        this.itemId = itemId;
        this.occurredOn = LocalDateTime.now();
    }

}
