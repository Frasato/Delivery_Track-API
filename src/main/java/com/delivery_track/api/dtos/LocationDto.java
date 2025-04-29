package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record LocationDto(
        @Schema(description = "Delivery ID (UUID)", example = "789add39-8b29-4673-b98a-3b3fa70b5c34")
        String deliveryId,
        @Schema(description = "Latitude value", example = "21.5433")
        double lat,
        @Schema(description = "Longitude value", example = "20.5619")
        double lng) {
}
