package com.delivery_track.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Delivery Track API",
                version = "1.0",
                description = "API to track deliveries in realtime"
        )
)
public class SwaggerConfig {
}
