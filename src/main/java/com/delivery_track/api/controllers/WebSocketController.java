package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/location")
    @SendTo("/topic/location")
    @Operation(
            summary = "Websocket",
            description = "Send and get location"
    )
    @SecurityRequirement(name = "bearerAuth")
    public LocationDto sendMessage(LocationDto locationDto){
        return new LocationDto(locationDto.deliveryId(), locationDto.lat(), locationDto.lng());
    }

}
