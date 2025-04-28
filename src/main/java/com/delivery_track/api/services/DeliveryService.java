package com.delivery_track.api.services;

import com.delivery_track.api.dtos.ResponseDeliveryDto;
import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.models.User;
import com.delivery_track.api.repositories.DeliveryRepository;
import com.delivery_track.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> initDelivery(String userId){
        try{
            Optional<User> findeduser = userRepository.findById(userId);

            if(findeduser.isEmpty()){
                return ResponseEntity.badRequest().body("ERROR: User doesn't exist!");
            }

            Delivery delivery = new Delivery();
            delivery.setLat(0);
            delivery.setLng(0);
            delivery.setInit(Instant.now());
            delivery.setUser(findeduser.get());

            Delivery savedDelivery = deliveryRepository.save(delivery);
            String link = "https://localhost:8080/api/delivery/track/public/" + savedDelivery.getId();

            Map<String, String> response = new HashMap<>();
            response.put("link", link);

            return ResponseEntity.status(201).body(new ResponseDeliveryDto(delivery.getId(), userId, response));

        }catch(Exception e){
            return ResponseEntity.internalServerError().body("ERROR: " + e);
        }
    }

    public void updateLocation(String deliveryId, double lat, double lng){
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new RuntimeException("Delivery not found!"));
        delivery.setLat(lat);
        delivery.setLng(lng);
        deliveryRepository.save(delivery);
    }

    public ResponseEntity<?> finishDelivery(String deliveryId, String userId){
        try{
            Optional<User> findedUser = userRepository.findById(userId);
            Optional<Delivery> findedDelivery = deliveryRepository.findById(deliveryId);

            if(findedDelivery.isEmpty() || findedUser.isEmpty()){
                return ResponseEntity.badRequest().body("ERROR: Delivery or User doesn't exist!");
            }

            Delivery delivery = findedDelivery.get();
            delivery.setFinish(Instant.now());
            deliveryRepository.save(delivery);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Delivery finished successfully.");
            return ResponseEntity.status(201).body(response);

        }catch(Exception e){
            return ResponseEntity.internalServerError().body("ERROR: " + e);
        }

    }

}
