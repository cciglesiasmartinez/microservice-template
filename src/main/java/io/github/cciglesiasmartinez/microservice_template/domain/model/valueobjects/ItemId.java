package io.github.cciglesiasmartinez.microservice_template.domain.model.valueobjects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.cciglesiasmartinez.microservice_template.domain.model.Item;
import io.github.cciglesiasmartinez.microservice_template.domain.model.exception.WrongItemIdException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.UUID;

/**
 * Esta clase representa un value object, es decir, una propiedad de una entidad de dominio.
 * <p>
 * En este caso tenemos un identificador (ID) de Item. Al igual que con una entidad de dominio, declararemos el
 * constructor con métodos privado.
 * <p>
 * Además, por convención, estableceremos el atributo "value" que contendrá usualmente el valor del valueobject en
 * el tipo primitivo o genérico que corresponda. En este caso, contendrá un String que representará un UUIDv4 generado
 * por la librería java.util.UUID.
 * <p>
 * Los value objects son clases perfectas para contener verificaciones sobre las propiedades a las que referencian,
 * por ejemplo, verificar si pueden ser nulos o contener espacios en blanco, etcétera.
 * <p>
 * Además, emplearemos anotaciones como @JsonCreator o @JsonProperty tal y como hemos hecho en este caso cuando sea
 * necesario serializar en JSON el value object.
 * <p>
 * Por último, recalcar que por convención en el caso de los value objects el getter será .value() simplemente y no
 * getValue(), aunque en este caso concreto tendremos también el getter getValue() que será empleado por las librerías
 * que serializan JSON.
 */
@EqualsAndHashCode
@ToString
public class ItemId {

    private final String value;

    /**
     * Constructor privado.
     * @param value
     */
    @JsonCreator
    private ItemId(@JsonProperty("value") String value) {
        this.value = value;
    }

    /**
     * Método factoría. Este método será empleado para generar por primera vez un identificador.
     * @return
     */
    public static ItemId generate() {
        return new ItemId(UUID.randomUUID().toString());
    }

    /**
     * Método factoría. Este método será empleado para crear un identificador. Este método será empleado normalmente
     * cuando recibamos un identificador existente por ejemplo de la base de datos y necesitemos reconstruir el
     * objeto en el sistema.
     * <p>
     * Entender la diferencia entre cuando usemos ItemId.generate() e ItemId.of() es importante.
     * @param id
     * @return
     */
    public static ItemId of(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new WrongItemIdException("User ID cannot be null or empty.");
        }
        try {
            UUID.fromString(id.trim());
        } catch (IllegalArgumentException e ) {
            throw new WrongItemIdException("Invalid UUID format.");
        }
        return new ItemId(id.trim());
    }


    /**
     * Getter convencional para value objects (nótese que no empieza por "get").
     * @return
     */
    public String value() {
        return value;
    }

    /**
     * Este getter es necesario porque es empleado por defecto por librerías de serialización.
     * @return
     */
    public String getValue() {
        return value;
    }

}
