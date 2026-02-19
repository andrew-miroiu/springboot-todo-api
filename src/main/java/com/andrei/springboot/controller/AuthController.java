package com.andrei.springboot.controller;

import com.andrei.springboot.service.JwtService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username) {

        // momentan nu verificăm parola, doar generăm token
        return jwtService.generateToken(username);
    }
}
