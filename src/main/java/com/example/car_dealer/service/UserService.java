package com.example.car_dealer.service;

import com.example.car_dealer.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User addUser(User user);
    List<User> findUsersInDB();
    Optional<User> findUserInDB(String userName);


}
