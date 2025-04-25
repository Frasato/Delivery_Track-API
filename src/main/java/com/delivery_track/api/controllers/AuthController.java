package com.delivery_track.api.controllers;

import com.delivery_track.api.dtos.AuthDto;
import com.delivery_track.api.dtos.ResponseAuthDto;
import com.delivery_track.api.models.User;
import com.delivery_track.api.repositories.UserRepository;
import com.delivery_track.api.security.config.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/delivery/track")
public class AuthController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@RequestBody AuthDto authDto){
        User user = new User();

        user.setName(authDto.name());
        user.setEmail(authDto.email());
        user.setPassword(passwordEncoder.encode(authDto.password()));

        userRepository.save(user);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> userLogin(@RequestBody AuthDto authDto){
        Optional<User> findedUser = userRepository.findUserByEmail(authDto.email());

        User user = findedUser.get();

        if(passwordEncoder.matches(authDto.password(), user.getPassword())){
            String token = tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseAuthDto(user.getName(), user.getEmail(), token));
        }

        return ResponseEntity.badRequest().build();
    }

}
