package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.exception;

import io.github.cciglesiasmartinez.microservice_template.domain.exception.WrongItemIdException;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.ExceptionResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Meta;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Esta clase va anotada con {@code ControllerAdvice} y cada uno de los "handler methods" irá anotado con {@code
 * ExceptionHandler} indicando la clase de excepciones que manejará.
 * <p>
 * Esta clase constituye un punto central de gestión de excepciones. Aquí se capturan las excepciones y se manejan,
 * es decir, enviamos un mensaje de logging y acto seguido lanzamos una {@link ResponseEntity} con el código HTTP
 * correspondiente, así como un mensaje corto (una suerte de código) reflejando el error.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Construye una {@link ResponseEntity} estándar para el envío de excepciones en la API.
     * <p>
     * Este método centraliza la creación de respuestas de error, incluyendo logging y metadatos como el tipo de
     * excepción y el origen en el código. La respuesta incluye un código de error amigable y un objeto {@link Meta}
     * con información adicional de la petición.
     *
     * @param e La excepción capturada que queremos convertir en respuesta HTTP.
     * @param code Código corto que representa el tipo de error para el cliente.
     * @param type Código de estado HTTP que se devolverá.
     * @return Un objeto {@link ResponseEntity} con la envoltura {@link Envelope} que contiene
     *         el {@link ExceptionResponse} y los metadatos {@link Meta}.
     */
    private ResponseEntity<Envelope<ExceptionResponse>> buildResponse(Exception e, String code, HttpStatus type) {
        StackTraceElement origin = e.getStackTrace()[0];
        MDC.put("exceptionType", e.getClass().getSimpleName());
        MDC.put("exceptionOrigin", origin.getClassName() + ":" + origin.getLineNumber());
        log.warn(e.getMessage());
        MDC.remove("exceptionOrigin");
        MDC.remove("exceptionType");
        ExceptionResponse exceptionResponse = new ExceptionResponse(code);
        Envelope<ExceptionResponse> response = new Envelope<>(exceptionResponse, new Meta());
        return new ResponseEntity<>(response, type);
    }


    /**
     * Captura y maneja las excepciones de tipo {@link WrongItemIdException} que puedan producirse en cualquier
     * controlador de la aplicación.
     * <p>
     * Al interceptar esta excepción, se envía una respuesta HTTP 404 NOT FOUND con un código de error amigable para
     * el cliente, mientras que se registra el evento en los logs con información contextual para facilitar
     * el debugging.
     *
     * @param e La excepción de tipo {@link WrongItemIdException} capturada.
     * @return Un {@link ResponseEntity} que contiene un {@link Envelope} con {@link ExceptionResponse}
     *         y {@link Meta} indicando los metadatos de la petición.
     */
    @ExceptionHandler(WrongItemIdException.class)
    public ResponseEntity<?> handleWrongItemIdException(WrongItemIdException e) {
        return buildResponse(e, "wrong_item_id", HttpStatus.NOT_FOUND);
    }

}
