package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.GetItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping("/item")
@Tag(
        name = "Item service.",
        description = "Endpoints related to Item entity. CRUD operations."
)
public class ItemController {

    private ItemUseCase itemUseCase;

    @GetMapping("/{id}")
    public ResponseEntity<Envelope<GetItemResponse>> getItem(@Valid @RequestBody GetItemRequest request) {
        Envelope<GetItemResponse> response = itemUseCase.getItem(request);
        return null;
    }

}
