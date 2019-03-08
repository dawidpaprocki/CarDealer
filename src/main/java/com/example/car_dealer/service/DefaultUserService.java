package com.example.car_dealer.service;

import com.example.car_dealer.model.User;
import com.example.car_dealer.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultUserService implements UserService {
    private UserRepository userRepository;

    public DefaultUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(User user) {

        return userRepository.save(user);
    }

    @Override
    public List<User> findUsersInDB() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserInDB(String userName) {
        return userRepository.findByUserName(userName);
    }
}
