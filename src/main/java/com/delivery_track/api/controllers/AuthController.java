package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.AuthDto;
import com.delivery_track.api.dtos.LoginDto;
import com.delivery_track.api.dtos.ResponseAuthDto;
import com.delivery_track.api.models.User;
import com.delivery_track.api.repositories.UserRepository;
import com.delivery_track.api.security.config.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/delivery/track")
@CrossOrigin("*")
@Tag(
        name = "Authenticate",
        description = "Endpoints to register or login users"
)
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @Operation(
            summary = "Register",
            description = "Create a new user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Create a new user"),
            @ApiResponse(responseCode = "400", description = "Invalid Information")
    })
    public ResponseEntity<?> userRegister(@RequestBody AuthDto authDto){
        if(authDto.email().isEmpty() || authDto.name().isEmpty() || authDto.password().isEmpty()){
            return ResponseEntity.status(400).body("Error: The field name, email or password should be not empty!");
        }
        User user = new User();

        user.setName(authDto.name());
        user.setEmail(authDto.email());
        user.setPassword(passwordEncoder.encode(authDto.password()));

        userRepository.save(user);
        return ResponseEntity.status(201).body(user);
    }

    @PostMapping("/login")
    @Operation(
            summary = "Login",
            description = "Find and authenticate user"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Authenticated"),
            @ApiResponse(responseCode = "400", description = "Invalid Information")
    })
    public ResponseEntity<?> userLogin(@RequestBody LoginDto loginDto){
        Optional<User> findedUser = userRepository.findUserByEmail(loginDto.email());

        if(findedUser.isEmpty()) return ResponseEntity.status(400).body("Error: user not found!");

        User user = findedUser.get();

        if(passwordEncoder.matches(loginDto.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return ResponseEntity.status(200).body(new ResponseAuthDto(user.getId(), user.getName(), user.getEmail(), token));
        }

        return ResponseEntity.status(400).build();
    }

}
