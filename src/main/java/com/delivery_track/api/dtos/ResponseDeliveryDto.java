package com.delivery_track.api.dtos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record ResponseDeliveryDto(
        @Schema(description = "Delivery ID (UUID)", example = "f23dff60-77d8-4fea-b53e-2dc64705059c")
        String deliveryId,
        @Schema(description = "User ID (UUID)", example = "f23dff60-77d8-4fea-b53e-2dc64705059c")
        String userId,
        @Schema(description = "Link to track delivery", example = "http://192.168.152.254:8080/api/track/f23dff60-77d8-4fea-b53e-2dc64705059c")
        Map<String, String> link
) {}
