package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.LocationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/location")
    @Operation(
            summary = "Websocket",
            description = "Send and get location"
    )
    @SecurityRequirement(name = "bearerAuth")
    public void sendMessage(LocationDto locationDto){
        logger.info("Lat: " + locationDto.lat() + "Lng: " + locationDto.lng());
        String destination = "/topic/location/" + locationDto.deliveryId();
        simpMessagingTemplate.convertAndSend(destination, locationDto);
    }

}
