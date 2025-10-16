package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.domain.event.DomainEventPublisher;
import io.github.cciglesiasmartinez.microservice_template.domain.event.ItemCreatedEvent;
import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Name;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Esta clase se encarga del caso de uso para crear un item. Nótese que inyectamos el {@link ItemRepository}
 * y que anotamos la clase con {@code @Service} y {@code @Slf4j}. Esta última anotación nos aportará la capacidad de
 * logging.
 * <p>
 * Además, en este caso de uso inyectaremos también un {@link DomainEventPublisher} que publicará un evento dentro
 * de nuestra aplicación, ejecutado en otro hilo de ejecución independiente a la petición, que será recogido por un
 * handler también en nuestra aplicación, esto servirá para desacoplar el flujo de ejecución de la petición del flujo
 * de ejecución del evento. Posteriormente, el evento será recogido en un handler también en nuestra propia aplicación,
 * el cual enviará un mensaje mediante el MessageBroker (Apache Kafka) notificando el evento.
 */
@AllArgsConstructor // Necesitamos el constructor para inyectar correctamente itemRepository y domainEventPublisher.
@Service
@Slf4j // Empleamos esta anotación para enviar posteriormente log.info()
public class CreateItemUseCase {

    private ItemRepository itemRepository;
    private DomainEventPublisher domainEventPublisher;

    /**
     * Ejecuta el caso de uso.
     * <p>
     * Es de especial interés el uso de Item.create() en vez de Item.of(), dado que estamos creando un objeto nuevo
     * por lo que necesitaremos generar un id, cosa que hace Item.create().
     *
     * @param request el DTO de petición de tipo {@link CreateItemRequest}.
     * @return un objeto de tipo {@link Envelope} conteniendo el correspondiente DTO de respuesta.
     */
    public Envelope<CreateItemResponse> execute(CreateItemRequest request) {
        Name name = Name.of(request.getName());
        Item item = Item.create(name); // Usamos create() para crear un nuevo Item en vez de of().
        itemRepository.save(item);
        CreateItemResponse data = new CreateItemResponse(item.itemId().value(), item.name().value());
        Meta meta = new Meta();
        ItemCreatedEvent event = new ItemCreatedEvent(item.itemId());
        domainEventPublisher.publish(event);
        log.info("Item {} created successfully.", item.itemId());
        return new Envelope(data, meta);
    }

}
