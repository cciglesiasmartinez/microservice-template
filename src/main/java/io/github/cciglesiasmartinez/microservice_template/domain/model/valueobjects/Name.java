package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Esta clase representa un value object, es decir, una propiedad de una entidad de dominio.
 * <p>
 * Referirse a la documentación de la clase {@link ItemId} para ver las pautas generales de elaboración de un value
 * object.
 */
@EqualsAndHashCode
@ToString
public class Name {

    private final String value;

    @JsonCreator
    private Name(@JsonProperty("value") String value) {
        this.value = value;
    }

    public static Name of(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name can't be null or empty.");
        }
        return new Name(name);
    }

    public String value() {
        return this.value;
    }

    public String getValue() {
        return this.value;
    }

}
