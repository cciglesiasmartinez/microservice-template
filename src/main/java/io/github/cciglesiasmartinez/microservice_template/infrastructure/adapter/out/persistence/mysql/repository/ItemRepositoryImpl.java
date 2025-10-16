package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.repository;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Name;
import io.github.cciglesiasmartinez.microservice_template.domain.port.out.ItemRepository;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper.ItemEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Aquí haremos la implementación del repositorio, implementaremos la interfaz ItemRepository e inyectaremos la
 * interfaz (con implementación) SpringDataItemRepository que está previamente anotada con @Repository.
 * <p>
 * Esta implementación además hará uso del mapper {@link ItemEntityMapper}, puesto que necesitamos convertir los
 * objetos de dominio con los que trabajaremos en objetos compatibles con persistencia JPA.
 */
@Repository
@AllArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

    private final SpringDataItemRepository springDataItemRepository;
    private final ItemEntityMapper mapper;

    @Override
    public Optional<Item> findById(ItemId id) {
        Item item = mapper.toDomain(springDataItemRepository.findById(id.value()).orElse(null));
        return Optional.of(item);
    }

    @Override
    public void save(Item item) {
        springDataItemRepository.save(mapper.toEntity(item));
    }

    @Override
    public void delete(Item item) {
        springDataItemRepository.delete(mapper.toEntity(item));
    }

    @Override
    public boolean existsByName(Name name) {
        return springDataItemRepository.existsByName(name.value());
    }
}
