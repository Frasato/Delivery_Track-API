package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginDto(
        @Schema(description = "User email", example = "gabriel@gmail.com")
        String email,
        @Schema(description = "User password", example = "gabriel123")
        String password) {
}
