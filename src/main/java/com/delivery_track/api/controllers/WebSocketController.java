package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.LocationDto;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/location")
    @SendTo("/topic/location")
    public LocationDto sendMessage(LocationDto locationDto){
        return new LocationDto(locationDto.deliveryId(), locationDto.lat(), locationDto.lng());
    }

}
