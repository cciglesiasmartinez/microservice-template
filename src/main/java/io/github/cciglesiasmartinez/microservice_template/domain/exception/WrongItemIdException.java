package io.github.cciglesiasmartinez.microservice_template.domain.exception;

/**
 * Esta clase representará una excepción personalizada básica.
 * <p>
 * Deberá tener un atributo privado estático constante de tipo String llamado DEFAULT_MESSAGE que será un mensaje
 * por defecto genérico de la excepción.
 * <p>
 * Además, tendrá dos constructores. Un constructor "por defecto" sin argumentos que pasará a la clase padre este
 * DEFAULT_MESSAGE, y un constructor con un argumento String que será precisamente un mensaje más concreto en caso
 * de necesitar un aviso más descriptivo que aporte información relevante al caso.
 * <p>
 * Todas las excepciones personalizadas deberán seguir este formato. Serà menester crear una nueva excepción siempre
 * que sea oportuno (lógica de negocio, etc), y evitar usar excepciones genéricas como RunTimeException.
 */
public class WrongItemIdException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Wrong ItemId.";

    public WrongItemIdException() {
        super(DEFAULT_MESSAGE);
    }

    public WrongItemIdException(String message) {
        super(message);
    }

}
