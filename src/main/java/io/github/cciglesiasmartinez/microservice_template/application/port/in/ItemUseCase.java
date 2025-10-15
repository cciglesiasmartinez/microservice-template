package io.github.cciglesiasmartinez.microservice_template.application.port.in;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.GetItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;

/**
 * Esta interfaz será un puerto de entrada de aplicación. Definirá los casos de uso que manejará nuestra aplicación.
 * Quizá pueda resultar un poco redundante o innecesaria, ya que normalmente haremos una implementación que no
 * cambiaremos, pero bueno, creo que no está de más definirla.
 * <p>
 * No obstante, estoy abierta a consideraciones y discusión sobre si prescindir de ella o no.
 */
public interface ItemUseCase {

    Envelope<GetItemResponse> getItem(GetItemRequest request);

}
