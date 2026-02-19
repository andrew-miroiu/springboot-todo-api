package com.andrei.springboot.controller;

import com.andrei.springboot.model.User;
import com.andrei.springboot.model.Todo;
import com.andrei.springboot.dto.UserRegisterRequest;
import com.andrei.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public User register(@RequestBody @Valid UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());

        return userService.register(user);
    }

    @GetMapping()
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{username}")
    public User findByUsername(@PathVariable String username){
        return userService.findByUsername(username);
    }

    
    
}
