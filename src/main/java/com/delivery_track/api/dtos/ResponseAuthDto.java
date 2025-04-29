package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseAuthDto(
        @Schema(description = "User ID (UUID)", example = "f23dff60-77d8-4fea-b53e-2dc64705059c")
        String id,
        @Schema(description = "Username", example = "gabriel")
        String name,
        @Schema(description = "User email", example = "gabriel@gmail.com")
        String email,
        @Schema(description = "Authenticate Token", example = "Bearer sdfsonngpASODNPSO.spdnfosdnfsondsodnfsonf")
        String token) {
}
