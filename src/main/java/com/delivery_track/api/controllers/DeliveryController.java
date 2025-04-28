package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.FinishDeliveryDto;
import com.delivery_track.api.dtos.LocationDto;
import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.repositories.DeliveryRepository;
import com.delivery_track.api.services.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery/track")
@CrossOrigin("*")
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/init/{id}")
    public ResponseEntity<?> initDelivery(@PathVariable String id){
        return deliveryService.initDelivery(id);
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<LocationDto> getDeliveryLocation(@PathVariable String id){
        Delivery delivery = deliveryRepository.findById(id).orElseThrow(() -> new RuntimeException("ERROR"));
        LocationDto locationDto = new LocationDto(id, delivery.getLat(), delivery.getLng());
        return ResponseEntity.ok(locationDto);
    }

    @PostMapping("/finish")
    public ResponseEntity<?> finishDelivery(@RequestBody FinishDeliveryDto finish){
        return deliveryService.finishDelivery(finish.deliveryId(), finish.userId());
    }

}
