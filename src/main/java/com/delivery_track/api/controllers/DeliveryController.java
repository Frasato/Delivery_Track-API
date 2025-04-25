package com.delivery_track.api.controllers;

import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/delivery/track")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @GetMapping("/public/{id}")
    public ResponseEntity<Delivery> getDeliveryLocation(@PathVariable String id){
        return ResponseEntity.ok(deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR")));
    }

}
