package com.ocmotta.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public record ResponseDto(
        @Schema(
                description = "Status code of the response", example = "200"
        )
        String statusCode,

        @Schema(
                description = "Status message of the response", example = "Request processed successfully"
        )
        String statusMsg
) {
}
