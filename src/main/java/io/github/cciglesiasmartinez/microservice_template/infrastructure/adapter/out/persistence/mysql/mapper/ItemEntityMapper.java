package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.mapper;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.out.persistence.mysql.entity.ItemEntity;

/**
 * Interfaz para el mapper.
 */
public interface ItemEntityMapper {

    /**
     * Este método mapeará objetos de entidad JPA (MySQL) extraídos de un repositorio a entidades de dominio.
     *
     * @param entity El {@link ItemEntity} extraído del repositorio.
     * @return El {@link Item} de dominio
     */
    Item toDomain(ItemEntity entity);

    /**
     * Este método mapeará objetos de dominio a su correspondiente clase de persistencia.
     *
     * @param item El {@link Item} de dominio, normalmente traído de la capa de aplicación.
     * @return El {@link ItemEntity} preparado para ser persistido en el repositorio.
     */
    ItemEntity toEntity(Item item);

}
