package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.LocationDto;
import com.delivery_track.api.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin("*")
public class WebSocketController {

    @Autowired
    private DeliveryService deliveryService;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/location")
    public void sendMessage(LocationDto locationDto){
        deliveryService.updateLocation(locationDto.deliveryId(), locationDto.lat(), locationDto.lng());
        simpMessagingTemplate.convertAndSend("/topic/location/" + locationDto.deliveryId(), locationDto);
    }

}
