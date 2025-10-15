package io.github.cciglesiasmartinez.microservice_template.application;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.application.usecases.GetItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.GetItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ItemUseCaseImpl implements ItemUseCase {

    private final GetItemUseCase getItemUseCase;

    @Override
    public Envelope<GetItemResponse> getItem(GetItemRequest request) {
        return null;
    }

}
