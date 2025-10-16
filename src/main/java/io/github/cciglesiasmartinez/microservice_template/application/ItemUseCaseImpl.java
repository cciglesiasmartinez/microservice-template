package io.github.cciglesiasmartinez.microservice_template.application;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.CreateItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.GetItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Esta clase es un coordinador/orquestador de casos de uso. Implementa la interfaz {@link ItemUseCase} que contendrá
 * todos los casos de uso.
 * <p>
 * Desde esta clase se llamará a cada caso de uso particular, que tendrá un método execute() que llevará a cabo las
 * operaciones necesarias para el caso.
 */
@Service
@AllArgsConstructor
public class ItemUseCaseImpl implements ItemUseCase {

    private final GetItemUseCase getItemUseCase;
    private final CreateItemUseCase createItemUseCase;

    @Override
    public Envelope<GetItemResponse> getItem(String id) {
        return getItemUseCase.execute(id);
    }

    @Override
    public Envelope<CreateItemResponse> createItem(CreateItemRequest request) {
        return createItemUseCase.execute(request);
    }

}
