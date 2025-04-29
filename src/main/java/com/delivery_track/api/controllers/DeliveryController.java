package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.FinishDeliveryDto;
import com.delivery_track.api.dtos.LocationDto;
import com.delivery_track.api.models.Delivery;
import com.delivery_track.api.repositories.DeliveryRepository;
import com.delivery_track.api.services.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery/track")
@CrossOrigin("*")
@Tag(
        name = "Delivery",
        description = "Endpoints to create and finish delivery"
)
public class DeliveryController {

    @Autowired
    private DeliveryRepository deliveryRepository;
    @Autowired
    private DeliveryService deliveryService;

    @PostMapping("/init/{id}")
    @Operation(
            summary = "Init",
            description = "Create a new delivery"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create a new delivery"),
            @ApiResponse(responseCode = "400", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> initDelivery(@PathVariable String id){
        return deliveryService.initDelivery(id);
    }

    @PostMapping("/finish")
    @Operation(
            summary = "Finish",
            description = "Finish a delivery"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Finished a delivery"),
            @ApiResponse(responseCode = "400", description = "User not found or Delivery"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<?> finishDelivery(@RequestBody FinishDeliveryDto finish){
        return deliveryService.finishDelivery(finish.deliveryId(), finish.userId());
    }

}
