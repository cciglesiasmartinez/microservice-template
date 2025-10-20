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

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

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

    @ExceptionHandler(WrongItemIdException.class)
    public ResponseEntity<?> handleWrongItemIdException(WrongItemIdException e) {
        return buildResponse(e, "wrong_item_id", HttpStatus.NOT_FOUND);
    }

}
