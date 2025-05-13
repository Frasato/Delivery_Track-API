package com.delivery_track.api.services;

import com.delivery_track.api.dtos.ResponseDeliveryDto;
import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.models.Finished;
import com.delivery_track.api.models.User;
import com.delivery_track.api.repositories.DeliveryRepository;
import com.delivery_track.api.repositories.FinishedRepository;
import com.delivery_track.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class DeliveryService {

    private static final Logger logger = LoggerFactory.getLogger(DeliveryService.class);
    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FinishedRepository finishedRepository;

    public ResponseEntity<?> initDelivery(String userId, int orders){
        logger.info("Starting the delivery for user {}", userId);
        try{
            Optional<User> findeduser = userRepository.findById(userId);

            if(findeduser.isEmpty()){
                logger.warn("User with id {} not found", userId);
                return ResponseEntity.status(400).body("ERROR: User doesn't exist!");
            }

            Delivery delivery = new Delivery();
            delivery.setLat(0);
            delivery.setLng(0);
            delivery.setInit(Instant.now());
            delivery.setUser(findeduser.get());
            delivery.setOrders(orders);

            Delivery savedDelivery = deliveryRepository.save(delivery);
            String link = "https://localhost:8080/api/delivery/track/public/" + savedDelivery.getId();

            logger.info("Delivery start success to user {} and delivery {}", userId, savedDelivery.getId());

            Map<String, String> response = new HashMap<>();
            response.put("link", link);

            return ResponseEntity.status(201).body(new ResponseDeliveryDto(delivery.getId(), userId, response));

        }catch(Exception e){
            logger.error("Error on start delivery for user {}: {}", userId, e.getMessage());
            return ResponseEntity.internalServerError().body("ERROR: " + e);
        }
    }

    public ResponseEntity<?> finishDelivery(String deliveryId, String userId){
        logger.info("Finishing the delivery {}", deliveryId);
        try{
            Optional<User> findedUser = userRepository.findById(userId);
            Optional<Delivery> openedDelivery = deliveryRepository.findById(deliveryId);

            if(openedDelivery.isEmpty() || findedUser.isEmpty()){
                logger.warn("User {} and Delivery {} not found", userId, deliveryId);
                return ResponseEntity.status(400).body("ERROR: Delivery or User doesn't exist!");
            }

            Delivery delivery = openedDelivery.get();

            Finished finishedDelivery = new Finished();
            finishedDelivery.setStartTime(delivery.getInit().toString());
            finishedDelivery.setName(findedUser.get().getName());
            finishedDelivery.setFinishTime(Instant.now());
            finishedDelivery.setOrder(delivery.getOrders());

            deliveryRepository.delete(delivery);
            finishedRepository.save(finishedDelivery);

            logger.info("Delivery {} finished", deliveryId);

            Map<String, String> response = new HashMap<>();
            response.put("message", "Delivery finished successfully.");
            return ResponseEntity.status(201).body(response);

        }catch(Exception e){
            logger.error("Error on finishing the delivery {} - {}", deliveryId, e.getMessage());
            return ResponseEntity.status(500).body("ERROR: " + e);
        }

    }

}
