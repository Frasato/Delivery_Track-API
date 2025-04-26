package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.LocationDto;
import com.delivery_track.api.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @Autowired
    private DeliveryService deliveryService;

    @MessageMapping("/location")
    @SendTo("/topic/location")
    public LocationDto sendMessage(LocationDto locationDto){
        deliveryService.updateLocation(locationDto.deliveryId(), locationDto.lat(), locationDto.lng());
        return locationDto;
    }

}
