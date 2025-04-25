package com.delivery_track.api.controllers;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/location")
    @SendTo("/topic/location")
    public Message sendMessage(Message message){
        return message;
    }

}
