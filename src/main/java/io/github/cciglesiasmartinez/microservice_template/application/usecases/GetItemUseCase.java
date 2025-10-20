package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongItemIdException;
import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Caso de uso encargado de recuperar un {@link Item} por su identificador único.
 * <p>
 * Esta clase actúa como orquestador del flujo de negocio específico de “obtener un item”. Consulta el {@link
 * ItemRepository} para localizar el {@link Item} correspondiente y devuelve un {@link Envelope} con la información
 * del item y metadatos de la petición.
 * <p>
 * Se integra con el sistema de logging a través de {@link lombok.extern.slf4j.Slf4j} y asegura trazabilidad mediante
 * {@link Meta}, incluyendo el requestId y timestamp.
 * <p>
 * En caso de que el {@link Item} no exista, lanza una {@link WrongItemIdException} que será
 * capturada por el GlobalExceptionHandler.
 */
@AllArgsConstructor
@Service
@Slf4j
public class GetItemUseCase {

    private ItemRepository itemRepository;

    /**
     * Ejecuta la operación de recuperación de un {@link Item}.
     *
     * @param id Identificador único del item a recuperar.
     * @return Un {@link Envelope} que contiene un {@link GetItemResponse} con los datos del item
     *         y un {@link Meta} con la metadata de la petición.
     * @throws WrongItemIdException Si no se encuentra un item con el {@code id} proporcionado.
     */
    public Envelope<GetItemResponse> execute(String id) {
        ItemId itemId = ItemId.of(id);
        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new WrongItemIdException("Item ID not found");
        });
        GetItemResponse data = new GetItemResponse(item.itemId().value(), item.name().value());
        Meta meta = new Meta();
        log.info("Item {} successfully retrieved.", itemId.value());
        return new Envelope<>(data, meta);
    }

}
