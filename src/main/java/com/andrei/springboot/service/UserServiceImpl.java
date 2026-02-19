package com.andrei.springboot.service;

import org.springframework.stereotype.Service;
import com.andrei.springboot.repository.UserRepository;
import com.andrei.springboot.model.User;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public List<User> findAll(){
        return userRepository.findAll();
    }
}
