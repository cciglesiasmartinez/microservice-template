package io.github.cciglesiasmartinez.microservice_template.domain.model;

import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.ItemId;
import io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects.Name;

/**
 * Representa un Item dentro del sistema.
 * <p>
 * Esta clase representa una entidad de dominio, es decir, un objeto dentro del dominio con sus comportamientos y
 * restricciones, sujeto a unas reglas de negocio concretas.
 * <p>
 * Siempre que sea posible y conveniente, los atributos de una entidad de dominio no serán tipos primitivos ni clases
 * genéricas como String, sino que serán "value objects", es decir, atributos representados por clases personalizadas
 * que recogerán aspectos importantes sobre las propiedades de la entidad.
 * <p>
 * Los getter para las entidades de dominio por convención no empezarán por "get", sino que se llamarán directamente
 * como el atributo al que referencian.
 */
public class Item {

    private final ItemId id;
    private Name name;

    /**
     * El constructor de este tipo de objetos será privado (private), puesto que queremos instanciar las entidades
     * de dominio con métodos factoría específicos que normalmente representarán eventos o acciones en el sistema
     * relacionadas con la lógica de negocio.
     *
     * @param id
     * @param name
     */
    private Item(ItemId id, Name name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Este método factoría por ejemplo será el empleado cuando demos de alta un Item por primera vez.
     *
     * @param name
     * @return
     */
    public static Item create(Name name) {
        return new Item(ItemId.generate(), name);
    }

    /**
     * Este método factoría será el empleado por ejemplo cuando reconstruyamos un objeto Item desde una instancia
     * de este objeto traída directamente del repositorio.
     * @return
     */
    public static Item of(ItemId itemId, Name name) {
        return new Item(itemId, name);
    }

    public ItemId itemId() {
        return this.id;
    }

    public Name name() {
        return this.name;
    }

}
