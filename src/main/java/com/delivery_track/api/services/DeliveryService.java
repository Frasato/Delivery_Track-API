package com.delivery_track.api.services;

import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.repositories.DeliveryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    public void updateLocation(String deliveryId, double lat, double lng){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found!"));
        delivery.setLat(lat);
        delivery.setLng(lng);
        deliveryRepository.save(delivery);
    }

}
