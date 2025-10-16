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

@AllArgsConstructor
@Service
@Slf4j
public class GetItemUseCase {

    private ItemRepository itemRepository;

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
