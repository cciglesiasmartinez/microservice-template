package io.github.cciglesiasmartinez.microservice_template.application.usecases;

import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.GetItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;

public class GetItemUseCase {

    public Envelope<GetItemResponse> execute(GetItemRequest request) {
        return null;
    }

}
