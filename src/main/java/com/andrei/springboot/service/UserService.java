package com.andrei.springboot.service;

import com.andrei.springboot.model.User;
import java.util.List;

public interface UserService {

    User register(User user);
    List<User> findAll();
    User findByUsername(String username);

}
