package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthDto(
        @Schema(description = "Username", example = "Gabriel")
        String name,
        @Schema(description = "User email", example = "gabriel@gmail.com")
        String email,
        @Schema(description = "User password", example = "gabriel123")
        String password
) {
}
