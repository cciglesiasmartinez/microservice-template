package io.github.cciglesiasmartinez.microservice_template.model.port.out;

import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Name;

import java.util.Optional;

/**
 * Cada entidad de dominio tendrá una interfaz repository propia. Aquí usaremos las entidades de dominio con sus
 * value objects.
 * <p>
 * Esta interfaz será distinta de la interfaz y la clase repositorio que residirán en el paquete de infraestructura.
 * Esta clase está pensada como un "puerto" (arquitectura Ports and Adapters) de salida mediante el cual interactuará
 * el dominio a través de la aplicación para comunicarse con el "adaptador" que contendrá la implementación de la base
 * de datos.
 * <p>
 * En este ejemplo se adjuntan algunos de los métodos más usuales (CRUD) que implementará una interfaz de repositorio.
 */
public interface ItemRepository {

    Optional<Item> findById(ItemId id);
    void save(Item item);
    void delete(Item item);
    boolean existsByName(Name name);

}
