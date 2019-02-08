package com.example.car_dealer.service;

import com.example.car_dealer.model.User;
import com.example.car_dealer.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private UserRepository userRepository;
    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }
}
