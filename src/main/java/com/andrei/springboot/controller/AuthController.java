package com.andrei.springboot.controller;

import com.andrei.springboot.service.JwtService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @GetMapping("/me")
    public String getMe(@RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7); // scoatem "Bearer "

        String username = jwtService.extractUsername(token);

        return "Logged user: " + username;
    }

    @PostMapping("/users/{id}/test")
    public String testOwnership(
            @PathVariable Long id,
            @RequestHeader("Authorization") String authHeader) {

        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);

        // aici simulăm că id 1 = andrew
        if(id == 1L && username.equals("andrei")) {
            return "Allowed";
        }

        return "Forbidden";
    }

    @GetMapping("/hello")
    public String hello() {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return "Hello " + username;
    }


}
