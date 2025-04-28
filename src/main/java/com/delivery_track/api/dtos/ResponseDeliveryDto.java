package com.delivery_track.api.dtos;

import java.util.Map;

public record ResponseDeliveryDto(String deliveryId, String userId, Map<String, String> link) {
}
