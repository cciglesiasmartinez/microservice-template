package io.github.cciglesiasmartinez.microservice_template.infrastructure.adapter.in.web.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "Response after creating a new item.")
public class GetItemResponse {

    @Schema(
            description = "Item identifier (UUIDv4).",
            example = "57e9fc95-2a66-40e7-8fd3-debd19a661e3"
    )
    private String id;

    @Schema(
            description = "Item name.",
            example = "ThingyThing"
    )
    private String name;

}
