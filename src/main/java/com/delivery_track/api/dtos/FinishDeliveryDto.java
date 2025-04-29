package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

public record FinishDeliveryDto(
        @Schema(description = "Delivery ID (UUID)", example = "789add39-8b29-4673-b98a-3b3fa70b5c34")
        String deliveryId,
        @Schema(description = "User ID (UUID)", example = "f23dff60-77d8-4fea-b53e-2dc64705059c")
        String userId
) {
}
