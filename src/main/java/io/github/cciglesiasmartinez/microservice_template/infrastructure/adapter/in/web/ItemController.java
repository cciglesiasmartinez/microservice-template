package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web;

import io.github.cciglesiasmartinez.microservice_template.application.port.in.ItemUseCase;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.requests.CreateItemRequest;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.CreateItemResponse;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.Envelope;
import io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses.GetItemResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Este será el controlador REST. Aquí irán las anotaciones de OpenAPI/Swagger además de la llamada a la interfaz
 * {@link ItemUseCase} que residirá en la capa de aplicación.
 * <p>
 * Es conveniente notar que esta capa no interactúa directamente con otras partes de la infraestructura, como los
 * repositorios MySQL; eso se llevará a cabo en la capa de aplicación (o sea, en los use cases).
 */
@AllArgsConstructor
@RestController
@RequestMapping("/items")
// La etiqueta @Tag será empleada para anotar el nombre del servicio y dar una pequeña descripción para Swagger.
@Tag(
        name = "Item service.",
        description = "Endpoints related to Item entity. CRUD operations."
)
public class ItemController {

    private ItemUseCase itemUseCase;

    /**
     * Ruta del API REST.
     * <p>
     * Debe ir anotada con la correspondiente información para Swagger (anotaciones {@code @Operation} y
     * {@code ApiResponses}).
     *
     * @param id el ID del item a consultar.
     * @return un objeto de tipo {@link ResponseEntity} con el DTO correspondiente.
     */
    @Operation(
            summary = "Retrieves an item.",
            description = "Retrieves the information available for an item given its id."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item retrieved successfully.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Envelope<GetItemResponse>> getItem(@PathVariable String id) {
        Envelope<GetItemResponse> response = itemUseCase.getItem(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Ruta del API REST.
     * <p>
     * Debe ir anotada con la correspondiente información para Swagger (anotaciones {@code @Operation} y
     * {@code ApiResponses}).
     *
     * @param request el DTO para el caso de uso correspondiente ({@link CreateItemRequest}).
     * @return un objeto de tipo {@link ResponseEntity} con el DTO correspondiente.
     */
    @Operation(
            summary = "Creates a new item.",
            description = "Creates a new item given its name."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item created successfully.")
    })
    @PostMapping("/")
    public ResponseEntity<Envelope<CreateItemResponse>> createItem(@RequestBody CreateItemRequest request) {
        Envelope<CreateItemResponse> response = itemUseCase.createItem(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
