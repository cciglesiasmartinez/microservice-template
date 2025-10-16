package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Name;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;
import org.springframework.stereotype.Component;

/**
 * Implementación de la interfaz {@link ItemEntityMapper}. Se anotará como {@code @Component}.
 */
@Component
public class ItemEntityMapperImpl implements ItemEntityMapper {

    @Override
    public Item toDomain(ItemEntity entity) {
        return Item.of(ItemId.of(entity.getId()), Name.of(entity.getName()));
    }

    @Override
    public ItemEntity toEntity(Item item) {
        return new ItemEntity(item.itemId().value(), item.name().value());
    }
}
